package com.green.hanbang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // 시작페이지
    @GetMapping("/")
    public String main(){
        System.out.println(":)");
        System.out.println("환영합니다~");
        return "index";
    }
}
