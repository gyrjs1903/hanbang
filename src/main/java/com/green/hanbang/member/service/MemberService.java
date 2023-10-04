package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberVO;

public interface MemberService {
    // 회원 가입
    public int join(MemberVO memberVO);

    // 로그인
    public MemberVO login(MemberVO memberVO);

}
