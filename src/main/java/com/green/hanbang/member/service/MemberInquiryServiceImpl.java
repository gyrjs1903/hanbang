package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.InquiryStatusVO;
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
    public String selectNextInquiryNumber() {
        return sqlSession.selectOne("inquiryMapper.selectNextInquiryNumber");
    }

    @Override
    public int insertMemberInquiry(MemberInquiryVO memberInquiryVO) {
        return sqlSession.insert("inquiryMapper.insertMemberInquiry", memberInquiryVO);
    }

    @Override
    public List<MemberInquiryVO>  selectInquiryDetail(MemberInquiryVO memberInquiryVO) {
        return sqlSession.selectList("inquiryMapper.selectInquiryDetail", memberInquiryVO);
    }

    @Override
    public InquiryStatusVO selectStatus(String inquiryStCode) {
        return sqlSession.selectOne("inquiryMapper.selectStatus",inquiryStCode);
    }

    @Override
    public int insertMemberInquiryImg(MemberInquiryImgVO memberInquiryImgVO) {
        return sqlSession.insert("inquiryMapper.insertMemberInquiryImg", memberInquiryImgVO);
    }



}
