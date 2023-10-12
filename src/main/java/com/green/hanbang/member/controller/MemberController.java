package com.green.hanbang.member.controller;

import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

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

    // 회원 가입 시 닉네임 자동 생성

    // 회원 탈퇴
    @GetMapping("/memberDelete")
    public String memberDelete(MemberVO memberVO){
        memberService.memberDelete(memberVO);
        return "redirect:/main/home";
    }
    // 로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(MemberVO memberVO) {
        return "content/member/login";
    }

    // 로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session) {
        MemberVO loginInfo = memberService.login(memberVO);

        if (loginInfo != null) {
            session.setAttribute("loginInfo", loginInfo);
            if (loginInfo.getLoginType().equals("REALTOR")) {
                return "redirect:/realtor/main";
            }
        }

        return "content/member/login_result";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginInfo");
        return "redirect:/main/home";
    }

    // 비밀번호 재설정 창 팝업

    // 내 정보 페이지로 이동
    @GetMapping("/memberInfo")
    public String memberInfo(MemberVO memberVO, HttpSession session) {
        MemberVO loginInfo = memberService.login(memberVO);
        return "content/member/user_info";
    }

    // 문자문의 페이지로 이동
    @GetMapping("/memberSMS")
    public String memberSMS() {
        return "content/member/user_sms";
    }

    // 전화문의 페이지로 이동
    @GetMapping("/memberCall")
    public String memberCall() {
        return "content/member/user_call";
    }

    // 1:1문의 페이지로 이동
    @GetMapping("/memberInquiry")
    public String memberInquiry() {
        return "content/member/user_inquiry";
    }

    // 허위매물 신고내역 페이지로 이동
    @GetMapping("/memberReport")
    public String memberReport() {
        return "content/member/user_report";
    }

    // 이메일(아이디) 중복 확인 및 유효성 검사
    @ResponseBody
    @PostMapping("/userNameDuplicationCheckFetch")
    public String userNameDuplicationCheckFetch(@RequestParam String userName){
        return memberService.userNameCheck(userName);
    }
}