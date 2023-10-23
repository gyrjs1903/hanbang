package com.green.hanbang.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class MemCateVO {
    private String memCateCode;
    private String memCateName;
    private String membershipCode;
    private String itemCode;
    private List<MembershipVO> membershipList;

}
