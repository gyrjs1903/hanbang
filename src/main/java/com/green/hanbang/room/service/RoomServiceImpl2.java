package com.green.hanbang.room.service;

import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.RoomVO;
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
}
