package com.green.hanbang.room.service;

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
}
