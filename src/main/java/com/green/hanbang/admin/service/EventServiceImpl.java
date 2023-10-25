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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertEvent(EventVO eventVO) {
        sqlSession.insert("adminMapper.insertEvent",eventVO);
        sqlSession.insert("adminMapper.insertEventImg", eventVO);

    }

    // 이벤트 목록 조회
    @Override
    public List<EventVO> selectEventList() {
        return sqlSession.selectList("adminMapper.selectEventList");
    }
}
