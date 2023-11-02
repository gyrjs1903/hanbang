package com.green.hanbang.member.vo;

import com.green.hanbang.room.vo.RoomVO;
import lombok.Data;


@Data
public class MemberSaveVO {
    private String memberSaveNo;
    private String roomCode;
    private String userNo;
    private String memberSaveCode;
    private MemberSaveTypeVO memberSaveTypeVO;
    private RoomVO roomVO;
}
