package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MemberManageVO;

import java.util.List;

public interface MemberManageService {

    // 일반회원 , 공인 중개사 목록 조회
    public List<MemberManageVO> userList();
    public List<MemberManageVO> realList();
}
