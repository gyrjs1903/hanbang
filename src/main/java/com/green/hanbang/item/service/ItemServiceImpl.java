package com.green.hanbang.item.service;

import com.green.hanbang.item.vo.BuyVO;
import com.green.hanbang.item.vo.GeneralItemVO;
import com.green.hanbang.item.vo.PackageItemVO;
import com.green.hanbang.item.vo.PlusItemVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final SqlSessionTemplate sqlSession;

    // buyCode 생성
    @Override
    public String selectNextBuyCode() {
        return sqlSession.selectOne("itemMapper.selectNextBuyCode");
    }

    // 아이템 구매
    @Override
    public void goBuyItem(BuyVO buyVO) {
        sqlSession.insert("itemMapper.goBuyItem", buyVO);
    }

    // packageDetailCode 생성
    @Override
    public String selectNextPackageDetailCode() {
        return sqlSession.selectOne("itemMapper.selectNextPackageDetailCode");
    }

    // 페키지 아이템 구매 시 구매 세부 내역
    @Override
    public void buyPackageItem(PackageItemVO packageItemVO) {
        sqlSession.insert("itemMapper.buyPackageItem", packageItemVO);
    }

    // generalDetailCode 생성
    @Override
    public String selectNextGeneralDetailCode() {
        return sqlSession.selectOne("itemMapper.selectNextGeneralDetailCode");
    }

    // 일반 상품 구매 시 구매 세부 내역
    @Override
    public void buyGeneralItem(GeneralItemVO generalItemVO) {
        sqlSession.insert("itemMapper.buyGeneralItem", generalItemVO);
    }

    // plusDetailCode 생성
    @Override
    public String selectNextPlusDetailCode() {
        return sqlSession.selectOne("itemMapper.selectNextPlusDetailCode");
    }

    // 플러스 상품 구매 시 구매 세부 내역
    @Override
    public void buyPlusItem(PlusItemVO plusItemVO) {
        sqlSession.insert("itemMapper.buyPlusItem", plusItemVO);
    }


}
