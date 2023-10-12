package com.green.hanbang.room.controller;

import com.green.hanbang.room.service.RoomService2;
import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.RoomVO;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/room2")
@RequiredArgsConstructor
public class RoomController2 {
    private final RoomService2 roomService2;

    @GetMapping("/roomDetailInfo")
    public String roomDetailInfo(String roomCode, Model model){
        //선택한 옵션
        String options = roomService2.selectRoomInfo("ROOM_0003").getDetailOptions();
        List<String> optionList = Arrays.asList(options.split(","));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(optionList);
        model.addAttribute("optionList",optionList);

        //모든 옵션
        List<OptionsVO> os = roomService2.selectOptions();
        System.out.println(os);
        model.addAttribute("allOptionList",roomService2.selectOptions());
//        System.out.println(roomService2.selectRoomInfo("ROOM_0003"));

        //방 모든 정보
        model.addAttribute("roomDetail",roomService2.selectRoomInfo("ROOM_0003"));
        return "room/room_detail";
    }

}
