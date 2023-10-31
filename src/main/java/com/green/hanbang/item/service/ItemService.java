package com.green.hanbang.item.service;

import com.green.hanbang.item.vo.BuyVO;
import com.green.hanbang.item.vo.GeneralItemVO;
import com.green.hanbang.item.vo.PackageItemVO;
import com.green.hanbang.item.vo.PlusItemVO;

public interface ItemService {
    // buyCode 생성
    public String selectNextBuyCode();

    // 상품 구매하기
    public void goBuyItem(BuyVO buyVO);

    // packageDetailCode 생성
    public String selectNextPackageDetailCode();

    // 페키지 아이템 구매 후 세부 내역
    public void buyPackageItem(PackageItemVO packageItemVO);

    // generalDetailCode 생성
    public String selectNextGeneralDetailCode();

    // 일반 상품 구매 후 세부 내역
    public void buyGeneralItem(GeneralItemVO generalItemVO);

    // plusDetailCode 생성
    public String selectNextPlusDetailCode();

    // 플러스 상품 구매 후 세부 내역
    public void buyPlusItem(PlusItemVO plusItemVO);
}
