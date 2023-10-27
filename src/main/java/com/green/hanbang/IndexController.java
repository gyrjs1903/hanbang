package com.green.hanbang;

import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final MemberService memberService;
    // 시작페이지
    @GetMapping("/")
    public String main(){
        return "main/home";
    }
    //카피
    ////////////////////////////////////////////
    //자바에서 a태그 매개변수 최대한 줄여서 보내기
//    @GetMapping("/")
//    public String main(Model model, HttpSession session){
//        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
//        if(loginInfo.getLoginType().equals("REALTOR")){
//            model.addAttribute("authorityAlarm",memberService.selectAuthorityAlarm(loginInfo.getUserNo()));
//            model.addAttribute("realtorInquiryList",memberService.selectInquiryAlarm(loginInfo.getUserNo()));
//        }
//        return "main/home";
//    }
}