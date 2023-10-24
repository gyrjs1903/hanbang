package com.green.hanbang.room.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.realtor.vo.RealtorDetailVO;
import com.green.hanbang.room.vo.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl2 implements RoomService2{
    private final SqlSessionTemplate sqlSession;

    @Override
    public RoomVO selectRoomInfo(String roomCode) {
        return sqlSession.selectOne("room2Mapper.selectRoomInfo",roomCode);
    }

    @Override
    public List<OptionsVO> selectOptions() {
        return sqlSession.selectList("roomMapper.selectOptions");
    }

    @Override
    public String selectLoginType(String userNo) {
        return sqlSession.selectOne("room2Mapper.selectLoginType",userNo);
    }

    @Override
    public MemberVO selectRegUser(String userNo) {
        return sqlSession.selectOne("room2Mapper.selectRegUser",userNo);
    }

    @Override
    public MemberVO selectRegRealtor(String userNo) {
        return sqlSession.selectOne("room2Mapper.selectRegRealtor",userNo);
    }

    @Override
    public String selectElDAS(MemberVO memberVO) {
        return sqlSession.selectOne("room2Mapper.selectElDAS",memberVO);
    }

    @Override
    public List<ReasonVO> selectReasonList() {
        return sqlSession.selectList("room2Mapper.selectReasonList");
    }

    @Override
    public int insertFalseOfferings(FalseOfferingsVO falseOfferingsVO) {
        return sqlSession.insert("room2Mapper.insertFalseOfferings",falseOfferingsVO);
    }

    @Override
    public List<InquiryTitleVO> selectInquiryTitle() {
        return sqlSession.selectList("room2Mapper.selectInquiryTitle");
    }
}
