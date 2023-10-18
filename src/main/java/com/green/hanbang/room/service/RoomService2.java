package com.green.hanbang.room.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.RoomVO;

import java.util.List;

public interface RoomService2 {

    //room테이블 디테일정보 조회
    public RoomVO selectRoomInfo(String roomCode);

    //detailOption값 조회(roomMapper)
    public List<OptionsVO> selectOptions();

    //방 등록한 사람 login_type 조회
    public String selectLoginType(String userNo);

    //방 등록한 일반회원 정보 조회
    public MemberVO selectRegUser(String userNo);

    //방 등록한 공인중개사 정보 조회
    public MemberVO selectRegRealtor(String userNo);
}
