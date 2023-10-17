package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberVO;
import com.green.hanbang.room.vo.PropertyTypeVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public int join(MemberVO memberVO) {
        return sqlSession.insert("memberMapper.join", memberVO);
    }

    @Override
    public int updateProImg(MemberVO memberVO) {
        return sqlSession.update("memberMapper.updateProImg");
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }

    @Override
    public MemberVO profileImgLoad(String userNo) {
        return sqlSession.selectOne("memberMapper.profileImgLoad", userNo);
    }

    @Override
    public String userNameCheck(String userName) {
        return sqlSession.selectOne("memberMapper.userNameCheck", userName);
    }

    @Override
    public int memberDelete(MemberVO memberVO) {
        return sqlSession.delete("memberMapper.memberDelete", memberVO);
    }

}
