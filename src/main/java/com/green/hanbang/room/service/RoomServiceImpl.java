package com.green.hanbang.room.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.vo.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<OptionsVO> selectOptions() {
        return sqlSession.selectList("roomMapper.selectOptions");
    }

    @Override
    public List<PropertyTypeVO> selectProperty() {
        return sqlSession.selectList("roomMapper.selectProperty");
    }

    @Override
    public List<TradeTypeVO> selectTradeType() {
        return sqlSession.selectList("roomMapper.selectTradeType");
    }

    @Override
    public String selectNextRoomCode() {
        return sqlSession.selectOne("roomMapper.selectNextRoomCode");
    }

    @Override
    public void insertRoom(RoomVO roomVO) {
        sqlSession.insert("roomMapper.insertRoom",roomVO);
        sqlSession.insert("roomMapper.insertRoomImg", roomVO);
        sqlSession.insert("roomMapper.addrInsert", roomVO);
    }

    @Override
    public List<RoomVO> selectRoom(RoomSearchVO roomSearchVO) {
        return sqlSession.selectList("roomMapper.selectRoom", roomSearchVO);
    }

    @Override
    public List<RoomAddrVO> selectRoomAddr() {
        return sqlSession.selectList("roomMapper.selectRoomAddr");
    }



    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public RoomVO selectRoomInfo(String roomCode) {
        return sqlSession.selectOne("roomMapper.selectRoomInfo",roomCode);
    }

//    @Override
//    public List<OptionsVO> selectOptions() {
//        return sqlSession.selectList("roomMapper.selectOptions");
//    }

    @Override
    public String selectLoginType(String userNo) {
        return sqlSession.selectOne("roomMapper.selectLoginType",userNo);
    }

    @Override
    public MemberVO selectRegUser(String userNo) {
        return sqlSession.selectOne("roomMapper.selectRegUser",userNo);
    }

    @Override
    public MemberVO selectRegRealtor(String userNo) {
        return sqlSession.selectOne("roomMapper.selectRegRealtor",userNo);
    }

    @Override
    public String selectElDAS(MemberVO memberVO) {
        return sqlSession.selectOne("roomMapper.selectElDAS",memberVO);
    }

    @Override
    public List<ReasonVO> selectReasonList() {
        return sqlSession.selectList("roomMapper.selectReasonList");
    }

    @Override
    public int insertFalseOfferings(FalseOfferingsVO falseOfferingsVO) {
        return sqlSession.insert("roomMapper.insertFalseOfferings",falseOfferingsVO);
    }

    @Override
    public List<RoomIMGVO> selectFalseOfferingsImgs(String roomCode) {
        return sqlSession.selectList("adminMapper.selectFalseOfferingsImgs",roomCode);
    }

    @Override
    public int deleteRoom(String roomCode) {
        sqlSession.delete("adminMapper.deleteFalseOfferings",roomCode);
        sqlSession.delete("adminMapper.deleteFalseOfferingsImgs",roomCode);
        sqlSession.delete("adminMapper.deleteAddr",roomCode);
        return sqlSession.delete("adminMapper.deleteRoom",roomCode);
    }

    @Override
    public List<InquiryTitleVO> selectInquiryTitle() {
        return sqlSession.selectList("roomMapper.selectInquiryTitle");
    }

    @Override
    public boolean insertInquiry(InquiryVO inquiryVO) {
        return sqlSession.insert("roomMapper.insertInquiry",inquiryVO) == 1;
    }
}
