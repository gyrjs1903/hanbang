package com.green.hanbang.realtor.controller;

import com.green.hanbang.realtor.service.RealtorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
}
