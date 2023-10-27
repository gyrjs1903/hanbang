package com.green.hanbang;

import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.AlarmVO;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.InquiryVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final MemberService memberService;

    // 시작페이지
    @GetMapping("/")
    public String main() {
        return "main/home";
    }

    @GetMapping("/2")
    public String main2(Model model, HttpSession session) {
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        int alarmCnt = 0;
        //공인중개사
        if (loginInfo.getLoginType().equals("REALTOR")) {
            if (memberService.selectAlarm(loginInfo.getUserNo()) != null) {
                //권한승인알림
                int authorityStatus = memberService.selectAuthorityAlarm(loginInfo.getUserNo());
                model.addAttribute("authorityAlarm", authorityStatus);


                //매물문의알림
                RealtorDetailVO realtor = memberService.selectInquiryAlarm(loginInfo.getUserNo());

                int realtorInquiryCnt = 0;
                for (InquiryVO inquiry : realtor.getInquiryVOList()) {
                    if (inquiry.getInquiryAnswer() == null) {
                        realtorInquiryCnt += 1;
                    }
                }
                model.addAttribute("realtorInquiryCnt", realtorInquiryCnt);

                //총 알림 개수
                if (authorityStatus == 1) alarmCnt += 1;
                if (realtorInquiryCnt != 0) alarmCnt += 1;
            }
            model.addAttribute("alarmCnt", alarmCnt);
        }
        // 일반 회원
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
            model.addAttribute("userInquiryAnswer", userInquiryAnswer);
            model.addAttribute("alarmCnt", alarmCnt);
        }
        return "main/home222";
    }

    //권한 승인 완료 알림 지우기
    @ResponseBody
    @PostMapping("/realtorAuthorityAlarm")
    public void realtorAuthorityAlarm(HttpSession session, AlarmVO alarmVO){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        alarmVO.setUserNo(loginInfo.getUserNo());
        memberService.insertAlarm(alarmVO);
    }
}
