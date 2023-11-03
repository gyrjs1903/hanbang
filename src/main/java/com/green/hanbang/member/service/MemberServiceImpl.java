package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.AlarmVO;
import com.green.hanbang.member.vo.MemberImgVO;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public int join(MemberVO memberVO) {
        return sqlSession.insert("memberMapper.join", memberVO);
    }

    @Override
    public String selectNextUserNo() {
        return sqlSession.selectOne("memberMapper.selectNextUserNo");
    }

    @Override
    public void insertProfile(MemberImgVO memberImgVO) {
        sqlSession.insert("memberMapper.insertProImg", memberImgVO);
    }

    @Override
    public void updateProfile(MemberImgVO memberImgVO) {
        sqlSession.update("memberMapper.updateProfile", memberImgVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }

    @Override
    public String userNameCheck(String userName) {
        return sqlSession.selectOne("memberMapper.userNameCheck", userName);
    }

    @Override
    public String passWordCheck(String passWord) {
        return sqlSession.selectOne("memberMapper.passWordCheck", passWord);
    }

    @Override
    public String selectUserNo(String userNo) {
        return sqlSession.selectOne("memberMapper.selectUserNo", userNo);
    }

    @Override
    public String selectUserInfo(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.selectUserInfo", memberVO);
    }

    @Override
    public void updateNickname(String userName) {
        sqlSession.update("memberMapper.updateNickName", userName);
    }

    @Override
    public int updatePassWord(MemberVO memberVO) {
        return sqlSession.update("memberMapper.updatePassWord", memberVO);
    }

    @Override
    public int deleteMember(int userNo) {
        return sqlSession.delete("memberMapper.deleteMember", userNo);
    }

    /* ----------------------------------------------------------------------------------- */


    @Override
    public AlarmVO selectAlarm(String userNo) {
        return sqlSession.selectOne("memberMapper.selectAlarm",userNo);
    }

    //승인 알림 insert
    @Override
    public int insertAlarm(AlarmVO alarmVO) {
        return sqlSession.insert("memberMapper.insertAlarm",alarmVO);
    }

    //승인 알림 띄우기
    @Override
    public Integer selectAuthorityAlarm(String userNo) {
        return sqlSession.selectOne("memberMapper.selectAuthorityAlarm",userNo);
    }

    @Override
    public RealtorDetailVO selectInquiryAlarm(String userNo) {
        return sqlSession.selectOne("memberMapper.selectInquiryAlarm",userNo);
    }

    @Override
    public List<InquiryVO> selectUserInquiryAlarm(String userNo) {
        return sqlSession.selectList("memberMapper.selectUserInquiryAlarm",userNo);
    }

    @Override
    public void updateInquiryReadCnt(String inquiryCode) {
        sqlSession.update("memberMapper.updateInquiryReadCnt",inquiryCode);
    }
}
