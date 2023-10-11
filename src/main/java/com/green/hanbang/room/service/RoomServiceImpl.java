package com.green.hanbang.room.service;

import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.PropertyTypeVO;
import com.green.hanbang.room.vo.RoomVO;
import com.green.hanbang.room.vo.TradeTypeVO;
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
    public int insertRoom(RoomVO roomVO) {
        return sqlSession.insert("roomMapper.insertRoom",roomVO);
    }
}
