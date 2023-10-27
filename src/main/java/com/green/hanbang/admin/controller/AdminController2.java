package com.green.hanbang.admin.controller;

import com.green.hanbang.admin.service.AdminService2;
import com.green.hanbang.admin.service.MemberManageService;
import com.green.hanbang.admin.vo.MemberManageVO;
import com.green.hanbang.member.service.MemberService;
import com.green.hanbang.member.vo.AlarmVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.FalseOfferingsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin2")
public class AdminController2 {
    private final AdminService2 adminService2;
    private final MemberManageService memberManageService;
    private final MemberService memberService;

    @GetMapping("/manage")
    public String adminManage(){
        return "admin/admin_manage222";
    }

    /////////////추가///////////////
    //허위매물신고
    @ResponseBody
    @PostMapping("/alarm")
    public Map<String, Object> adminAlarm(){
        List<FalseOfferingsVO> falseOfferingsList = adminService2.selectFalseOfferings();
        List<RealtorDetailVO> realtorDetailList = adminService2.selectRealtorAuthority();
        Map<String,Object> map = new HashMap<>();
        if(!falseOfferingsList.isEmpty()){
            map.put("falseOfferings",falseOfferingsList);
        }
        if(!realtorDetailList.isEmpty()) {
            map.put("realtorDetail",realtorDetailList);
        }
        return map;
    }

    // 공인중개사 목록 페이지
    @RequestMapping(value = "/realList")
    public String realList(Model model, MemberManageVO memberManageVO){
        List<MemberManageVO> realList = memberManageService.realList(memberManageVO);
        model.addAttribute("realList", realList );
        return "admin/real_list222";
    }

    // 공인중개사 상세 조회
    @GetMapping("/realDetail")
    public String realDetail(String identificationNum, Model model){
        MemberManageVO realDetail = memberManageService.realDetail(identificationNum);
        model.addAttribute("realDetail", realDetail);
        return "admin/real_detail222";
    }

    // 공인 중개사 승인
    @GetMapping("/updateAuthority")
    public String updateAuthority(MemberManageVO memberManageVO, AlarmVO alarmVO){
        memberManageService.updateAuthority(memberManageVO);
        /////////////추가/////////////
        //승인 요청 알림 insert
        alarmVO.setUserNo(memberManageVO.getUserNo());
        alarmVO.setAuthorityUpdate(1);
        memberService.insertAlarm(alarmVO);
        return "redirect:/admin2/realList";
    }

}
