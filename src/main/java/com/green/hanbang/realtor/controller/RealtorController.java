package com.green.hanbang.realtor.controller;

import com.green.hanbang.realtor.service.RealtorService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/realtor")
public class RealtorController {
    private final RealtorService realtorService;

    @RequestMapping("/main")
    public String realtor(){
        return "realtor/realtor_layout";
    }

    @GetMapping("/myPage")
    public String myPage(Model model){
        model.addAttribute("realtorInfo",realtorService.selectRealtorMyPage());
        return "realtor/realtor_mypage";
    }

}
