package com.green.hanbang.room.vo;

import com.green.hanbang.member.vo.InquiryStatusVO;
import lombok.Data;

@Data
public class FalseOfferingsVO {
    private String falseOfferingsCode;
    private String roomCode;
    private String userNo;
    private String reasonCode;
    private String reportContent;
    private int reportCnt;
    private String reportDate;
    private RoomVO roomVO;
    private String inquiryStCode;
    private InquiryStatusVO inquiryStatusVO;
}
