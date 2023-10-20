package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MemCateVO;
import com.green.hanbang.admin.vo.MemItemVO;
import com.green.hanbang.admin.vo.MembershipVO;

import java.util.List;

public interface MembershipService {

    // 카테고리 조회 (대분류)
    public List<MemCateVO> selectCategory();

    // 카테고리 조회 (중분류)
    public List<MembershipVO> selectMidCategory(String memCateCode);

    // 카테고리 조회 (아이템)
    public List<MemItemVO> selectItemCategory(String membershipCode);

    // 맴버쉽 상품 별 세부 상품 조회 (중분류 및 소분류)
    public List<MembershipVO> selectMembershipItemList(String memCateCode);

    // 맴버쉽 상품 등록하기
    public int insertCategory(MemCateVO memCateVO);
    public int insertMidCategory(MemCateVO memCateVO);
    public int insertItem(MemItemVO memItemVO);

    //다음에 insert할 CateCode 조회
    public String selectNextCateCode();

    //다음에 insert할 CateCode 조회
    public String selectNextMembershipCode();

    //다음에 insert할 CateCode 조회
    public String selectNextItemCode();
}
