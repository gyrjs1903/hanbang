package com.green.hanbang.room.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoomVO {
    private String roomCode;
    private String propertyTypeCode;
    private String subPropertyTypeCode;
    private String addr;
    private String detailAddr;
    private int roomSizeP;
    private double roomSizeM;
    private int floor;
    private String tradeTypeCode;
    private int monthlyLease;
    private int deposit;
    private int jeonseCost;
    private int maintenanceCost;
    //private String[] detailOptions;
    private String detailOptions;
    private String title;
    private String content;
    private String createDate;
    private String userNo;
    private List<RoomIMGVO> imgList;

}
