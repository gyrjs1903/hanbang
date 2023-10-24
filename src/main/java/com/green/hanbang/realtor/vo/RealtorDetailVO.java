package com.green.hanbang.realtor.vo;

import com.green.hanbang.room.vo.InquiryVO;
import lombok.Data;

import java.util.List;

@Data
public class RealtorDetailVO {
    private String realtorCode;
    private String userNo;
    private String officeName;
    private String identificationNum;
    private String licenseFileName;
    private String authority;
    private String requestDate;
    private String realtorName;
    private LicenseImgVO licenseImgVO;
    private List<InquiryVO> inquiryVOList;
}
