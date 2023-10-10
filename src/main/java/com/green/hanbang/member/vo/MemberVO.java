package com.green.hanbang.member.vo;

import com.green.hanbang.realtor.vo.RealtorDetailVO;
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
    private RealtorDetailVO realtorDetailVO;

    /* 프로필 사진 */
    private String profileImgNO;
    private String profileImgName;
    private String attachedProfileImgName;
}
