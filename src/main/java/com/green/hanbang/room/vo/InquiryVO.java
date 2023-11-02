package com.green.hanbang.room.vo;

import com.green.hanbang.realtor.vo.PageVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class InquiryVO extends PageVO {
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
