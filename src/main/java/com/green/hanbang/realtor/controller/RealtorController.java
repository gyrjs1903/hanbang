package com.green.hanbang.realtor.controller;

import com.green.hanbang.realtor.service.RealtorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RealtorController {
    private final RealtorService realtorService;
}
