package com.green.hanbang.member.vo;

import lombok.Data;

@Data
public class MemberInquiryVO {
    private String memberInquiryWriteNo;
    private String memberInquiryCode;
    private String memberInquiryTitle;
    private String memberInquiryDetail;
    private String memberInquiryWriteDate;
    private String userNo;
    private String inquiryStCode;

    private MemberInquiryImgVO memberInquiryImgVO;
    private MemberInquiryTypeVO memberInquiryTypeVO;
    private InquiryStatusVO inquiryStatusVO;
}
