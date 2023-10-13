package com.green.hanbang.room.service;

import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.RoomVO;

import java.util.List;

public interface RoomService2 {

    //room테이블 디테일정보 조회
    public RoomVO selectRoomInfo(String roomCode);

    //detailOption값 조회(roomMapper)
    public List<OptionsVO> selectOptions();
}
