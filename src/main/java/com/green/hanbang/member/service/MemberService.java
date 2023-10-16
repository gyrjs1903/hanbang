package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberVO;

import java.util.List;

public interface MemberService {
    // 회원 가입
    public int join(MemberVO memberVO);

    // 로그인
    public MemberVO login(MemberVO memberVO);

    // 멤버리스트
    public List<MemberVO> memberList(MemberVO memberVO);

    // 이메일(아이디) 중복 확인
    public String userNameCheck(String userName);

    // 회원 탈퇴
    public int memberDelete(MemberVO memberVO);
}
