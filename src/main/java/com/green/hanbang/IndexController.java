package com.green.hanbang;

import com.green.hanbang.admin.service.MembershipService;
import com.green.hanbang.admin.vo.MembershipVO;
import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.AlarmVO;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.InquiryVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class IndexController {
    private final MemberService memberService;
    private final MembershipService membershipService;

    // 시작페이지
    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        // 대분류 조회
        model.addAttribute("cateList", membershipService.selectCategory());

        if(session.getAttribute("loginInfo") != null){

            MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
            System.out.println(loginInfo);
            setAlarmData(loginInfo);
        }
        return "main/home";

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
                System.out.println(authorityStatus);
                System.out.println("@@@@@@@@@@@@@@@@@");

                //model.addAttribute("authorityAlarm", authorityStatus);
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
                    //model.addAttribute("realtorInquiryCnt", realtorInquiryCnt);
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
            //model.addAttribute("alarmCnt", alarmCnt);
            session.setAttribute("alarmCnt", alarmCnt);
        }
        //일반회원
        if (loginInfo.getLoginType().equals("USER")) {
            List<InquiryVO> userRoomInquiry = memberService.selectUserInquiryAlarm(loginInfo.getUserNo());
            System.out.println(userRoomInquiry);
            int userInquiryAnswer = 0;
            for (InquiryVO inquiry : userRoomInquiry) {
                if (inquiry.getInquiryAnswer() != null && inquiry.getInquiryReadCnt() == 0) {
                    userInquiryAnswer += 1;
                }
            }
            if (userInquiryAnswer != 0) alarmCnt += 1;
            //model.addAttribute("userInquiryAnswer", userInquiryAnswer);
            session.setAttribute("userInquiryAnswer", userInquiryAnswer);
            //model.addAttribute("alarmCnt", alarmCnt);
            session.setAttribute("alarmCnt", alarmCnt);
        }

    }
    //권한 승인완료 알림 지우기
    @ResponseBody
    @PostMapping("/realtorAuthorityAlarm")
    public void realtorAuthorityAlarm(HttpSession session, AlarmVO alarmVO){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        alarmVO.setUserNo(loginInfo.getUserNo());
        memberService.insertAlarm(alarmVO);
        setAlarmData(loginInfo);
    }

    ////////// 메인 페이지 하단 링크로 연결 되는 아이템 목록 ///////////////
    @GetMapping("/buyItem")
    public String buyItemList (Model model, String memCateCode){
        List<MembershipVO> buyItemList = membershipService.selectMembershipItemList(memCateCode);
        model.addAttribute("buyItemList", buyItemList);
        model.addAttribute("cateList", membershipService.selectCategory());
        return "main/buyItemList";
    }
}
