package com.green.hanbang.realtor.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.InquiryVO;

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
    public String selectIdentificationNum(String identificationNum);

    //정보 수정시 비밀번호 확인
    public String selectRealtorPw(String userNo);

    //공인중개사 detail정보 입력 시 인증하기 버튼 disabled
    public String selectRealtorDetailInfo(String userNo);

    //공인중개사 인증 완료 시 상세정보 조회
    public RealtorDetailVO selectRealtorOfficeInfo(String userNo);

    //공인중개사 정보 수정
    public void updateRealtorInfo(MemberVO memberVO);

    //공인중개사 사무소명 수정
    public void updateRealtorOffice(MemberVO memberVO);

    //공인중개사 문의글 확인
    public List<InquiryVO> selectInquiryBoard(InquiryVO inquiryVO);

    //공인중개사 전체 문의글 수 확인
    public int countInquiryCnt(String userNo);

    //문의글 상세 조회
    public InquiryVO selectInquiryDetail(String inquiryCode);

    //문의글 답변 작성
    public boolean updateInquiryAnswer(InquiryVO inquiryVO);
}
