package com.green.hanbang.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class EventVO {
    private String eventCode;
    private String eventTitle;
    private String eventContent;
    private String eventRegDate;
    private int eventReadCnt;
    private List<EventImgVO> eventImgList;
}
