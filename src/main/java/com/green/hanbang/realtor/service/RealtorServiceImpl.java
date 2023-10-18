package com.green.hanbang.realtor.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealtorServiceImpl implements RealtorService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public MemberVO selectRealtorMyPage(String userNo) {
        return sqlSession.selectOne("realtorMapper.selectRealtorMyPage",userNo);
    }

    @Override
    public String selectNextRealtorCode() {
        return sqlSession.selectOne("realtorMapper.selectNextRealtorCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRealtorDetail(RealtorDetailVO realtorDetailVO) {
        sqlSession.insert("realtorMapper.insertRealtorDetail",realtorDetailVO);
        sqlSession.insert("realtorMapper.insertLicenseImg",realtorDetailVO);
    }

    @Override
    public String selectAuthorityStatue(String userNo) {
        return sqlSession.selectOne("realtorMapper.selectAuthorityStatue",userNo);
    }

    @Override
    public String selectIdentificationNum(String identificationNum) {
        return sqlSession.selectOne("realtorMapper.selectIdentificationNum", identificationNum);
    }

    @Override
    public String selectRealtorPw(String userNo) {
        return sqlSession.selectOne("realtorMapper.selectRealtorPw",userNo);
    }
}
