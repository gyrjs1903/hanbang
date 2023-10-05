package com.green.hanbang.realtor.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

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
    public void insertRealtorDetail(RealtorDetailVO realtorDetailVO) {
        sqlSession.insert("realtorMapper.insertLicenseImg",realtorDetailVO);
        sqlSession.insert("realtorMapper.insertRealtorDetail",realtorDetailVO);
    }
}
