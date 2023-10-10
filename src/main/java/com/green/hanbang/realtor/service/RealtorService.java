package com.green.hanbang.realtor.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;

import java.util.List;

public interface RealtorService {

    //공인중개사 마이페이지
    public MemberVO selectRealtorMyPage(String userNo);

    //공인중개사 다음 코드 조회
    public String selectNextRealtorCode();

    //공인중개사 상세정보 입력
    public void insertRealtorDetail(RealtorDetailVO realtorDetailVO);

    //공인중개사 인증완료 여부
    public String selectAuthorityStatue(String userNo);

    //사업자등록증 중복 여부
    public Integer selectIdentificationNum(int identificationNum);
}
