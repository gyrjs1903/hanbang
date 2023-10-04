package com.green.hanbang.realtor.service;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealtorServiceImpl implements RealtorService{
    private final SqlSessionTemplate sqlSession;
}
