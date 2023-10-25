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
    public List<MemItemVO> selectItemCategory(String memCateCode);

    // 맴버쉽 상품 별 세부 상품 조회 (중분류 및 소분류) --> membershipList에서 상품 모두 보기
    public List<MembershipVO> selectMembershipItemList(String memCateCode);

    // 맴버쉽 카테고리 등록하기
    public int insertCategory(MemCateVO memCateVO);
    public int insertMidCategory(MemCateVO memCateVO);

    //다음에 insert할 CateCode 조회
    public String selectNextCateCode();

    //다음에 insert할 CateCode 조회
    public String selectNextMembershipCode();

    //다음에 insert할 CateCode 조회
    public String selectNextItemCode();

    //맴버쉽 등록 화면에서 상단의 카테고리 클릭 시 해당 카테고리에 포함된 상품을 조회하는 쿼리
    public List<MemItemVO> selectItemListByCate(String memCateCode);

    //맴버쉽 등록 화면에서 대분류 또는 중분류 클릭 시 해당 카테고리에 포함된 상품을 조회
    public List<MemItemVO> selectItemListByMidCate(MemItemVO memItemVO);

    // 특정 아이템 클릭 시 그 아이템의 상세 정보 조회
    public List<MemItemVO> selectItemDetail(String itemCode);

    //아이템 수정하기
    public int updateItemInfo (MemItemVO memItemVO);

    // 아이템 등록하기
    public int insertItem(MemItemVO memItemVO);
}
