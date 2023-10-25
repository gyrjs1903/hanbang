package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberInquiryTypeVO;

import java.util.List;

public interface MemberInquiryService {

    // 문의 유형 조회
    public List<MemberInquiryTypeVO> selectMemberInquiryTypeList(MemberInquiryTypeVO memberInquiryTypeVO);

}
