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
    public List<MembershipVO> selectMidCategory(String memCateCode) {
        return sqlSession.selectList("adminMapper.selectMidCategory", memCateCode);
    }

    // 카테고리 조회 (소분류)
    @Override
    public List<MemItemVO> selectItemCategory(String membershipCode) {
        return sqlSession.selectList("adminMapper.selectItemCategory", membershipCode);
    }

    // 중분류 및 소분류 조회
    @Override
    public List<MembershipVO> selectMembershipItemList(String memCateCode) {
        return sqlSession.selectList("adminMapper.selectMembershipItemList", memCateCode);
    }


    // 맴버쉽 등록하기
    @Override
    public int insertCategory(MemCateVO memCateVO) {
        return sqlSession.insert("adminMapper.insertCategory", memCateVO);
    }

    @Override
    public int insertMidCategory(MemCateVO memCateVO) {
        return sqlSession.insert("adminMapper.insertMidCategory", memCateVO);
    }

    @Override
    public int insertItem(MemItemVO memItemVO) {
        return sqlSession.insert("adminMapper.insertItem", memItemVO);
    }

    @Override
    public String selectNextCateCode() {
        return sqlSession.selectOne("adminMapper.selectNextCateCode");
    }

    @Override
    public String selectNextMembershipCode() {
        return sqlSession.selectOne("adminMapper.selectNextMembershipCode");
    }

    @Override
    public String selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }


}
