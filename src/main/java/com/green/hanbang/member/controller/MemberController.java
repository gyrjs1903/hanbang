package com.green.hanbang.member.controller;

import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.MemberImgVO;
import com.green.hanbang.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String login(MemberVO memberVO, HttpSession session, HttpServletRequest request) {

        MemberVO loginInfo = memberService.login(memberVO);

        if (loginInfo != null) {
            session.setAttribute("loginInfo", loginInfo);
            if (loginInfo.getLoginType().equals("USER")) {
                return "redirect:/";
            } else if(loginInfo.getLoginType().equals("REALTOR")) {
                return "redirect:/realtor/main";
            } else if(loginInfo.getLoginType().equals("ADMIN")) {
                return "redirect:/admin/admin_manage";
            }
        }

        // 아이디나 비밀번호가 틀린 경우
        String userName = request.getParameter("userName");
        return "redirect:/member/loginForm?userName=" + userName;
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

    // 내 정보 페이지로 이동
    @GetMapping("/memberInfo")
    public String memberInfo(MemberVO memberVO, HttpSession session, Model model) {
        // 현재 로그인한 유저 번호를 조회
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        // 가져온 회원 정보를 모델에 담기
        model.addAttribute("userName", loginInfo);

        return "content/member/user_info";
    }
    
    // 프로필 이미지 등록
    @PostMapping("/updateProfileImg")
    public String insertProImg(MemberImgVO memberImgVO){

        memberService.insertProImg(memberImgVO);

        return "redirect:/member/memberInfo";
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

    // 헤더에 찜목록 누를 시 찜목록 페이지로 이동
    @GetMapping("/dibsOn")
    public String memberDibsOn() {
        return "content/member/recent_viewed_room";
    }
}