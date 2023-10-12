package com.green.hanbang.room.service;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl2 implements RoomService2{
    private final SqlSessionTemplate sqlSession;
}
