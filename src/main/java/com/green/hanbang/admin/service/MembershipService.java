package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MemCateVO;
import com.green.hanbang.admin.vo.MemItemVO;
import com.green.hanbang.admin.vo.MembershipVO;

import java.util.List;

public interface MembershipService {

    // 맴버쉽 카테고리 조회 (대분류)
    public List<MemCateVO> selectCategory();

    // 맴버쉽 상품 별 세부 상품 조회 (중분류 및 소분류)
    public List<MembershipVO> selectMembershipItemList(String memCateCode);

    // 상품 세부 정보 조회 (중분류 및 소분류의 세부 정보)
    public MemCateVO membershipItemDetail(MemCateVO memCateVO);

}
