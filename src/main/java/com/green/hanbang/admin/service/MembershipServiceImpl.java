package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MembershipVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<MembershipVO> selectCategory() {
        return sqlSession.selectList("adminMapper.selectCategory");
    }

    @Override
    public List<MembershipVO> selectMembershipList(String mCateCode) {
        return sqlSession.selectList("adminMapper.selectMembershipList", mCateCode);
    }

    @Override
    public List<MembershipVO> selectMembershipItemList(String membershipCode) {
        return sqlSession.selectList("adminMapper.selectMembershipItemList", membershipCode);
    }
}
