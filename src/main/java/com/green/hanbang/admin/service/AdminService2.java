package com.green.hanbang.admin.service;

import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.FalseOfferingsVO;

import java.util.List;

public interface AdminService2 {

    //허위매물 신고 4건 이상인 매물 조회
    public List<FalseOfferingsVO> selectFalseOfferings();

    //공인중개사 권한 승인 요청
    public List<RealtorDetailVO> selectRealtorAuthority();
}
