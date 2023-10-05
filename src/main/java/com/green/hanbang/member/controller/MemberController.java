package com.green.hanbang.member.controller;

import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원 가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm(){
        return "content/member/join";
    }

    // 회원 가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        memberService.join(memberVO);
        return "content/member/login";
    }

    // 로그인 페이지 이동
    @GetMapping("loginForm")
    public String loginForm(MemberVO memberVO){
        return "content/member/login";
    }

    // 로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);

        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
//            if(loginInfo.getLoginType().equals("realtor")){
//                return "redirect:/realtor/main";
//            }
        }

        return "content/member/login_result";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

    // 내 정보 관리 페이지
    @GetMapping("/memberInfo")
    public String memberInfo(){
        return "content/member/member_info";
    }
}
