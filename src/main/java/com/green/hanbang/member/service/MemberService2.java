package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.AlarmVO;
import com.green.hanbang.member.vo.MemberImgVO;
import com.green.hanbang.member.vo.MemberVO;

public interface MemberService2 {

    //공인중개사 승인 후 알림insert
    public int insertAlarm(AlarmVO alarmVO);

    //승인 알림 띄우기
    public int selectAuthorityAlarm(String userNo);

}
