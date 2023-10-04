package com.green.hanbang.realtor.service;

import com.green.hanbang.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealtorServiceImpl implements RealtorService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public MemberVO selectRealtorMyPage() {
        return sqlSession.selectOne("realtorMapper.selectRealtorMyPage");
    }
}
