package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MembershipVO;

import java.util.List;

public interface MembershipService {

    // 맴버쉽 카테고리 조회
    public List<MembershipVO> selectCategory();

    // 맴버쉽 카테고리 별 리스트 조회
    public List<MembershipVO> selectMembershipList(String mCateCode);

}
