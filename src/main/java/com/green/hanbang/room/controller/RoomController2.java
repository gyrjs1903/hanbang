package com.green.hanbang.room.controller;

import com.green.hanbang.room.service.RoomService2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room2")
@RequiredArgsConstructor
public class RoomController2 {
    private final RoomService2 roomService2;
}
