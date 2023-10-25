package com.green.hanbang;

import com.green.hanbang.member.service.MemberService2;
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


@Controller
@RequiredArgsConstructor
public class IndexController {
    private final MemberService2 memberService2;
    // 시작페이지
    @GetMapping("/")
    public String main(){
        return "main/home";
    }
    //카피
    ////////////////////////////////////////////
    @GetMapping("/2")
    public String main2(Model model, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        int alarmCnt = 0;
        if(loginInfo.getLoginType().equals("REALTOR")){
            //권한승인알림
            int authorityStatus = memberService2.selectAuthorityAlarm(loginInfo.getUserNo());
            model.addAttribute("authorityAlarm",authorityStatus);
            RealtorDetailVO realtor = memberService2.selectInquiryAlarm(loginInfo.getUserNo());

            //매물문의알림
            int realtorInquiryCnt = 0;
            for(InquiryVO inquiry : realtor.getInquiryList()){
                if(inquiry.getInquiryAnswer() == null){
                    realtorInquiryCnt += 1;
                }
            }
            model.addAttribute("realtorInquiryCnt",realtorInquiryCnt);

            //총 알림 개수
            if(authorityStatus == 1)alarmCnt += 1;
            if(realtorInquiryCnt != 0)alarmCnt += 1;
            System.out.println(alarmCnt);
            model.addAttribute("alarmCnt",alarmCnt);
        }
        return "main/home222";
    }

    @ResponseBody
    @PostMapping("/realtorAuthorityAlarm")
    public void realtorAuthorityAlarm(HttpSession session, AlarmVO alarmVO){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        alarmVO.setUserNo(loginInfo.getUserNo());
        memberService2.insertAlarm(alarmVO);
    }
}