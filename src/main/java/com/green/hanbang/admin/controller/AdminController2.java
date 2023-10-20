package com.green.hanbang.admin.controller;

import com.green.hanbang.admin.service.AdminService2;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.FalseOfferingsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin2")
public class AdminController2 {
    private final AdminService2 adminService2;

    @GetMapping("/manage")
    public String adminManage(){
        return "admin/admin_manage2";
    }

    @ResponseBody
    @PostMapping("/alarm")
    public Map<String, Object> adminAlarm(){
        List<FalseOfferingsVO> falseOfferingsList = adminService2.selectFalseOfferings();
        List<RealtorDetailVO> realtorDetailList = adminService2.selectRealtorAuthority();
        Map<String,Object> map = new HashMap<>();
        if(falseOfferingsList != null){
            map.put("falseOfferings",falseOfferingsList);
        }
        if(realtorDetailList != null) {
            map.put("realtorDetail",realtorDetailList);
        }
        return map;
    }
}
