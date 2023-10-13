package com.green.hanbang.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class MembershipVO {
    private String memCateName;
    private String memCateCode;
    private String membershipCode;
    private String membershipName;
    private String membershipContent;
    private int membershipPrice;
    private List<MemItemVO> memItemList;

}
