package com.green.hanbang.room.vo;

import lombok.Data;

@Data
public class InquiryVO {
    private String inquiryCode;
    private String fromUserNo;
    private String toUserNo;
    private String roomCode;
    private String inquiryTitleCode;
    private String inquiryContent;
    private String inquiryAnswer;
    private int inquiryReadCnt;
    private String inquiryDate;
    private InquiryTitleVO inquiryTitleVO;
}
