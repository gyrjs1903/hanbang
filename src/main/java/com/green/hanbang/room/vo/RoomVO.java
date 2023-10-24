package com.green.hanbang.room.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoomVO {
    private String roomCode;
    private String propertyTypeCode;
    private String subPropertyTypeCode;
    private RoomAddrVO roomAddrVO;
    private int roomSizeP;
    private double roomSizeM;
    private int floor;
    private String tradeTypeCode;
    private String monthlyLease;
    private String deposit;
    private String jeonseCost;
    private String maintenanceCost;
    //private String[] detailOptions;
    private String detailOptions;
    private String title;
    private String content;
    private String createDate;
    private String immediateOccupancy;
    private String availableMoveInDate;
    private String userNo;
    private List<RoomIMGVO> imgList;

}
