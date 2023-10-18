package com.green.hanbang.room.controller;

import com.green.hanbang.room.service.RoomService2;
import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.RoomVO;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.classgen.ReturnAdder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/room2")
@RequiredArgsConstructor
public class RoomController2 {
    private final RoomService2 roomService2;

    @GetMapping("/roomDetailInfo")
    public String roomDetailInfo(String roomCode, Model model){
        //방 모든 정보
        RoomVO room = roomService2.selectRoomInfo("ROOM_0007");
        System.out.println(room);
        model.addAttribute("roomDetail",room);

        //선택한 옵션
        String options = room.getDetailOptions();
        List<String> optionList = Arrays.asList(options.split(","));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(optionList);
        model.addAttribute("optionList",optionList);

        //모든 옵션
        List<OptionsVO> os = roomService2.selectOptions();
        System.out.println(os);
        model.addAttribute("allOptionList",roomService2.selectOptions());

        //매물번호 (RoomCode 마지막 숫자 4자)
        String number = room.getRoomCode().substring(room.getRoomCode().length()-4);
        model.addAttribute("number", number);

        //방 등록한 사람 login_type 조회
        String loginType = roomService2.selectLoginType(room.getUserNo());

        //등록한 사람 정보 조회
        if(Objects.equals(loginType, "USER")){
            System.out.println(roomService2.selectRegUser(room.getUserNo()));
            model.addAttribute("personInfo",roomService2.selectRegUser(room.getUserNo()));
        } else if(Objects.equals(loginType, "REALTOR")){
            System.out.println(roomService2.selectRegRealtor(room.getUserNo()));
            model.addAttribute("personInfo",roomService2.selectRegRealtor(room.getUserNo()));
        }
        return "room/room_detail";
    }

    @ResponseBody
    @PostMapping("/roomDetailFetch")
    public List<String> roomDetailFetch(){
        String options = roomService2.selectRoomInfo("ROOM_0007").getDetailOptions();
        List<String> optionList = Arrays.asList(options.split(","));
        return optionList;
    }
}
