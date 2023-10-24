package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MemberManageVO;

import java.util.List;

public interface MemberManageService {

    // 일반회원 , 공인 중개사 목록 조회
    public List<MemberManageVO> userList(MemberManageVO memberManageVO);
    public List<MemberManageVO> realList(MemberManageVO memberManageVO);

    // 일반회원 상세 조회
    public MemberManageVO userDetail(String userNo);

    // 공인 중개사 상세 조회
    public MemberManageVO realDetail(String identificationNum);

    // 공인 중개사 승인
    public int updateAuthority(MemberManageVO memberManageVO);

    // 일반 회원 삭제
    public int deleteUser(String userNo);

    // 공인 중개사 삭제
    public int deleteReal(int identificationNum);
}
