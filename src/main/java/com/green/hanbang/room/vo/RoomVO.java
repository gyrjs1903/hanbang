package com.green.hanbang.room.vo;

import com.green.hanbang.admin.vo.MemItemVO;
import com.green.hanbang.item.vo.BuyVO;
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
    private String detailOptions;
    private String title;
    private String content;
    private String createDate;
    private String immediateOccupancy;
    private String availableMoveInDate;
    private String userNo;
    private List<RoomIMGVO> imgList;
    private PropertyTypeVO propertyTypeVO;
    private ApplyItemVO applyItemVO;
    private MemItemVO memItemVO;
    private BuyVO buyVO;
    private String memType;
    private SubPropertyTypeVO subPropertyTypeVO;

}
