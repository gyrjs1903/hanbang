package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberVO;

public interface MemberService {
    // 회원 가입
    public int join(MemberVO memberVO);

    // 프로필 이미지 변경
    public int updateProImg(MemberVO memberVO);

    // 로그인
    public MemberVO login(MemberVO memberVO);

    // 프로필 이미지 불러오기
    public MemberVO profileImgLoad(String userNo);

    // 이메일(아이디) 중복 확인
    public String userNameCheck(String userName);

    // 회원 탈퇴
    public int memberDelete(MemberVO memberVO);
}
