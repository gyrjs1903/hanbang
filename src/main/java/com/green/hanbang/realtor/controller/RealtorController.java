package com.green.hanbang.realtor.controller;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.service.RealtorService;
import com.green.hanbang.realtor.vo.LicenseImgVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.InquiryVO;
import com.green.hanbang.util.LicenseUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/realtor")
public class RealtorController {
    private final RealtorService realtorService;
    private final RoomService2 roomService2;

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

        return "redirect:/realtor/main";
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
        return "realtor/realtor_update";
    }

    //공인중개사 정보 수정
    @PostMapping("/realtorInfoUpdate")
    public String realtorInfoUpdate(MemberVO memberVO){
        realtorService.updateRealtorInfo(memberVO);
        return "redirect:/realtor/myPage";
    }

    //문의글 조회
    @GetMapping("/inquiryBoardList")
    public String inquiryBoardList(HttpSession session,Model model){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        System.out.println(realtorService.selectInquiryBoard(loginInfo.getUserNo()));
        model.addAttribute("inquiryBoardList",realtorService.selectInquiryBoard(loginInfo.getUserNo()));
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
    public String inquiryAnswer(InquiryVO inquiryVO){
        System.out.println(inquiryVO);
        realtorService.updateInquiryAnswer(inquiryVO);
        return "redirect:/realtor/inquiryBoardList";
    }
}
