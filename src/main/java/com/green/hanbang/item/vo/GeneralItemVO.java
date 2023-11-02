package com.green.hanbang.item.vo;

import lombok.Data;

@Data
public class GeneralItemVO {
    private String generalCode;
    private String buyCode;
    private String itemCode;
    private String userNo;
    private int generalProductCnt;
    private String startDate;
    private String endDate;
    private String isValid;
    private String memCateCode;
    private String memCateName;
}
