package com.green.hanbang.member.controller;

import com.green.hanbang.member.service.MemberInquiryService;
import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.*;
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

    // 회원 가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm() {
        return "content/member/join";
    }

    // 회원 가입
    @PostMapping("/join")
    public String join(MemberVO memberVO) {
        // 회원가입 (회원 코드를 조회하기 위해 먼저 실행)
        memberService.join(memberVO);

        // 가입한 회원 번호 가져오기
        String userNo = memberService.selectUserNo(memberVO.getUserNo());
        System.out.println(memberVO.getUserNo());

        MemberImgVO memberImgVO = new MemberImgVO();
        memberImgVO.setUserNo(userNo);
        memberImgVO.setProfileImgName("img/member/profileImg/default_profile_image.png");
        memberImgVO.setAttachedProfileImgName("img/member/profileImg/default_profile_image.png");

        // 프로필 이미지 등록
        memberService.insertProImg(memberImgVO);

        String userName = memberVO.getUserName();

        return "redirect:/member/loginForm?userName=" + userName;
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
                return "redirect:/";
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
        // 현재 로그인 한 유저 번호를 조회
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
    public String insertPro(MemberVO memberVO, @RequestParam("profileImg") MultipartFile memberImg, HttpSession session) {
        // 유저 번호 조회
        String userNo = memberVO.getUserNo();

        // 정보 세팅
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        memberVO.setUserNo(loginInfo.getUserNo());

        // 파일 업로드 및 관련 작업 처리
        MemberImgVO uploadedMemberImg = MemberUtil.MemberUploadFile(memberImg);
        uploadedMemberImg.setUserNo(userNo); // 파일 업로드 후 유저 번호 설정

        // DB에 프로필 이미지 정보 등록
        memberService.insertProImg(uploadedMemberImg);

        String errorMessage = "프로필 이미지 등록에 실패했습니다. 나중에 다시 시도해주세요.";

        // 실패 메시지를 세션에 설정
        session.setAttribute("errorMessage", errorMessage);

        return "redirect:/member/memberInfo";
    }

    // 알림 페이지로 이동 (공인중개사 <-> 회원)
    @GetMapping("/memberCall")
    public String memberCall(HttpSession session,Model model) {
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<InquiryVO> roomInquiryList = memberService.selectUserInquiryAlarm(loginInfo.getUserNo());
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

    // 1:1문의 작성물 전송
    @PostMapping("memberInquirySave")
    public String memberInquirySave(MemberInquiryVO memberInquiryVO, MemberInquiryImgVO memberInquiryImgVO, MemberInquiryTypeVO memberInquiryTypeVO){

        memberInquiryService.insertMemberInquiryType(memberInquiryTypeVO);
        memberInquiryService.insertMemberInquiry(memberInquiryVO);
        memberInquiryService.insertMemberInquiryImg(memberInquiryImgVO);

        return "redirect:/member/memberInquiry";

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

    // 헤더에 찜목록 누를 시 찜목록(최근 본 방) 페이지로 이동
    @GetMapping("/dibsOn")
    public String dibsOn() {
        return "content/member/recent_viewed_room";
    }

    // 찜한 방 페이지로 이동
    @GetMapping("/dibsOnRoom")
    public String dibsOnRoom() {
        return "content/member/dibs_on_room";
    }

    // 최근 본 단지 페이지로 이동
    @GetMapping("/recentViewedApartment")
    public String recentViewedApartment() {
        return "content/member/recent_viewed_apartment";
    }

    // 찜한 단지 페이지로 이동
    @GetMapping("/dibsOnApartment")
    public String dibsOnApartment() {
        return "content/member/dibs_on_apartment";
    }

    // 즐겨찾기 페이지로 이동
    @GetMapping("/wishListFavorites")
    public String wishListFavorites() {
        return "content/member/wish_list_favorites";
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