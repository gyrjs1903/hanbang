package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.EventVO;

import java.util.List;

public interface EventService {
    // 이벤트 코드 생성
    public String selectNextEventCode();

    // 이벤트 등록 + 이벤트 이미지 등록
    public void insertEvent(EventVO eventVO);

    // 이벤트 목록 조회
    public List<EventVO> selectEventList();

    // 이벤트 상세 조회
    public EventVO selectEventDetail(String eventCode);

    //조회수 증가
    public int updateReadCnt (String eventCode);

    // 이벤트 정보 삭제
    public int deleteEvent (String eventCode);

    // 등록된 이미지 조회
    public EventVO selectImgList (EventVO eventVO);

    // 이벤트 정보 수정
    public int updateEventInfo (EventVO eventVO);


}
