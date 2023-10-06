package com.green.hanbang.admin.controller;

import com.green.hanbang.admin.service.MemberManageService;
import com.green.hanbang.admin.vo.MemberManageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberManageService memberManageService;

    // 회원 목록 페이지
    @GetMapping("/userList")
    public String userList(Model model){
        List<MemberManageVO> userList = memberManageService.userList();
        model.addAttribute("userList", userList );
        return "admin/user_list";
    }

    // 공인중개사 목록 페이지
    @GetMapping("/realList")
    public String realList(Model model){
        List<MemberManageVO> realList = memberManageService.realList();
        model.addAttribute("realList", realList );
        return "admin/real_list";
    }

}
