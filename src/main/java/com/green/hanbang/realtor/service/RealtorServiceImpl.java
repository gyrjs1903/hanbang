package com.green.hanbang.realtor.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealtorServiceImpl implements RealtorService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public MemberVO selectRealtorMyPage(String userNo) {
        return sqlSession.selectOne("realtorMapper.selectRealtorMyPage",userNo);
    }

    @Override
    public String selectNextRealtorCode() {
        return sqlSession.selectOne("realtorMapper.selectNextRealtorCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRealtorDetail(RealtorDetailVO realtorDetailVO) {
        sqlSession.insert("realtorMapper.insertRealtorDetail",realtorDetailVO);
        sqlSession.insert("realtorMapper.insertLicenseImg",realtorDetailVO);
    }

    @Override
    public String selectAuthorityStatue(String userNo) {
        return sqlSession.selectOne("realtorMapper.selectAuthorityStatue",userNo);
    }

    @Override
    public String selectIdentificationNum(String identificationNum) {
        return sqlSession.selectOne("realtorMapper.selectIdentificationNum", identificationNum);
    }

    @Override
    public String selectRealtorPw(String userNo) {
        return sqlSession.selectOne("realtorMapper.selectRealtorPw",userNo);
    }

    @Override
    public String selectRealtorDetailInfo(String userNo) {
        return sqlSession.selectOne("realtorMapper.selectRealtorDetailInfo",userNo);
    }

    @Override
    public RealtorDetailVO selectRealtorOfficeInfo(String userNo) {
        return sqlSession.selectOne("realtorMapper.selectRealtorOfficeInfo",userNo);
    }

    @Override
    public void updateRealtorInfo(MemberVO memberVO) {
        sqlSession.update("realtorMapper.updateRealtorInfo",memberVO);
    }

    @Override
    public void updateRealtorOffice(MemberVO memberVO) {
        sqlSession.update("realtorMapper.updateRealtorOffice",memberVO);
    }

    @Override
    public List<InquiryVO> selectInquiryBoard(InquiryVO inquiryVO) {
        return sqlSession.selectList("realtorMapper.selectInquiryBoard",inquiryVO);
    }

    @Override
    public int countInquiryCnt(String userNo) {
        return sqlSession.selectOne("realtorMapper.countInquiryCnt",userNo);
    }

    @Override
    public InquiryVO selectInquiryDetail(String inquiryCode) {
        return sqlSession.selectOne("realtorMapper.selectInquiryDetail",inquiryCode);
    }

    @Override
    public boolean updateInquiryAnswer(InquiryVO inquiryVO) {
        return sqlSession.update("realtorMapper.updateInquiryAnswer",inquiryVO) == 1;
    }
}
