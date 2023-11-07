package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.AlarmVO;
import com.green.hanbang.member.vo.MemberImgVO;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.FalseOfferingsVO;
import com.green.hanbang.room.vo.InquiryVO;

import java.util.List;

public interface MemberService {
    // 회원 가입
    public int join(MemberVO memberVO);

    // 다음에 들어갈 회원 번호 조회
    public String selectNextUserNo();

    // 프로필 이미지 등록
    public void insertProfile(MemberImgVO memberImgVO);

    // 프로필 이미지 수정
    public void updateProfile(MemberImgVO memberImgVO);

    // 로그인
    public MemberVO login(MemberVO memberVO);

    // 이메일(아이디) 중복 확인
    public String userNameCheck(String userName);

    // 비밀 번호 일치 확인
    public String passWordCheck(String passWord);

    // 회원 번호 조회
    public String selectUserNo(String userNo);

    // 회원 정보 조회
    public String selectUserInfo(MemberVO memberVO);

    // 닉네임 변경
    public void updateNickname(MemberVO memberVO);

    // 비밀 번호 변경
    public void updatePassword(MemberVO memberVO);

    // 회원 탈퇴
    public int deleteMember(int userNo);

    // -------------------------------------------------------------------- //

    //공인중개사 승인 여부
    public AlarmVO selectAlarm(String userNo);

    //공인중개사 승인 후 알림insert
    public int insertAlarm(AlarmVO alarmVO);

    //승인 알림 띄우기
    public Integer selectAuthorityAlarm(String userNo);

    //공인중개사 매물 문의 알림
    public RealtorDetailVO selectInquiryAlarm(String userNo);

    //매물 문의 답글 완료 알림
    public List<InquiryVO> selectUserInquiryAlarm(String userNo);

    //문의 답글 확인 후 ReadCnt 증가
    public void updateInquiryReadCnt(String inquiryCode);

    //허위매물 신고 상태 조회
    public List<FalseOfferingsVO> selectFalseOfferingsList(String userNo);
}
