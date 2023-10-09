package com.green.hanbang.room.controller;

import com.green.hanbang.room.service.RoomService;
import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.PropertyTypeVO;
import com.green.hanbang.room.vo.TradeTypeVO;
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
        //옵션 셀렉트
        List<OptionsVO> optionsList = roomService.selectOptions();
        //매물유형 셀렉트
        List<PropertyTypeVO> propertyList = roomService.selectProperty();
        //전월세 셀렉트
        List<TradeTypeVO> tradeTypeList = roomService.selectTradeType();
        System.out.println(optionsList);
        System.out.println(propertyList);
        model.addAttribute("optionsList", optionsList);
        model.addAttribute("propertyList", propertyList);
        model.addAttribute("tradeTypeList", tradeTypeList);
        return "room/reg_room";
    }

}
