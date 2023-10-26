package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.EventVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final SqlSessionTemplate sqlSession;

    // eventCode 생성
    @Override
    public String selectNextEventCode() {
        return sqlSession.selectOne("adminMapper.selectNextEventCode");
    }

    // 이벤트 + 이미지 등록
    @Override
    public void insertEvent(EventVO eventVO) {
        sqlSession.insert("adminMapper.insertEvent",eventVO);
        sqlSession.insert("adminMapper.insertEventImg", eventVO);

    }

    // 이벤트 목록 조회
    @Override
    public List<EventVO> selectEventList() {
        return sqlSession.selectList("adminMapper.selectEventList");
    }

    // 이벤트 상세 정보 조회
    @Override
    public EventVO selectEventDetail(String eventCode) {
        return sqlSession.selectOne("adminMapper.selectEventDetail", eventCode);
    }

    // 클릭 시 조회수 증가
    @Override
    public int updateReadCnt(String eventCode) {
        return sqlSession.update("adminMapper.updateReadCnt", eventCode);
    }

    // 이벤트 삭제
    @Override
    public int deleteEvent(String eventCode) {
        return sqlSession.delete("adminMapper.deleteEvent", eventCode);
    }

    // 저장된 이미지 리스트 조회
    @Override
    public EventVO selectImgList(EventVO eventVO) {
        return sqlSession.selectOne("adminMapper.selectImgList", eventVO);
    }

    // 이벤트 정보 수정 + 첨부파일 업로드
    @Override
    public int updateEventInfo(EventVO eventVO) {
        sqlSession.update("adminMapper.updateEventInfo", eventVO);
        sqlSession.delete("adminMapper.deleteImgInfo", eventVO);
        return sqlSession.insert("adminMapper.insertEventImg", eventVO);
    }

}
