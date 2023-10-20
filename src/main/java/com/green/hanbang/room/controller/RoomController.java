package com.green.hanbang.room.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.service.RoomService;
import com.green.hanbang.room.vo.*;
import com.green.hanbang.util.RoomUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/reg")
    public String regRoom(Model model, HttpSession session){
        //옵션 셀렉트
        List<OptionsVO> optionsList = roomService.selectOptions();
        //매물유형 셀렉트
        List<PropertyTypeVO> propertyList = roomService.selectProperty();
        //전월세 셀렉트
        List<TradeTypeVO> tradeTypeList = roomService.selectTradeType();

        MemberVO loginInfo  = (MemberVO) session.getAttribute("loginInfo");
        model.addAttribute("optionsList", optionsList);
        model.addAttribute("propertyList", propertyList);
        model.addAttribute("tradeTypeList", tradeTypeList);

        return "room/reg_room";
    }
    @PostMapping("/insertRoom")
    public String insertRoom(RoomVO roomVO, MultipartFile mainImg, MultipartFile[] subImg, RoomAddrVO roomAddrVO){
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
        roomService.insertRoom(roomVO);
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
}
