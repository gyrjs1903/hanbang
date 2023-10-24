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
    public List<MemItemVO> selectItemCategory(String memCateCode) {
        return sqlSession.selectList("adminMapper.selectMembershipItemList", memCateCode);
    }

    // 중분류 및 소분류 조회
    @Override
    public List<MembershipVO> selectMembershipItemList(String memCateCode) {
        return sqlSession.selectList("adminMapper.selectMembershipItemList", memCateCode);
    }


    // 맴버십 대분류 카테고리 등록하기
    @Override
    public int insertCategory(MemCateVO memCateVO) {
        return sqlSession.insert("adminMapper.insertCategory", memCateVO);
    }

    // 맴버십 중분류 카테고리 등록하기
    @Override
    public int insertMidCategory(MemCateVO memCateVO) {
        return sqlSession.insert("adminMapper.insertMidCategory", memCateVO);
    }

    // 각 카테고리의 code 자동 생성
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

    //맴버쉽 등록 화면에서 상단의 카테고리 클릭 시 해당 카테고리에 포함된 상품을 조회하는 쿼리
    @Override
    public List<MemItemVO> selectItemListByCate(String memCateCode) {
        return sqlSession.selectList("adminMapper.selectItemListByCate", memCateCode);
    }

    //맴버쉽 등록 화면에서 대분류 또는 중분류 클릭 시 해당 카테고리에 포함된 상품을 조회
    @Override
    public List<MemItemVO> selectItemListByMidCate(MemItemVO memItemVO) {
        return sqlSession.selectList("adminMapper.selectItemListByMidCate", memItemVO);
    }

    // 아이템 세부정보 확인하기
    @Override
    public List<MemItemVO> selectItemDetail(String itemCode) {
        return sqlSession.selectList("adminMapper.selectItemDetail", itemCode);
    }

    // 아이템 세부정보 수정하기
    @Override
    public int updateItemInfo(MemItemVO memItemVO) {
        return sqlSession.update("adminMapper.updateItemInfo", memItemVO);
    }

    // 아이템 등록하기
    @Override
    public int insertItem(MemItemVO memItemVO) {
        return sqlSession.insert("adminMapper.insertItem", memItemVO);
    }


}
