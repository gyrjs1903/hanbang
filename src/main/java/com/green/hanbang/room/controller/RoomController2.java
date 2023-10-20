package com.green.hanbang.room.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.service.RoomService2;
import com.green.hanbang.room.vo.FalseOfferingsVO;
import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.RoomVO;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.classgen.ReturnAdder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/room2")
@RequiredArgsConstructor
public class RoomController2 {
    private final RoomService2 roomService2;

    @GetMapping("/roomDetailInfo")
    public String roomDetailInfo(String roomCode, Model model){
        //방 모든 정보
        RoomVO room = roomService2.selectRoomInfo(roomCode);
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

        //허위 매물 신고 사유
        model.addAttribute("reasonList",roomService2.selectReasonList());
        return "room/room_detail";
    }

    //옵션 값 있을 시 '있음' 표시
    @ResponseBody
    @PostMapping("/roomDetailFetch")
    public List<String> roomDetailFetch(){
        String options = roomService2.selectRoomInfo("ROOM_0001").getDetailOptions();
        List<String> optionList = Arrays.asList(options.split(","));
        return optionList;
    }

    //본인인증
    @ResponseBody
    @PostMapping("/elDAS")
    public String elDAS(@RequestBody MemberVO memberVO){
        System.out.println(memberVO);
        return roomService2.selectElDAS(memberVO);
    }

    //허위매물신고
    @PostMapping("/falseOfferings")
    public String insertFalseOfferings(FalseOfferingsVO falseOfferingsVO){
        System.out.println(falseOfferingsVO);
        roomService2.insertFalseOfferings(falseOfferingsVO);
        return "redirect:/room2/roomDetailInfo";
    }
}
