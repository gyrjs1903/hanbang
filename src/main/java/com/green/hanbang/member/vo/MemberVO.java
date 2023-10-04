package com.green.hanbang.member.vo;

import lombok.Data;

@Data
public class MemberVO {
    private String id;
    private String passWord;
    private String passWordCheck;
    private String nickName;
    private String phone;
    private String profileImage;
    private String roll;
}
