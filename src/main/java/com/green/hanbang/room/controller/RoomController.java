package com.green.hanbang.room.controller;

import com.green.hanbang.room.service.RoomService;
import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.PropertyTypeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/reg")
    public String regRoom(Model model){
        List<OptionsVO> optionsList = roomService.selectOptions();
        List<PropertyTypeVO> propertyList = roomService.selectProperty();
        System.out.println(optionsList);
        System.out.println(propertyList);
        model.addAttribute("optionsList", optionsList);
        model.addAttribute("propertyList", propertyList);
        return "room/reg_room";
    }

}
