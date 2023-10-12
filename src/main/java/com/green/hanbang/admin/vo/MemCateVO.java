package com.green.hanbang.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class MemCateVO {
    private String memCateCode;
    private String memCateName;
    private List<MembershipVO> membershipList;
}
