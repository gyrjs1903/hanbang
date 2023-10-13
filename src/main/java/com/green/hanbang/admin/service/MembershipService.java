package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MemCateVO;
import com.green.hanbang.admin.vo.MemItemVO;
import com.green.hanbang.admin.vo.MembershipVO;

import java.util.List;

public interface MembershipService {

    // 맴버쉽 카테고리 조회
    public List<MemCateVO> selectCategory();

    // 맴버쉽 카테고리 별 상품 조회
    public List<MembershipVO> selectMembershipList(String memCateCode);

    // 맴버쉽 상품 별 세부 상품 조회
    public List<MemItemVO> selectMembershipItemList(String membershipCode);

    // 상품 세부 정보 조회
    public MemCateVO membershipItemDetail(MemCateVO memCateVO);

}
