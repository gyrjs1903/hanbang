package com.green.hanbang;

import com.green.hanbang.member.service.MemberService2;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.vo.InquiryVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final MemberService2 memberService2;
    // 시작페이지
    @GetMapping("/")
    public String main(){
        return "main/home";
    }
    //카피
    ////////////////////////////////////////////
    //자바에서 a태그 매개변수 최대한 줄여서 보내기
    @GetMapping("/2")
    public String main2(Model model, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        if(loginInfo.getLoginType().equals("REALTOR")){
            model.addAttribute("authorityAlarm",memberService2.selectAuthorityAlarm(loginInfo.getUserNo()));
            model.addAttribute("realtorInquiryList",memberService2.selectInquiryAlarm(loginInfo.getUserNo()));
        }
        return "main/home222";
    }
}