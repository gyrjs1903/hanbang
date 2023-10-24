package com.green.hanbang.member.controller;

import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.service.MemberService2;
import com.green.hanbang.member.vo.MemberImgVO;
import com.green.hanbang.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/member2")
@RequiredArgsConstructor
public class MemberController2 {
    private final MemberService memberService;
    private final MemberService2 memberService2;

    // 회원 가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm() {
        return "content/member/join";
    }

    // 회원 가입
    @PostMapping("/join")
    public String join(MemberVO memberVO) {
        memberService.join(memberVO);

        return "content/member/login";
    }

    // 로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(MemberVO memberVO) {
        return "content/member/login2";
    }

    // 로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session, HttpServletRequest request,Model model) {

        MemberVO loginInfo = memberService.login(memberVO);
        if (loginInfo != null) {
            session.setAttribute("loginInfo", loginInfo);
            if (loginInfo.getLoginType().equals("USER")) {
                return "redirect:/";
            } else if(loginInfo.getLoginType().equals("REALTOR")) {
                System.out.println(loginInfo);
                model.addAttribute("authorityAlarm",memberService2.selectAuthorityAlarm(loginInfo.getUserNo()));
                return "redirect:/";
            } else if(loginInfo.getLoginType().equals("ADMIN")) {
                return "redirect:/admin2/admin_manage";
            }
        }

        // 아이디나 비밀번호가 틀린 경우
        String userName = request.getParameter("userName");
        return "redirect:/member2/loginForm?userName=" + userName;
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

}