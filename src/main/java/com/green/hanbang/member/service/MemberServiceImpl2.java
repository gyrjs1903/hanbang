package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.AlarmVO;
import com.green.hanbang.member.vo.MemberImgVO;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl2 implements MemberService2 {
    private final SqlSessionTemplate sqlSession;

    //승인 알림 insert
    @Override
    public int insertAlarm(AlarmVO alarmVO) {
        return sqlSession.insert("member2Mapper.insertAlarm",alarmVO);
    }

    //승인 알림 띄우기
    @Override
    public int selectAuthorityAlarm(String userNo) {
        return sqlSession.selectOne("member2Mapper.selectAuthorityAlarm",userNo);
    }

    @Override
    public List<InquiryVO> selectInquiryAlarm(String userNo) {
        return sqlSession.selectList("member2Mapper.selectInquiryAlarm",userNo);
    }
}
