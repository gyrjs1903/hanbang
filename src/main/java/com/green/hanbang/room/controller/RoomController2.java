package com.green.hanbang.room.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.service.RoomService2;
import com.green.hanbang.room.vo.FalseOfferingsVO;
import com.green.hanbang.room.vo.InquiryVO;
import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.RoomVO;
import jakarta.servlet.http.HttpSession;
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
        model.addAttribute("optionList",optionList);

        //모든 옵션
        List<OptionsVO> os = roomService2.selectOptions();
        model.addAttribute("allOptionList",roomService2.selectOptions());

        //매물번호 (RoomCode 마지막 숫자 4자)
        String number = room.getRoomCode().substring(room.getRoomCode().length()-4);
        model.addAttribute("number", number);

        //방 등록한 사람 login_type 조회
        String loginType = roomService2.selectLoginType(room.getUserNo());

        //등록한 사람 정보 조회
        if(Objects.equals(loginType, "USER")){
            model.addAttribute("personInfo",roomService2.selectRegUser(room.getUserNo()));
        } else if(Objects.equals(loginType, "REALTOR")){
            model.addAttribute("personInfo",roomService2.selectRegRealtor(room.getUserNo()));
        }

        //허위 매물 신고 사유
        model.addAttribute("reasonList",roomService2.selectReasonList());

        //매물 문의 제목 조회
        model.addAttribute("inquiryTitleList",roomService2.selectInquiryTitle());

        return "room/room_detail";
    }

    //옵션 값 있을 시 '있음' 표시
    @ResponseBody
    @PostMapping("/roomDetailFetch")
    public List<String> roomDetailFetch(@RequestBody Map<String, String> data){
        String options = roomService2.selectRoomInfo(data.get("roomCode")).getDetailOptions();
        List<String> optionList = Arrays.asList(options.split(","));
        return optionList;
    }

    //본인인증
    @ResponseBody
    @PostMapping("/elDAS")
    public String elDAS(@RequestBody MemberVO memberVO){
        if(roomService2.selectElDAS(memberVO) == null){
            return "null";
        }
        return roomService2.selectElDAS(memberVO);
    }

    //허위매물신고
    @PostMapping("/falseOfferings")
    public String insertFalseOfferings(FalseOfferingsVO falseOfferingsVO){
        roomService2.insertFalseOfferings(falseOfferingsVO);
        return "redirect:/room2/roomDetailInfo?roomCode=" + falseOfferingsVO.getRoomCode();
    }

    //매물문의
    @ResponseBody
    @PostMapping("/insertInquiry")
    public boolean insertInquiry(@RequestBody InquiryVO inquiryVO, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        inquiryVO.setFromUserNo(loginInfo.getUserNo());

        return roomService2.insertInquiry(inquiryVO);
    }

}
