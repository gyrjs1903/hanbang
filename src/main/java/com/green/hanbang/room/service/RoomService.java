package com.green.hanbang.room.service;


import com.green.hanbang.admin.vo.MemCateVO;
import com.green.hanbang.item.vo.GeneralItemVO;
import com.green.hanbang.item.vo.PackageItemVO;
import com.green.hanbang.item.vo.PlusItemVO;
import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.vo.*;

import java.util.List;

public interface RoomService {
    //option select
    public List<OptionsVO> selectOptions();

    //select property
    public List<PropertyTypeVO> selectProperty();

    //전월세 유형 셀렉트
    public  List<TradeTypeVO> selectTradeType();

    //방코드 셀렉트
    public String selectNextRoomCode();

    //매물 인설트
    public void insertRoom(RoomVO roomVO);

    //방 조회
    public List<RoomVO> selectRoom(RoomSearchVO roomSearchVO);

    //주소 및 좌표 조회
    public List<RoomAddrVO> selectRoomAddr();

///////////////////////////////////////////////////////////////
    //room테이블 디테일정보 조회
    public RoomVO selectRoomInfo(String roomCode);

    //detailOption값 조회(roomMapper)
   // public List<OptionsVO> selectOptions();

    //방 등록한 사람 login_type 조회
    public String selectLoginType(String userNo);

    //방 등록한 일반회원 정보 조회
    public MemberVO selectRegUser(String userNo);

    //방 등록한 공인중개사 정보 조회
    public MemberVO selectRegRealtor(String userNo);

    //허위매물 신고 시 본인인증
    public String selectElDAS(MemberVO memberVO);

    //허위매물 사유 조회
    public List<ReasonVO> selectReasonList();

    //허위매물 신고
    public int insertFalseOfferings(FalseOfferingsVO falseOfferingsVO);

    //허위매물 방 이미지 조회
    public List<RoomIMGVO> selectFalseOfferingsImgs(String roomCode);

    //허위매물 방 삭제
    public int deleteRoom(String roomCode);

    //문의 제목 목록 조회
    public List<InquiryTitleVO> selectInquiryTitle();

    //문의 하기
    public boolean insertInquiry(InquiryVO inquiryVO);

    //////////////////////////////////////////

    // 구매 내역 출력
    public List<PackageItemVO> selectPackageItemList (String userNo);
    public List<GeneralItemVO> selectGeneralItemList (String userNo);
    public List<PlusItemVO> selectPlusItemList (String userNo);



}
