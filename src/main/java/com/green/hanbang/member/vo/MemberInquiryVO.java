package com.green.hanbang.member.vo;

import lombok.Data;

@Data
public class MemberInquiryVO {
    private String memberInquiryWriteNo;
    private String memberInquiryCode;
    private String memberInquiryTitle;
    private String memberInquiryDetail;
    private String memberWriteDate;
    private String userNo;

    private MemberInquiryImgVO memberInquiryImgVO;
    private MemberInquiryTypeVO memberInquiryTypeVO;

}
