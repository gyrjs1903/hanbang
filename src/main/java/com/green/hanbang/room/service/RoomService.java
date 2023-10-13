package com.green.hanbang.room.service;


import com.green.hanbang.room.vo.*;

import java.util.List;

public interface RoomService {
    //option select
    public List<OptionsVO> selectOptions();
    //select property
    public List<PropertyTypeVO> selectProperty();
    //전월세 유형 셀렉트
    public  List<TradeTypeVO> selectTradeType();
    //방코드 셀렉트
    public String selectNextRoomCode();
    //매물 인설트
    public void insertRoom(RoomVO roomVO);

}
