package com.green.hanbang.item.vo;

import lombok.Data;

@Data
public class BuyVO {
    private String buyCode;
    private String itemCode;
    private String userNo;
    private int buyPrice;
    private String buyDate;
}
