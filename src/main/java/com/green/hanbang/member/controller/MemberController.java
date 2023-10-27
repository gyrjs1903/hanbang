package com.green.hanbang.member.controller;

import com.green.hanbang.member.service.MemberInquiryService;
import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.MemberImgVO;
import com.green.hanbang.member.vo.MemberInquiryTypeVO;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.vo.InquiryVO;
import com.green.hanbang.util.MemberUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberInquiryService memberInquiryService;
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

        // 가입 한 회원 번호 조회
        String userNo = memberService.selectUserNo(memberVO.getUserNo());

        // 회원 번호가 null이 아니면 해당 회원에게 기본 프로필 이미지 삽입
        MemberImgVO memberImgVO = new MemberImgVO();
        memberImgVO.setUserNo(userNo);
        memberImgVO.setProfileImgName("img/member/profileImg/default_profile_image.png");
        memberImgVO.setAttachedProfileImgName("img/member/profileImg/default_profile_image.png");

        memberService.insertProImg(memberImgVO);

        String userName = memberVO.getUserName();

        return "redirect:/member/login?userName=" + userName;
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

        // 로그인 타입에 따라 페이지 이동
        if (loginInfo != null) {
            session.setAttribute("loginInfo", loginInfo);
            if (loginInfo.getLoginType().equals("USER")) {
                return "redirect:/";
            } else if (loginInfo.getLoginType().equals("REALTOR")) {
                return "redirect:/realtor/main";
            } else if (loginInfo.getLoginType().equals("ADMIN")) {
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
    public String memberInfo(Model model, HttpSession session) {
        // 현재 로그인한 유저 번호를 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        if (loginInfo != null) {
            String userNo = loginInfo.getUserNo();
            String memberInfo = memberService.selectUserNo(userNo);

            model.addAttribute("memberInfo", memberInfo);

            return "content/member/user_info";
        } else {
            // 미 로그인 한 상태로 페이지 접근 시 로그인 창으로 이동
            return "content/member/loginForm";
        }
    }
    
    // 프로필 이미지 등록
    @PostMapping("/updateProfile")
    public String insertPro(MemberVO memberVO, MemberImgVO memberImgVO, MultipartFile memberImg, HttpSession session) {

        // 유저 번호 조회
        String userNo = memberService.selectUserNo(memberVO.getUserNo());

        // 프로필 이미지 파일 첨부
        MemberImgVO vo = MemberUtil.MemberUploadFile(memberImg);
        vo.setUserNo(userNo);

        // 정보 세팅
        memberImgVO.setUserNo(userNo);
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        memberVO.setUserNo(loginInfo.getUserNo());

        memberService.insertProImg(memberImgVO);

        return "redirect:/member/memberInfo";
    }
    
    // 전화 문의 페이지로 이동
    @GetMapping("/memberCall")
    public String memberCall(HttpSession session,Model model) {
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<InquiryVO> roomInquiryList = memberService2.selectUserInquiryAlarm(loginInfo.getUserNo());
        System.out.println(roomInquiryList);
        model.addAttribute("roomInquiryList",roomInquiryList);
        return "content/member/user_call";
    }

    // 1:1문의 페이지로 이동
    @GetMapping("/memberInquiry")
    public String memberInquiry() {
        return "content/member/user_inquiry";
    }

    // 1:1문의 작성 페이지로 이동
    @GetMapping("/memberInquiryWrite")
    public String memberInquiryWrite(Model model, MemberInquiryTypeVO memberInquiryTypeVO){

        // 문의 유형 목록
        model.addAttribute("memberInquiryTypeList", memberInquiryService.selectMemberInquiryTypeList(memberInquiryTypeVO));

        return "content/member/user_inquiry_write";
    }

    // 허위 매물 신고 내역 페이지로 이동
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

    // 닉네임 변경
    @PostMapping("/updateNickName")
    public int updateNickName(MemberVO memberVO){
        return memberService.updateNickName(memberVO);
    }

    // 비밀 번호 변경
    @PostMapping("/updatePassWord")
    public int updatePassWord(MemberVO memberVO){
        return memberService.updatePassWord(memberVO);
    }

    // 회원 탈퇴
    @GetMapping("/deleteMember")
    public int deleteMember(int userNo){
        return memberService.deleteMember(userNo);
    }

}