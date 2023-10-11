package com.green.hanbang.realtor.vo;

import lombok.Data;

@Data
public class RealtorDetailVO {
    private String realtorCode;
    private String userNo;
    private String officeName;
    private String identificationNum;
    private String licenseFileName;
    private String authority;
    private String requestDate;
    private LicenseImgVO licenseImgVO;
}
