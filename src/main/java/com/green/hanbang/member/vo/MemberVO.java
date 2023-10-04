package com.green.hanbang.member.vo;

import lombok.Data;

@Data
public class MemberVO {
    private String userNo;
    private String userName;
    private String passWord;
    private String loginType;
    private String phone;
    private String nickName;
    private String profileImage;
    private String joinDate;
}
