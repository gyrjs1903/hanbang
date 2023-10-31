package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberInquiryImgVO;
import com.green.hanbang.member.vo.MemberInquiryTypeVO;
import com.green.hanbang.member.vo.MemberInquiryVO;
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

    @Override
    public List<MemberInquiryVO> selectMemberInquiryList(MemberInquiryVO memberInquiryVO) {
        return sqlSession.selectList("inquiryMapper.selectMemberInquiryList", memberInquiryVO);
    }

    @Override
    public String selectNextInquiryNumber(String memberInquiryWriteNo) {
        return sqlSession.selectOne(memberInquiryWriteNo);
    }

    @Override
    public int insertMemberInquiry(MemberInquiryVO memberInquiryVO) {
        return sqlSession.insert("inquiryMapper.insertMemberInquiry", memberInquiryVO);
    }

    @Override
    public int insertMemberInquiryImg(MemberInquiryImgVO memberInquiryImgVO) {
        return sqlSession.insert("inquiryMapper.insertMemberInquiryImg", memberInquiryImgVO);
    }

}
