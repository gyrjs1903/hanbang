package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.MemberManageVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberManageServiceImpl implements MemberManageService {
    private final SqlSessionTemplate sqlSession;


    @Override
    public List<MemberManageVO> userList(MemberManageVO memberManageVO) {
        return sqlSession.selectList("adminMapper.userManage",memberManageVO);
    }

    @Override
    public List<MemberManageVO> realList(MemberManageVO memberManageVO) {
        return sqlSession.selectList("adminMapper.realManage", memberManageVO);
    }

    @Override
    public MemberManageVO userDetail(String userNo) {
        return sqlSession.selectOne("adminMapper.userDetail", userNo);
    }

    @Override
    public MemberManageVO realDetail(String identificationNum) {
        return sqlSession.selectOne("adminMapper.realDetail", identificationNum);
    }

    @Override
    public int updateAuthority(MemberManageVO memberManageVO) {
        return sqlSession.update("adminMapper.updateAuthority", memberManageVO);
    }

    @Override
    public int deleteUser(String userNo) {
        return sqlSession.delete("adminMapper.deleteUser", userNo);
    }

    @Override
    public int deleteReal(int identificationNum) {
        return sqlSession.delete("adminMapper.deleteReal", identificationNum);
    }
}
