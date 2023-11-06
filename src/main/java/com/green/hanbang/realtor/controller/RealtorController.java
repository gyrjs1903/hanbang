package com.green.hanbang.realtor.controller;

import com.green.hanbang.IndexController;
import com.green.hanbang.item.vo.GeneralItemVO;
import com.green.hanbang.item.vo.PackageItemVO;
import com.green.hanbang.item.vo.PlusItemVO;
import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.service.RealtorService;
import com.green.hanbang.realtor.vo.LicenseImgVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.service.RoomService;
import com.green.hanbang.room.vo.InquiryVO;
import com.green.hanbang.util.LicenseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/realtor")
public class RealtorController {
    private final RealtorService realtorService;
    private final MemberService memberService;
    private final RoomService roomService;

    @RequestMapping("/main")
    public String realtor(){
        return "realtor/realtor_layout";
    }

    @GetMapping("/myPage")
    public String myPage(Model model,HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        model.addAttribute("realtorInfo",realtorService.selectRealtorMyPage(loginInfo.getUserNo()));
        model.addAttribute("authority",realtorService.selectAuthorityStatue(loginInfo.getUserNo()));
        model.addAttribute("realtorDetailInfo",realtorService.selectRealtorDetailInfo(loginInfo.getUserNo()));
        model.addAttribute("realtorOfficeInfo",realtorService.selectRealtorOfficeInfo(loginInfo.getUserNo()));
        return "realtor/realtor_mypage";
    }

    @GetMapping("/certificationForm")
    public String certificationForm(){
        return "realtor/certification";
    }

    @ResponseBody
    @PostMapping("/identificationNum")
    public boolean selectIdentificationNum(@RequestBody Map<String, String> identificationNum){
        System.out.println(identificationNum);
        String num = realtorService.selectIdentificationNum(identificationNum.get("identificationNum"));
        return num == null;
    }

    @PostMapping("/certification")
    public String certification(RealtorDetailVO realtorDetailVO, MultipartFile licenseImg,HttpSession session){
        //공인중개사 상세페이지 등록
        //1. 다음에 들어가야할 REALTOR_CODE 조회
        String realtorCode = realtorService.selectNextRealtorCode();

        //사업자등록증 파일 첨부
        LicenseImgVO img = LicenseUtil.licenseUtil(licenseImg);
        img.setRealtorCode(realtorCode);

        //정보&파일 등록
        realtorDetailVO.setRealtorCode(realtorCode);
        realtorDetailVO.setLicenseFileName(img.getAttachedLicenseFileName());
        realtorDetailVO.setLicenseImgVO(img);
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        realtorDetailVO.setUserNo(loginInfo.getUserNo());
        System.out.println(realtorDetailVO);

        realtorService.insertRealtorDetail(realtorDetailVO);

        return "redirect:/realtor/myPage";
    }

    @GetMapping("/pwCorrectPage")
    public String pwCorrectPage(){
        return "realtor/realtor_pwCorrect";
    }

    //본인확인
    @ResponseBody
    @PostMapping("/PWCorrect")
    public String PWCorrect(HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
         return realtorService.selectRealtorPw(loginInfo.getUserNo());
    }

    //본인확인
    @PostMapping("/PWIdentify")
    public String PWIdentify(HttpSession session, Model model){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        model.addAttribute("realtorInfo",realtorService.selectRealtorMyPage(loginInfo.getUserNo()));
        model.addAttribute("realtorOfficeInfo",realtorService.selectRealtorOfficeInfo(loginInfo.getUserNo()));
        return "realtor/realtor_update";
    }

    //공인중개사 정보 수정
    @PostMapping("/realtorInfoUpdate")
    public String realtorInfoUpdate(MemberVO memberVO){
        System.out.println("@@@@@@@@@@@@"+memberVO);
        realtorService.updateRealtorInfo(memberVO);
        if(memberVO.getRealtorDetailVO() != null){
            realtorService.updateRealtorOffice(memberVO);
        }
        return "redirect:/realtor/myPage";
    }

    //문의글 조회
    @GetMapping("/inquiryBoardList")
    public String inquiryBoardList(HttpSession session,Model model, InquiryVO inquiryVO){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        int totalDataCnt = realtorService.countInquiryCnt(loginInfo.getUserNo());
        inquiryVO.setTotalDataCnt(totalDataCnt);
        inquiryVO.setToUserNo(loginInfo.getUserNo());
        inquiryVO.setPageInfo();

        List<InquiryVO> inquiries = realtorService.selectInquiryBoard(inquiryVO);
        if(!inquiries.isEmpty()){
            int boardNum = totalDataCnt - (inquiryVO.getNowPage()-1)*inquiryVO.getDisplayDataCnt();
            model.addAttribute("boardNum",boardNum);
            model.addAttribute("inquiryBoardList", inquiries);
        } else {
            model.addAttribute("inquiryBoardList", "null");
        }
        return "realtor/inquiry_board";
    }

    //문의글 상세 조회
    @GetMapping("/inquiryDetail")
    public String inquiryDetail(String inquiryCode, Model model){
        model.addAttribute("inquiryDetail",realtorService.selectInquiryDetail(inquiryCode));
        return "realtor/inquiry_detail";
    }

    //문의글 답변 작성
    @PostMapping("/inquiryAnswer")
    public String inquiryAnswer(InquiryVO inquiryVO,HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        realtorService.updateInquiryAnswer(inquiryVO);
        setAlarmData(loginInfo);
        return "redirect:/realtor/inquiryBoardList";
    }

    //공인중개사 상품 구매 목록 조회
    @GetMapping("/buyItemList")
    public String selectBuyItem(HttpSession session, Model model){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        List<PackageItemVO> packageItemVOList = roomService.selectPackageItemList(loginInfo.getUserNo());
        List<GeneralItemVO> generalItemVOList = roomService.selectGeneralItemList(loginInfo.getUserNo());
        List<PlusItemVO> plusItemVOList = roomService.selectPlusItemList(loginInfo.getUserNo());
        model.addAttribute("packageItemVOList",packageItemVOList);
        model.addAttribute("generalItemVOList",generalItemVOList);
        model.addAttribute("plusItemVOList",plusItemVOList);
        System.out.println("@@@@@@@@@@@@패키지@@@@@@@@@@@");
        System.out.println(packageItemVOList);
        System.out.println("@@@@@@@@@@@@일반@@@@@@@@@@@");
        System.out.println(generalItemVOList);
        System.out.println("@@@@@@@@@@@@플러스@@@@@@@@@@@");
        System.out.println(plusItemVOList);
        return "/realtor/buy_item";
    }

    //다른페이지에서 알림창 확인 메소드
    public void setAlarmData(MemberVO loginInfo){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpSession session = request.getSession();
        int alarmCnt = 0;
        //공인중개사
        if (loginInfo.getLoginType().equals("REALTOR")) {
            if (memberService.selectAlarm(loginInfo.getUserNo()) != null) {
                // 권한승인알림
                int authorityStatus = memberService.selectAuthorityAlarm(loginInfo.getUserNo());

                //model.addAttribute("authorityAlarm", authorityStatus);
                session.setAttribute("authorityAlarm", authorityStatus);

                //매물문의알림
                RealtorDetailVO realtor = memberService.selectInquiryAlarm(loginInfo.getUserNo());
                int realtorInquiryCnt = 0;
                if(!realtor.getInquiryList().isEmpty()){
                    for (InquiryVO inquiry : realtor.getInquiryList()) {
                        if (inquiry.getInquiryAnswer() == null) {
                            realtorInquiryCnt += 1;
                        }
                    }
                    //model.addAttribute("realtorInquiryCnt", realtorInquiryCnt);
                    session.setAttribute("realtorInquiryCnt", realtorInquiryCnt);
                }

                //총 알림 개수
                if (authorityStatus == 1) alarmCnt += 1;
                if (realtorInquiryCnt != 0) alarmCnt += 1;
            }
            //model.addAttribute("alarmCnt", alarmCnt);
            session.setAttribute("alarmCnt", alarmCnt);
        }
        //일반회원
        if (loginInfo.getLoginType().equals("USER")) {
            List<InquiryVO> userRoomInquiry = memberService.selectUserInquiryAlarm(loginInfo.getUserNo());
            System.out.println(userRoomInquiry);
            int userInquiryAnswer = 0;
            for (InquiryVO inquiry : userRoomInquiry) {
                if (inquiry.getInquiryAnswer() != null && inquiry.getInquiryReadCnt() == 0) {
                    userInquiryAnswer += 1;
                }
            }
            if (userInquiryAnswer != 0) alarmCnt += 1;
            //model.addAttribute("userInquiryAnswer", userInquiryAnswer);
            session.setAttribute("userInquiryAnswer", userInquiryAnswer);
            //model.addAttribute("alarmCnt", alarmCnt);
            session.setAttribute("alarmCnt", alarmCnt);
        }

    }
}
