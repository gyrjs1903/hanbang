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
    public int insertProImg(MemberImgVO memberImgVO) {
        return sqlSession.insert("memberMapper.insertProImg", memberImgVO);
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
    public int memberDelete(MemberVO memberVO) {
        return sqlSession.delete("memberMapper.memberDelete", memberVO);
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
    public MemberImgVO profileImgLoad(MemberImgVO memberImgVO) {
        return sqlSession.selectOne("memberMapper.profileImgLoad", memberImgVO);
    }

    @Override
    public int updateNickname(String userName) {
        return sqlSession.update("memberMapper.updateNickName", userName);
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
}
