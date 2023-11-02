package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberInquiryImgVO;
import com.green.hanbang.member.vo.MemberInquiryTypeVO;
import com.green.hanbang.member.vo.MemberInquiryVO;

import java.util.List;

public interface MemberInquiryService {

    // 문의 유형 조회
    public List<MemberInquiryTypeVO> selectMemberInquiryTypeList(MemberInquiryTypeVO memberInquiryTypeVO);

    // 문의 목록 조회
    public List<MemberInquiryVO> selectMemberInquiryList(MemberInquiryVO memberInquiryVO);

    // 다음 문의 작성 번호 조회
    public String selectNextInquiryNumber();

    // 문의 작성물 전송
    public int insertMemberInquiry(MemberInquiryVO memberInquiryVO); // 제목, 내용 등
    // public int insertMemberInquiryImg(MemberInquiryImgVO memberInquiryImgVO); // 첨부 파일

    // 문의 상세 보기
    public List<MemberInquiryVO> selectInquiryDetail(MemberInquiryVO memberInquiryVO);

}