package com.green.hanbang.item.vo;

import lombok.Data;

@Data
public class PackageItemVO {
    private String buyDetailCode;
    private String buyCode;
    private String userNo;
    private String startDate;
    private String endDate;
    private int generalProductCnt;
    private int plusSeasonCnt;
    private int plusDayCnt;
    private String isValid;
}
