package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberImgVO;
import com.green.hanbang.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public int join(MemberVO memberVO) {
        return sqlSession.insert("memberMapper.join", memberVO);
    }

    @Override
    public int insertProImg(MemberImgVO memberImgVO) {
        return sqlSession.insert("memberMapper.insertProImg", memberImgVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }

    @Override
    public String userNameCheck(String userName) {
        return sqlSession.selectOne("memberMapper.userNameCheck", userName);
    }

    @Override
    public int memberDelete(MemberVO memberVO) {
        return sqlSession.delete("memberMapper.memberDelete", memberVO);
    }

    @Override
    public String selectUserNo(String userNo) {
        return sqlSession.selectOne("memberMapper.selectUserNo", userNo);
    }

    @Override
    public String selectUserInfo(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.selectUserInfo", memberVO);
    }

    @Override
    public MemberVO profileImgLoad(String userNo) {
        return sqlSession.selectOne("memberMapper.profileImgLoad", userNo);
    }

}
