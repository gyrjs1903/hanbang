package com.green.hanbang.realtor.controller;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.service.RealtorService;
import com.green.hanbang.realtor.vo.LicenseImgVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.util.LicenseUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/realtor")
public class RealtorController {
    private final RealtorService realtorService;

    @RequestMapping("/main")
    public String realtor(){
        return "realtor/realtor_layout";
    }

    @GetMapping("/myPage")
    public String myPage(Model model,HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        model.addAttribute("realtorInfo",realtorService.selectRealtorMyPage(loginInfo.getUserNo()));
        model.addAttribute("authority",realtorService.selectAuthorityStatue(loginInfo.getUserNo()));
        return "realtor/realtor_mypage";
    }

    @GetMapping("/certificationForm")
    public String certificationForm(){
        return "realtor/certification";
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
}
