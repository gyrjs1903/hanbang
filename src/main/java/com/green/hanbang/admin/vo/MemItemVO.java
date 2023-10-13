package com.green.hanbang.admin.vo;

import lombok.Data;

@Data
public class MemItemVO {
    private String itemCode;
    private String itemName;
    private int itemPrice;
    private String itemIntro;
    private String itemContent;
    private String membershipCode;
    private String memCateCode;
}
