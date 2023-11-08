package com.green.hanbang.room.service;

import com.green.hanbang.item.vo.GeneralItemVO;
import com.green.hanbang.item.vo.PackageItemVO;
import com.green.hanbang.item.vo.PlusItemVO;
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
    public List<RoomVO> selectRoom(RoomSearchVO roomSearchVO, String searchAddr) {
        return sqlSession.selectList("roomMapper.selectRoom", roomSearchVO);
    }

    @Override
    public List<RoomAddrVO> selectRoomAddr() {
        return sqlSession.selectList("roomMapper.selectRoomAddr");
    }

    @Override
    public List<RoomVO> selectMainPageRoom() {
        return sqlSession.selectList("roomMapper.selectMainPageRoom");
    }

    @Override
    public List<TypeAvgVO> avgRoom() {
        return sqlSession.selectList("roomMapper.avgRoom");
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
    public String selectDuplicateReport(FalseOfferingsVO falseOfferingsVO) {
        return sqlSession.selectOne("roomMapper.selectDuplicateReport",falseOfferingsVO);
    }

    @Override
    public List<RoomIMGVO> selectFalseOfferingsImgs(String roomCode) {
        return sqlSession.selectList("adminMapper.selectFalseOfferingsImgs",roomCode);
    }

    @Override
    public int deleteRoom(String roomCode) {
        sqlSession.update("adminMapper.updateFalseOfferings",roomCode);
        return sqlSession.delete("adminMapper.deleteFalseOfferingsImgs",roomCode);
    }

    @Override
    public List<InquiryTitleVO> selectInquiryTitle() {
        return sqlSession.selectList("roomMapper.selectInquiryTitle");
    }

    @Override
    public boolean insertInquiry(InquiryVO inquiryVO) {
        return sqlSession.insert("roomMapper.insertInquiry",inquiryVO) == 1;
    }

    // 상품 구매 내역 출력
    @Override
    public List<PackageItemVO> selectPackageItemList(String userNo) {
        return sqlSession.selectList("itemMapper.selectPackageItemList", userNo);
    }

    @Override
    public List<GeneralItemVO> selectGeneralItemList(String userNo) {
        return sqlSession.selectList("itemMapper.selectGeneralItemList", userNo);
    }

    @Override
    public List<PlusItemVO> selectPlusItemList(String userNo) {
        return sqlSession.selectList("itemMapper.selectPlusItemList", userNo);
    }

    // 매물에 상품 적용
    @Override
    public int insertApplyItem(ApplyItemVO applyItemVO) {
        return sqlSession.insert("roomMapper.insertApplyItem", applyItemVO);
    }

    // 상품 사용 시 갯수 차감
    @Override
    public void updateItemCnt(ApplyItemVO applyItemVO) {
        sqlSession.update("roomMapper.updateItemCnt", applyItemVO);
    }

    // 패키지 상품 잔여 갯수 조회
    @Override
    public boolean getPackageIsValid(String buyCode) {
        String result = sqlSession.selectOne("roomMapper.getPackageIsValid", buyCode);

        if(result == null){
            return true;
        }
        else{
            return false;
        }
    }

    // 일반 상품 잔여 갯수 조회
    @Override
    public boolean getGeneralIsValid(String buyCode) {
        String result = sqlSession.selectOne("roomMapper.getGeneralIsValid", buyCode);

        if(result == null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void updatePackageIsValid(String buyCode) {
        sqlSession.update("roomMapper.updatePackageIsValid", buyCode);
    }

    @Override
    public void updateGeneralIsValid(String buyCode) {
        sqlSession.update("roomMapper.updateGeneralIsValid", buyCode);
    }

    @Override
    public void updatePackageValidWhenLogin(String userNo) {
        sqlSession.update("roomMapper.updatePackageValidWhenLogin", userNo);
    }

    @Override
    public void updateGeneralValidWhenLogin(String userNo) {
        sqlSession.update("roomMapper.updateGeneralValidWhenLogin", userNo);
    }

    @Override
    public void updatePlusValidWhenLogin(String userNo) {
        sqlSession.update("roomMapper.updatePlusValidWhenLogin", userNo);
    }
}
