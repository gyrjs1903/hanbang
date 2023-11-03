package com.green.hanbang.member.controller;

import com.green.hanbang.member.service.MemberInquiryService;
import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.service.SaveService;
import com.green.hanbang.member.vo.*;
import com.green.hanbang.room.service.RoomService;
import com.green.hanbang.room.vo.InquiryVO;
import com.green.hanbang.room.vo.PropertyTypeVO;
import com.green.hanbang.room.vo.RoomIMGVO;
import com.green.hanbang.room.vo.RoomVO;
import com.green.hanbang.util.MemberUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberInquiryService memberInquiryService;
    private final RoomService roomService;
    private final SaveService saveService;

    // 회원 가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm() {
        return "content/member/join";
    }

    // 회원 가입
    @PostMapping("/join")
    public String join(MemberVO memberVO, RedirectAttributes rttr) {
        // 회원가입 (회원 코드를 조회하기 위해 먼저 실행)
        memberService.join(memberVO);

//        // 가입한 회원 번호 가져오기
//        String userNo = memberVO.getUserNo();
//
//        // 가입한 회원 번호 조회
//        memberService.selectUserNo(userNo);
//
//        MemberImgVO memberImgVO = new MemberImgVO();
//        memberImgVO.setUserNo(userNo);
//        memberImgVO.setProfileImgName("img/member/profileImg/default_profile_image.png");
//        memberImgVO.setAttachedProfileImgName("img/member/profileImg/default_profile_image.png");
//
//        // 프로필 이미지 등록
//        memberService.insertProImg(memberImgVO);

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
    public String login(MemberVO memberVO, HttpSession session, HttpServletRequest request, RedirectAttributes rttr) {

        MemberVO loginInfo = memberService.login(memberVO);

        // 로그인 타입에 따라 페이지 이동
        if (loginInfo != null) {
            session.setAttribute("loginInfo", loginInfo);

            // 수연 소스 추가
            // 로그인 시 사용자가 가지고 있는 상품 조회 후 상품의 endDate 비교 후 isValid 값 변경
            roomService.updatePackageValidWhenLogin(loginInfo.getUserNo());
            roomService.updateGeneralValidWhenLogin(loginInfo.getUserNo());
            roomService.updatePlusValidWhenLogin(loginInfo.getUserNo());

            if (loginInfo.getLoginType().equals("USER")) {
                return "redirect:/";
            } else if (loginInfo.getLoginType().equals("REALTOR")) {
                return "redirect:/";
            } else if (loginInfo.getLoginType().equals("ADMIN")) {
                return "redirect:/admin/manage";
            }
        }
        // 아이디나 비밀번호가 틀린 경우
        String userName = request.getParameter("userName");
        return "redirect:/member/loginForm?userName=" + userName;
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes rttr) {
        //모든 session 정보 삭제
        session.invalidate();
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
    public String insertPro(MemberImgVO memberImgVO, MultipartFile memberImg, HttpSession session, RedirectAttributes rttr) {

        // 유저 번호 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String userNo = loginInfo.getUserNo();

        // 단일 첨부 파일 업로드
        MemberImgVO uploadedMemberImg = MemberUtil.MemberUploadFile(memberImg);
        uploadedMemberImg.setUserNo(userNo); // 파일 업로드 후 유저 번호 설정

        // DB에 프로필 이미지 정보 등록
        memberService.insertProImg(memberImgVO);

        return "redirect:/member/memberInfo";
    }

    // 알림 페이지로 이동 (공인중개사 <-> 회원)
    @GetMapping("/memberCall")
    public String memberCall(HttpSession session, Model model) {
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<InquiryVO> roomInquiryList = memberService.selectUserInquiryAlarm(loginInfo.getUserNo());
        System.out.println(roomInquiryList);
        model.addAttribute("roomInquiryList",roomInquiryList);
        return "content/member/user_call";
    }

    // 1:1문의 페이지로 이동
    @GetMapping("/memberInquiry")
    public String memberInquiry(Model model, HttpSession session) {

        // 현재 로그인 한 유저 번호를 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String userNum = loginInfo.getUserNo();

        // MemberInquiryVO에 userNo를 설정
        MemberInquiryVO memberInquiryVO = new MemberInquiryVO();
        memberInquiryVO.setUserNo(userNum);

        // 문의 리스트 목록
        List<MemberInquiryVO> memberInquiryList = memberInquiryService.selectMemberInquiryList(memberInquiryVO);
        System.out.println(memberInquiryList);
        model.addAttribute("memberInquiryList", memberInquiryList);

        return "content/member/user_inquiry";
    }

    // 1:1문의 작성 페이지로 이동
    @GetMapping("/memberInquiryWrite")
    public String memberInquiryWrite(Model model, MemberInquiryTypeVO memberInquiryTypeVO){

        // 문의 유형 목록
        model.addAttribute("memberInquiryTypeList", memberInquiryService.selectMemberInquiryTypeList(memberInquiryTypeVO));

        return "content/member/user_inquiry_write";
    }

    // 1:1문의 전송
    @PostMapping("/memberInquirySave")
    public String memberInquirySave(HttpSession session, MultipartFile[] file, RedirectAttributes rttr){

        // 현재 로그인 한 유저 번호를 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String userNo = loginInfo.getUserNo();

        // MemberInquiryVO에 userNo를 설정
        MemberInquiryVO memberInquiryVO = new MemberInquiryVO();
        memberInquiryVO.setUserNo(userNo);

        // 문의 이미지 첨부 파일 null 값이 아니면 실행
        if (file != null) {
            // 다음 문의 작성 번호를 조회
            String inquiryWriteNo = memberInquiryService.selectNextInquiryNumber(memberInquiryVO.getMemberInquiryWriteNo());
            MemberInquiryImgVO memberInquiryImgVO = new MemberInquiryImgVO();
            memberInquiryImgVO.setMemberInquiryWriteNo(inquiryWriteNo);
            memberInquiryService.insertMemberInquiryImg(memberInquiryImgVO); // memberInquiryWriteNo
            memberInquiryService.insertMemberInquiry(memberInquiryVO);

        } else{

            memberInquiryService.insertMemberInquiry(memberInquiryVO);
        }

        return "redirect:/member/memberInquiry";
    }

    // 허위 매물 신고 내역 페이지로 이동
    @GetMapping("/memberReport")
    public String memberReport(Model model, HttpSession session) {

        // 문의 유형 목록

        return "content/member/user_report";
    }

    // 이메일(아이디) 중복 확인 및 유효성 검사
    @ResponseBody
    @PostMapping("/userNameDuplicationCheckFetch")
    public String userNameDuplicationCheckFetch(@RequestParam String userName){
        return memberService.userNameCheck(userName);
    }

    // 헤더에 찜목록 클릭 시 최근 본 방 페이지로 이동
    @GetMapping("/dibsOn")
    public String dibsOn(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        // 유저 번호 가져오기
        String userNo = loginInfo.getUserNo();

        // 쿠키 생성
        Cookie userCookie = new Cookie("userNo", userNo);
        response.addCookie(userCookie);

        // 최근 본 방 정보를 쿠키에서 가져와서 필요한 작업 수행
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("recentProperty")) {
                    String recentPropertyInfo = cookie.getValue();
                    // 최근 본 방 정보를 사용 하여 페이지에 표시하거나 다른 작업 수행
                    System.out.println("최근 본 방 정보: " + recentPropertyInfo);
                }
            }
        }

        return "content/member/recent_viewed_room?userNo=" + userNo;
    }

    // 찜한 방 페이지로 이동
    @GetMapping("/dibsOnRoom")
    public String dibsOnRoom(HttpSession session, String roomCode, Model model) {
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        String userNo = loginInfo.getUserNo();
        model.addAttribute("userNo", userNo);

        List<MemberSaveVO> save = saveService.selectSaveRoomList(userNo);
        model.addAttribute("saveList", save);

        return "content/member/dibs_on_room";
    }

    // 최근 본 단지 페이지로 이동
    @GetMapping("/recentViewedApartment")
    public String recentViewedApartment(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        // 쿠키 생성
        Cookie userCookie = new Cookie("userNo", loginInfo.getUserNo());
        response.addCookie(userCookie);

        // 쿠키 가져오기
        Cookie[] cookies = request.getCookies();
        String userNo = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userNo")) {
                    userNo = cookie.getValue();
                    break;
                }
            }
        }

        return "content/member/recent_viewed_apartment?userNo=" + userNo;
    }

    // 찜한 단지 페이지로 이동
    @GetMapping("/dibsOnApartment")
    public String dibsOnApartment(HttpSession session) {
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        String userNo = loginInfo.getUserNo();

        return "content/member/dibs_on_apartment";
    }

    // 즐겨찾기 페이지로 이동
    @GetMapping("/wishListFavorites")
    public String wishListFavorites(Model model, HttpSession session) {
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        return "content/member/wish_list_favorites";
    }

    // 닉네임 변경
    @PostMapping("/updateNickname")
    public String updateNickname(HttpSession session, String userName, RedirectAttributes rttr){
        memberService.updateNickname(userName);
        session.invalidate();
        rttr.addFlashAttribute("msg", "정보 수정이 완료되었습니다. 재 로그인 시 적용됩니다.");
        return "redirect:/member/memberInfo";
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

    // 찜하기
    @PostMapping("/like")
    public ResponseEntity<String> like(String subPropertyTypeCode, String roomCode, HttpSession session, Model model){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        if(loginInfo == null){
            return ResponseEntity.badRequest().body("회원만 가능합니다.");
        }

        String userNo = loginInfo.getUserNo();
        MemberSaveVO memberSaveVO = new MemberSaveVO();
        memberSaveVO.setRoomCode(roomCode);
        memberSaveVO.setUserNo(userNo);

        if (subPropertyTypeCode.equals("STYPE_005")) {
            memberSaveVO.setMemberSaveCode("SAVE_002");

            saveService.insertSaveRoom(memberSaveVO);
        }
        else {
            memberSaveVO.setMemberSaveCode("SAVE_001");

            saveService.insertSaveApart(memberSaveVO);
        }

        return ResponseEntity.ok().body("찜 목록에 추가되었습니다.");
    }

//    @RequestMapping("/setCookie")
//    public String setCookie(HttpServletResponse response) {
//        Cookie cookie = new Cookie("myCookie", "cookieValue");
//        response.addCookie(cookie);
//        return "Cookie is set";
//    }
//
//    @RequestMapping("/getCookie")
//    public String getCookie(@CookieValue(value = "myCookie", defaultValue = "defaultValue") String cookieValue){
//        return "Cookie value is:" + cookieValue;
//    }

}