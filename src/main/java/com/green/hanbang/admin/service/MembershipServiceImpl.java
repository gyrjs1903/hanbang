package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MemCateVO;
import com.green.hanbang.admin.vo.MemItemVO;
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
    public List<MemCateVO> selectCategory() {
        return sqlSession.selectList("adminMapper.selectCategory");
    }

    @Override
    public List<MembershipVO> selectMembershipList(String memCateCode) {
        return sqlSession.selectList("adminMapper.selectMembershipList", memCateCode);
    }

    @Override
    public List<MemItemVO> selectMembershipItemList(String membershipCode) {
        return sqlSession.selectList("adminMapper.selectMembershipItemList", membershipCode);
    }

    @Override
    public MemCateVO membershipItemDetail(MemCateVO memCateVO) {
        return sqlSession.selectOne("adminMapper.membershipDetail", memCateVO);
    }
}
