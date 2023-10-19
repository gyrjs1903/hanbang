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

    // 카테고리 조회 (대분류)
    @Override
    public List<MemCateVO> selectCategory() {
        return sqlSession.selectList("adminMapper.selectCategory");
    }

    // 카테고리 조회 (중분류)
    @Override
    public List<MembershipVO> selectMidCategory() {
        return sqlSession.selectList("adminMapper.selectMidCategory");
    }

    // 중분류 및 소분류 조회
    @Override
    public List<MembershipVO> selectMembershipItemList(String memCateCode) {
        return sqlSession.selectList("adminMapper.selectMembershipItemList", memCateCode);
    }

    // 중분류 및 소분류의 세부 조회
    @Override
    public MemCateVO membershipItemDetail(MemCateVO memCateVO) {
        return sqlSession.selectOne("adminMapper.membershipDetail", memCateVO);
    }

    // 맴버쉽 등록하기
    @Override
    public int insertCategory(MemCateVO memCateVO) {
        return sqlSession.insert("adminMapper.insertCategory", memCateVO);
    }

    @Override
    public int insertMidCategory(MembershipVO membershipVO) {
        return sqlSession.insert("adminMapper.insertMidCategory", membershipVO);
    }

    @Override
    public int insertItem(MemItemVO memItemVO) {
        return sqlSession.insert("adminMapper.insertItem", memItemVO);
    }


}
