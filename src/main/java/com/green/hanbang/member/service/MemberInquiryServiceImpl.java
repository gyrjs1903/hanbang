package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberInquiryTypeVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberInquiryServiceImpl implements MemberInquiryService{
    private final SqlSessionTemplate sqlSession;
    @Override
    public List<MemberInquiryTypeVO> selectMemberInquiryTypeList(MemberInquiryTypeVO memberInquiryTypeVO) {
        return sqlSession.selectList("inquiryMapper.selectMemberInquiryTypeList", memberInquiryTypeVO);
    }
}
