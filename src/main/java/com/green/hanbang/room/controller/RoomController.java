package com.green.hanbang.room.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.hanbang.item.service.ItemService;
import com.green.hanbang.item.vo.BuyVO;
import com.green.hanbang.item.vo.GeneralItemVO;
import com.green.hanbang.item.vo.PackageItemVO;
import com.green.hanbang.item.vo.PlusItemVO;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.service.RoomService;
import com.green.hanbang.room.vo.*;
import com.green.hanbang.util.RoomUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/reg")
    public String regRoom(Model model, HttpSession session, HttpServletResponse response){
        MemberVO loginInfo  = (MemberVO) session.getAttribute("loginInfo");
        if(loginInfo ==null){
            return "redirect:/member/loginForm";
        }else {
            //옵션 셀렉트
            List<OptionsVO> optionsList = roomService.selectOptions();
            //매물유형 셀렉트
            List<PropertyTypeVO> propertyList = roomService.selectProperty();
            //전월세 셀렉트
            List<TradeTypeVO> tradeTypeList = roomService.selectTradeType();


            model.addAttribute("optionsList", optionsList);
            model.addAttribute("propertyList", propertyList);
            model.addAttribute("tradeTypeList", tradeTypeList);


//        //수연 추가 코드
        //1. 현재 등록하려는 사람의 타입 (입반, 업자)
        //2. 업자라면 상품 기능을 사용할 수 있도록 html에 코드추가

        List<PackageItemVO> packageItemList = roomService.selectPackageItemList(loginInfo.getUserNo());
        List<GeneralItemVO> generalItemList = roomService.selectGeneralItemList(loginInfo.getUserNo());
        List<PlusItemVO> plusItemList = roomService.selectPlusItemList(loginInfo.getUserNo());

        //내가 가지고 있는 상품의 수량
        int itemCnt = packageItemList.size() + generalItemList.size() + plusItemList.size();
        model.addAttribute("itemCnt", itemCnt);

        model.addAttribute("packageItemList", roomService.selectPackageItemList(loginInfo.getUserNo()));
        model.addAttribute("generalItemList", roomService.selectGeneralItemList(loginInfo.getUserNo()));
        model.addAttribute("plusItemList", roomService.selectPlusItemList(loginInfo.getUserNo()));

        System.out.println(roomService.selectPackageItemList(loginInfo.getUserNo()));


            return "room/reg_room";
        }
    }
    @PostMapping("/insertRoom")
    public String insertRoom(RoomVO roomVO, MultipartFile mainImg, MultipartFile[] subImg, RoomAddrVO roomAddrVO, ApplyItemVO applyItemVO){
        //상품이미지등록
        //RoomCode를 조회
        String roomCode = roomService.selectNextRoomCode();

        //이미지정보 하나가 들어갈 수 있는 통
        //첨부파일 단일
        RoomIMGVO roomIMGVO = RoomUtil.uploadFile(mainImg);

        //첨부파일 다중
        List<RoomIMGVO> imgList =RoomUtil.multiFileUpload(subImg);
        imgList.add(roomIMGVO);

        //RoomIMGVO에 RoomCode 저장
        for (RoomIMGVO roomIMGVO1 : imgList){
            roomIMGVO1.setRoomCode(roomCode);
        }

        roomAddrVO.setRoomCode(roomCode);

        roomVO.setRoomAddrVO(roomAddrVO);
        roomVO.setImgList(imgList);
        System.out.println(roomVO);
        roomService.insertRoom(roomVO);

        ////////////////////////////////////////////////////////

        System.out.println("22222222222222222222222222222222"+ applyItemVO);

        // 1. 상품을 선택했다면
        System.out.println(applyItemVO);
        if (!applyItemVO.getBuyCode().equals("")){
            applyItemVO.setRoomCode(roomCode);

            //매물 등록 시 상품 정보 추가
            roomService.insertApplyItem(applyItemVO);

            //사용한 매물에 대한 개수 차감
            roomService.updateItemCnt(applyItemVO);

            //차감했는데 만약 사용개수로 0개로 업데이트 됐으면 사용불가로 변경

            if(applyItemVO.getMemCateCode().equals("CATE_001")){
                boolean result =  roomService.getPackageIsValid(applyItemVO.getBuyCode());

                if(!result){
                    roomService.updatePackageIsValid(applyItemVO.getBuyCode());
                }

            }
            else if(applyItemVO.getMemCateCode().equals("CATE_002")){
                boolean result =  roomService.getGeneralIsValid(applyItemVO.getBuyCode());

                if(!result){
                    roomService.updateGeneralIsValid(applyItemVO.getBuyCode());
                }

            }



        }


        return "redirect:/room/roomMain";
    }
    @GetMapping("/roomMain")
    public String roomMain(Model model, RoomSearchVO roomSearchVO){

        model.addAttribute("tradeTypeList", roomService.selectTradeType());
        model.addAttribute("roomList", roomService.selectRoom(roomSearchVO));
        model.addAttribute("propertyTypeList", roomService.selectProperty());
        model.addAttribute("Options", roomService.selectOptions());
        List<RoomVO> room= roomService.selectRoom(roomSearchVO);
        System.out.println(room);
        return "room/room_main";
    }

    @ResponseBody
    @PostMapping("/setMap")
    public List<RoomAddrVO> setMap() {
        // 비동기 통신으로 위도경도 셀렉트
        List<RoomAddrVO> roomAddrs = roomService.selectRoomAddr();
        return roomAddrs;
    }
    @ResponseBody
    @PostMapping("/roomSearch")
    public List<RoomVO> roomSearch(@RequestBody Map<String, Object> searchData){
        System.out.println(searchData);

        ObjectMapper mapper = new ObjectMapper();
        RoomSearchVO roomSearchVO= mapper.convertValue(searchData, RoomSearchVO.class);
        System.out.println(roomSearchVO);
        List<RoomVO> roomList = roomService.selectRoom(roomSearchVO);
    return roomList;

    }

////////////////////////////////

    @GetMapping("/roomDetailInfo")
    public String roomDetailInfo(String roomCode, Model model, HttpServletRequest request){
        //방 모든 정보
        RoomVO room = roomService.selectRoomInfo(roomCode);
        System.out.println(room);
        model.addAttribute("roomDetail",room);

        //선택한 옵션
        String options = room.getDetailOptions();
        List<String> optionList = Arrays.asList(options.split(","));

        if(optionList.size()==1&&Objects.equals(optionList.get(0), "on")){
            model.addAttribute("optionList","on");
        } else {
            model.addAttribute("optionList", optionList);
        }


        //모든 옵션
        List<OptionsVO> os = roomService.selectOptions();
        model.addAttribute("allOptionList",roomService.selectOptions());

        //매물번호 (RoomCode 마지막 숫자 4자)
        String number = room.getRoomCode().substring(room.getRoomCode().length()-4);
        model.addAttribute("number", number);

        //방 등록한 사람 login_type 조회
        String loginType = roomService.selectLoginType(room.getUserNo());

        //등록한 사람 정보 조회
        if(Objects.equals(loginType, "USER")){
            model.addAttribute("personInfo",roomService.selectRegUser(room.getUserNo()));
        } else if(Objects.equals(loginType, "REALTOR")){
            model.addAttribute("personInfo",roomService.selectRegRealtor(room.getUserNo()));
        }

        //허위 매물 신고 사유
        model.addAttribute("reasonList",roomService.selectReasonList());

        //매물 문의 제목 조회
        model.addAttribute("inquiryTitleList",roomService.selectInquiryTitle());

        return "room/room_detail";
    }

    //옵션 값 있을 시 '있음' 표시
    @ResponseBody
    @PostMapping("/roomDetailFetch")
    public List<String> roomDetailFetch(@RequestBody Map<String, String> data){
        String options = roomService.selectRoomInfo(data.get("roomCode")).getDetailOptions();
        List<String> optionList = Arrays.asList(options.split(","));
        return optionList;
    }

    //본인인증
    @ResponseBody
    @PostMapping("/elDAS")
    public String elDAS(@RequestBody MemberVO memberVO){
        if(roomService.selectElDAS(memberVO) == null){
            return "null";
        }
        return roomService.selectElDAS(memberVO);
    }

    //허위매물신고
    @PostMapping("/falseOfferings")
    public String insertFalseOfferings(FalseOfferingsVO falseOfferingsVO){
        roomService.insertFalseOfferings(falseOfferingsVO);
        return "redirect:/room/roomDetailInfo?roomCode=" + falseOfferingsVO.getRoomCode();
    }

    //매물문의
    @ResponseBody
    @PostMapping("/insertInquiry")
    public boolean insertInquiry(@RequestBody InquiryVO inquiryVO, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        inquiryVO.setFromUserNo(loginInfo.getUserNo());

        return roomService.insertInquiry(inquiryVO);
    }

}
