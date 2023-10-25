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
}
