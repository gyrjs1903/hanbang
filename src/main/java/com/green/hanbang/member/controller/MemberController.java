package com.green.hanbang.member.controller;

import com.green.hanbang.member.service.MemberInquiryService;
import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.service.SaveService;
import com.green.hanbang.member.vo.*;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.service.RoomService;
import com.green.hanbang.room.vo.*;
import com.green.hanbang.util.MemberInquiryUtil;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Array;
import java.util.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberInquiryService memberInquiryService;
    private final RoomService roomService;
    private final SaveService saveService;

    // -------- 페이지 이동만 하는 Controller --------------
    // 회원 가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm() {
        return "content/member/join";
    }

    // 로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(MemberVO memberVO) {
        return "content/member/login";
    }

    // 개인 정보 동의 상세 보기 페이지 새 탭으로 열기
    @GetMapping("/consent")
    public String consent(){
        return "content/member/userinfo_consent";
    }

    // -------- 중복 검사 --------------
    // 이메일(아이디) 중복 확인 및 유효성 검사
    @ResponseBody
    @PostMapping("/userNameDuplicationCheckFetch")
    public String userNameDuplicationCheckFetch(@RequestParam String userName){
        return memberService.userNameCheck(userName);
    }

    // 기존 비밀 번호 체크
    @ResponseBody
    @PostMapping("/changeForPassWordDuplicationCheckFetch")
    public String changeForPassWordDuplicationCheckFetch(@RequestParam String oldPassWord){
        return memberService.passWordCheck(oldPassWord);
    }

    // -------- 회원 기본 기능 --------------
    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  //모든 session 정보 삭제
        return "redirect:/";
    }

    // 닉네임 변경
    @PostMapping("/updateNickname")
    public String updateNickname(@RequestParam String nickName, HttpSession session, MemberVO memberVO){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String userNo = loginInfo.getUserNo();
        memberVO.setUserNo(userNo);
        memberVO.setNickName(nickName);
        memberService.updateNickname(memberVO);
        return "redirect:/member/memberInfo";
    }

    // 비밀 번호 변경
    @PostMapping("/updatePassWord")
    public String updatePassWord(@RequestParam String passWord, HttpSession session, MemberVO memberVO){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String userNo = loginInfo.getUserNo();
        memberVO.setUserNo(userNo);
        memberVO.setPassWord(passWord);

        memberService.updatePassword(memberVO);
        return "redirect:/member/memberInfo";
    }

    // 회원 탈퇴
    @GetMapping("/deleteMember")
    public String deleteMember(){

        return "redirect:/member/logout";
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

    // 회원 가입
    @PostMapping("/join")
    public String join(MemberVO memberVO, MemberImgVO memberImgVO) {
        // 회원 가입 (회원 코드 조회를 위해 선실행)
        memberService.join(memberVO);

        String userNo = memberService.selectNextUserNo();

        memberImgVO.setUserNo(userNo);
        memberImgVO.setProfileImgName("img/member/profileImg/default_profile_image.png");
        memberImgVO.setAttachedProfileImgName("img/member/profileImg/default_profile_image.png");

        // 프로필 이미지 등록
        memberService.insertProfile(memberImgVO);
        String userName = memberVO.getUserName();

        return "redirect:/member/loginForm?userName=" + userName;
    }

    // -------- 마이 페이지 관련 기능 --------------
    // 내 정보 페이지로 이동
    @GetMapping("/memberInfo")
    public String memberInfo(Model model, HttpSession session) {
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        if (loginInfo != null) {
            String userNo = loginInfo.getUserNo();
            String memberInfo = memberService.selectUserNo(userNo);
            model.addAttribute("memberInfo", memberInfo);

            return "content/member/user_info";
        } else {
            return "redirect:/member/loginForm";
        }
    }

    // 프로필 이미지 수정
    @PostMapping("/updateProfile")
    public String updateProfile(MultipartFile img, HttpSession session) {

        // 유저 번호 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String userNo = loginInfo.getUserNo();

        // 단일 첨부 파일 업로드
        MemberImgVO uploadedMemberImg = MemberUtil.MemberUploadFile(img);
        uploadedMemberImg.setUserNo(userNo); // 파일 업로드 후 유저 번호 설정

        // DB에 프로필 이미지 정보 등록
        memberService.updateProfile(uploadedMemberImg);

        return "redirect:/member/memberInfo";
    }


    //답글 읽을 시 readCnt 증가
    @ResponseBody
    @PostMapping("/readInquiryAnswer")
    public void readInquiryAnswer(@RequestBody InquiryVO inquiryVO,HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        memberService.updateInquiryReadCnt(inquiryVO.getInquiryCode());
        setAlarmData(loginInfo);
    }

    //다른페이지에서 알림창 확인 메소드
    public void setAlarmData(MemberVO loginInfo){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpSession session = request.getSession();
        int alarmCnt = 0;
        //공인중개사
        if (loginInfo.getLoginType().equals("REALTOR")) {
            if (memberService.selectAlarm(loginInfo.getUserNo()) != null) {
                // 권한승인알림
                Integer authorityStatus = memberService.selectAuthorityAlarm(loginInfo.getUserNo());

                session.setAttribute("authorityAlarm", authorityStatus);

                //매물문의알림
                RealtorDetailVO realtor = memberService.selectInquiryAlarm(loginInfo.getUserNo());
                int realtorInquiryCnt = 0;
                if(realtor != null){
                    for (InquiryVO inquiry : realtor.getInquiryList()) {
                        if (inquiry.getInquiryAnswer() == null) {
                            realtorInquiryCnt += 1;
                        }
                    }
                    session.setAttribute("realtorInquiryCnt", realtorInquiryCnt);
                }
                //총 알림 개수
                if (authorityStatus == 1) alarmCnt += 1;
                if (realtorInquiryCnt != 0) alarmCnt += 1;
            } else {
                // 권한 승인 안 되어있을시
                Integer authorityStatus = memberService.selectAuthorityAlarm(loginInfo.getUserNo());
                session.setAttribute("authorityAlarm", authorityStatus);
            }
            session.setAttribute("alarmCnt", alarmCnt);
        }
        //일반회원
        if (loginInfo.getLoginType().equals("USER")) {
            List<InquiryVO> userRoomInquiry = memberService.selectUserInquiryAlarm(loginInfo.getUserNo());
            int userInquiryAnswer = 0;
            for (InquiryVO inquiry : userRoomInquiry) {
                if (inquiry.getInquiryAnswer() != null && inquiry.getInquiryReadCnt() == 0) {
                    userInquiryAnswer += 1;
                }
            }
            if (userInquiryAnswer != 0) alarmCnt += 1;
            session.setAttribute("userInquiryAnswer", userInquiryAnswer);
            session.setAttribute("alarmCnt", alarmCnt);
        }

    }

    // -------- 허위 매물 신고 관련 --------------
    // 허위 매물 신고 내역 페이지 이동
    @GetMapping("/memberReport")
    public String memberReport(HttpSession session,Model model) {
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        if (loginInfo == null) {
            return "redirect:/member/loginForm";
        } else {
            System.out.println(memberService.selectFalseOfferingsList(loginInfo.getUserNo()));
            model.addAttribute("falseOfferingsList",memberService.selectFalseOfferingsList(loginInfo.getUserNo()));
            return "content/member/user_report"; // 문의 유형 목록
        }
    }
    // -------- 1:1문의 관련 --------------
    // 1:1문의 페이지 이동
    @GetMapping("/memberInquiry")
    public String memberInquiry(Model model, HttpSession session) {
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        if (loginInfo == null) {
            return "redirect:/member/loginForm";
        } else {
            String userNum = loginInfo.getUserNo();

            // MemberInquiryVO에 userNo를 설정
            MemberInquiryVO memberInquiryVO = new MemberInquiryVO();
            memberInquiryVO.setUserNo(userNum);

            // 문의 리스트 목록
            List<MemberInquiryVO> memberInquiryList = memberInquiryService.selectMemberInquiryList(memberInquiryVO);

            model.addAttribute("memberInquiryList", memberInquiryList);
            return "content/member/user_inquiry";
        }
    }

    // 1:1문의 작성 페이지 이동
    @GetMapping("/memberInquiryWrite")
    public String memberInquiryWrite(Model model, MemberInquiryTypeVO memberInquiryTypeVO, HttpSession session){
        model.addAttribute("memberInquiryTypeList", memberInquiryService.selectMemberInquiryTypeList(memberInquiryTypeVO));  // 문의 유형 목록 조회
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        if (loginInfo == null) {
            return "redirect:/member/loginForm";
        } else {
            return "content/member/user_inquiry_write";
        }
    }

    // 1:1문의 전송 (작성 후 문의 버튼 클릭)
    @PostMapping("/memberInquirySave")
    public String memberInquirySave(HttpSession session, MultipartFile[] imgs, MultipartFile img, MemberInquiryVO memberInquiryVO){

        // 로그인 한 회원 번호 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String userNo = loginInfo.getUserNo();
        memberInquiryVO.setUserNo(userNo);

        memberInquiryService.insertMemberInquiry(memberInquiryVO);
        System.out.println(memberInquiryVO);

        String memberInquiryWriteNo = memberInquiryVO.getMemberInquiryWriteNo();

        // 첨부 파일 (단일)

        // 단일 첨부 파일 업로드
        MemberInquiryImgVO uploadedMemberInquiryImg = MemberInquiryUtil.inquiryUploadFile(img);
        uploadedMemberInquiryImg.setMemberInquiryWriteNo(memberInquiryWriteNo); // 파일 업로드 후 유저 번호 설정

        // DB에 문의 이미지 정보 등록
        memberInquiryService.insertMemberInquiryImg(uploadedMemberInquiryImg);

//        // 첨부 파일 (여러개)
//        List<MemberInquiryImgVO> inqImgList = MemberInquiryUtil.inquiryMultiUpload(imgs);
//        System.out.println(imgs);
//        for (MemberInquiryImgVO inquiryImgVO : inqImgList){
//            String memberInquiryWriteNo = memberInquiryService.selectNextInquiryNumber();
//            inquiryImgVO.setMemberInquiryWriteNo(memberInquiryWriteNo);
//        }
//
//
//        memberInquiryService.insertMemberInquiryImg((MemberInquiryImgVO) inqImgList);

        return "redirect:/member/memberInquiry";
    }

    // 1:1 문의 상세 페이지 이동
    @GetMapping("/memberInquiryDetail")
    public String memberInquiryDetail(Model model, MemberInquiryVO memberInquiryVO, HttpSession session){

        // 문의 리스트 목록
        List<MemberInquiryVO> memberInquiryList = memberInquiryService.selectInquiryDetail(memberInquiryVO);



        model.addAttribute("memberInquiryList", memberInquiryList);

        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        if (loginInfo == null) {
            return "redirect:/member/loginForm";
        } else {
            return "content/member/user_inquiry_detail";
        }
    }
    // -------- 매물 문의 (알림) 관련 --------------
    // 알림 페이지 이동 (공인중개사 <-> 회원)
    @GetMapping("/memberCall")
    public String memberCall(HttpSession session, Model model) {
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<InquiryVO> roomInquiryList = memberService.selectUserInquiryAlarm(loginInfo.getUserNo());

        for(InquiryVO inquiry: roomInquiryList){
            inquiry.getInquiryStatusVO()
                    .setStatusName(memberInquiryService.selectStatus(inquiry.getInquiryStCode()).getStatusName());
        }

        System.out.println(roomInquiryList);
        model.addAttribute("roomInquiryList",roomInquiryList);
        return "content/member/user_call";
    }

    // -------- 찜목록 관련 --------------
    // 찜하기 기능
    @PostMapping("/like")
    public ResponseEntity<String> like(String subPropertyTypeCode, String roomCode, HttpSession session){
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

    // 찜 취소 기능
    @PostMapping("/unLike")
    public ResponseEntity<String> unLike(String subPropertyTypeCode, String roomCode, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        if(loginInfo == null){
            return ResponseEntity.badRequest().body("회원만 가능합니다.");
        }

        String userNo = loginInfo.getUserNo();
        MemberSaveVO memberSaveVO = new MemberSaveVO();
        memberSaveVO.setRoomCode(roomCode);
        memberSaveVO.setUserNo(userNo);

        // 이미 찜한 매물 인지 확인
        boolean isAlreadySaved = saveService.checkIfSaved(memberSaveVO);

        if (isAlreadySaved) {
            saveService.deleteSavedItem(memberSaveVO);
        }
        return ResponseEntity.ok().body("찜이 취소되었습니다.");
    }

    // 찜 상태 확인 기능
//    @GetMapping("/dibOnStatus")
//    public ResponseEntity<String> check(String subPropertyTypeCode, String roomCode, HttpSession session){
//
//    }


    // 쿠키에 있는 데이터 배열로 받기
    private String[] getCookieValue(HttpServletRequest request, String name){
        Cookie[] cookieBox = request.getCookies();
        String[] result = null;

        for(Cookie c : cookieBox){
            if(c.getName().equals(name)){
                result = c.getValue().split("_");
            }
        }

        return result;
    }

    // 헤더에 찜목록 클릭 시 최근 본 방 페이지 이동
    @GetMapping("/dibsOn")
    public String dibsOn(HttpServletRequest request, Model model) {

        Cookie[] cookieBox = request.getCookies();

        String[] recentViewTitleArr = getCookieValue(request, "title");
        String[] recentViewImg = getCookieValue(request, "isMain");
        String[] recentViewPropertyTypeArr = getCookieValue(request, "propertyTypeName");
        String[] recentViewTradeTypeArr = getCookieValue(request, "tradeTypeName");
        String[] recentViewDepositArr = getCookieValue(request, "deposit");
        String[] recentViewMonthlyLeaseArr = getCookieValue(request, "monthlyLease");
        String[] recentViewFloorArr = getCookieValue(request, "floor");
        String[] recentViewRoomSizeMArr = getCookieValue(request, "roomSizeM");
        String[] recentViewMaintenanceCostArr = getCookieValue(request, "maintenanceCost");

        System.out.println(Arrays.toString(recentViewTitleArr));
        System.out.println(Arrays.toString(recentViewImg));
        System.out.println(Arrays.toString(recentViewPropertyTypeArr));
        System.out.println(Arrays.toString(recentViewTradeTypeArr));
        System.out.println(Arrays.toString(recentViewDepositArr));
        System.out.println(Arrays.toString(recentViewMonthlyLeaseArr));
        System.out.println(Arrays.toString(recentViewFloorArr));
        System.out.println(Arrays.toString(recentViewRoomSizeMArr));
        System.out.println(Arrays.toString(recentViewMaintenanceCostArr));

        List<RoomVO> recentViewList = new ArrayList<>();

        // 쿠키 정보의 개수만큼 반복해서 리스트에 저장
        for(int i = 0 ; i < recentViewTitleArr.length ; i++){
            RoomVO vo = new RoomVO();
            // title cookie
            vo.setTitle(recentViewTitleArr[i]);
            // img cookie
//            List<RoomIMGVO> imgList = new ArrayList<>();
//            RoomIMGVO imgVO = new RoomIMGVO();
////            String fileName = recentViewImg[i];
////            int maxLength = 1;
////            String trimmedFileName = fileName.substring(0, Math.min(fileName.length(), maxLength));
////            imgVO.setAttachedFileName(trimmedFileName);
//            imgVO.setAttachedFileName(recentViewImg[i]);
//            imgList.add(imgVO);
//            vo.setImgList(imgList);

            // deposit cookie
            vo.setDeposit(recentViewDepositArr[i]);
            // monthlyLease cookie
            vo.setMonthlyLease(recentViewMonthlyLeaseArr[i]);
//            // propertyTypeName cookie
//            List<PropertyTypeVO> propertyList = new ArrayList<>();
//            PropertyTypeVO propertyVO = new PropertyTypeVO();
//            propertyVO.setPropertyTypeName(recentViewPropertyTypeArr[i]);
//            propertyList.add(propertyVO);
//            vo.setPropertyTypeVO((PropertyTypeVO) propertyList);
//            // tradeType cookie
//            List<TradeTypeVO> tradeList = new ArrayList<>();
//            TradeTypeVO tradeVO = new TradeTypeVO();
//            tradeVO.setTradeTypeName(recentViewTradeTypeArr[i]);
//            tradeList.add(tradeVO);
//            vo.setTradeTypeCode(tradeList.toString());

            // floor cookie
            // roomSizeM cookie
            // maintenanceCost cookie
            //vo.setMaintenanceCost(recentViewMaintenanceCostArr[i]);

            recentViewList.add(vo);
        }


        for(RoomVO e  : recentViewList){
            System.out.println(e);
        }

        model.addAttribute("recentViewList", recentViewList);

        return "content/member/recent_viewed_room";
    }

    // 찜한 방 페이지 이동
    @GetMapping("/dibsOnRoom")
    public String dibsOnRoom(HttpSession session, String roomCode, Model model) {
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        String userNo = loginInfo.getUserNo();
        model.addAttribute("userNo", userNo);

        List<MemberSaveVO> save = saveService.selectSaveRoomList(userNo);
        model.addAttribute("saveList", save);

        return "content/member/dibs_on_room";
    }

    // 최근 본 단지 페이지 이동
    @GetMapping("/recentViewedApartment")
    public String recentViewedApartment(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        return "content/member/recent_viewed_apartment";
    }

    // 찜한 단지 페이지 이동
    @GetMapping("/dibsOnApartment")
    public String dibsOnApartment(HttpSession session) {
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        String userNo = loginInfo.getUserNo();

        return "content/member/dibs_on_apartment";
    }

    // 즐겨 찾기 페이지 이동
//    @GetMapping("/wishListFavorites")
//    public String wishListFavorites(Model model, HttpSession session) {
//        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
//        if (loginInfo == null) {
//            return "redirect:/member/loginForm";
//        } else{
//            return "content/member/wish_list_favorites";
//        }
//    }

}