package com.green.hanbang.admin.service;

import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.FalseOfferingsVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl2 implements AdminService2{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<FalseOfferingsVO> selectFalseOfferings() {
        return sqlSession.selectList("admin2Mapper.selectFalseOfferings");
    }

    @Override
    public List<RealtorDetailVO> selectRealtorAuthority() {
        return sqlSession.selectList("admin2Mapper.selectRealtorAuthority");
    }
}
