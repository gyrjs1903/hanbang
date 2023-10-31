package com.green.hanbang.item.controller;

import com.green.hanbang.admin.vo.MemCateVO;
import com.green.hanbang.item.service.ItemService;
import com.green.hanbang.item.vo.BuyVO;
import com.green.hanbang.item.vo.GeneralItemVO;
import com.green.hanbang.item.vo.PackageItemVO;
import com.green.hanbang.item.vo.PlusItemVO;
import com.green.hanbang.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/goBuyItem")
    public String buyItem(BuyVO buyVO, HttpSession session, PackageItemVO packageItemVO, GeneralItemVO generalItemVO, PlusItemVO plusItemVO, String memCateCode){
        String buyNextCode = itemService.selectNextBuyCode();
        buyVO.setBuyCode(buyNextCode);
        buyVO.setUserNo(((MemberVO)session.getAttribute("loginInfo")).getUserNo());

        itemService.goBuyItem(buyVO);

        if (memCateCode.equals("CATE_001")){
            String nextPackageCode = itemService.selectNextPackageDetailCode();
            packageItemVO.setBuyDetailCode(nextPackageCode);
            packageItemVO.setBuyCode(buyVO.getBuyCode());
            packageItemVO.setUserNo(((MemberVO)session.getAttribute("loginInfo")).getUserNo());

            itemService.buyPackageItem(packageItemVO);
        } else if (memCateCode.equals("CATE_002")){
            generalItemVO.setBuyDetailCode(itemService.selectNextGeneralDetailCode());
            generalItemVO.setBuyCode(buyVO.getBuyCode());
            generalItemVO.setUserNo(((MemberVO)session.getAttribute("loginInfo")).getUserNo());

            itemService.buyGeneralItem(generalItemVO);
        } else if (memCateCode.equals("CATE_003")){
            plusItemVO.setBuyDetailCode(itemService.selectNextPlusDetailCode());
            plusItemVO.setBuyCode(buyVO.getBuyCode());
            plusItemVO.setUserNo(((MemberVO)session.getAttribute("loginInfo")).getUserNo());

            itemService.buyPlusItem(plusItemVO);
        }




        return "main/buyItemList";
    }


}
