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
    public List<MemberManageVO> userList() {
        return sqlSession.selectList("adminMapper.userManage");
    }

    @Override
    public List<MemberManageVO> realList() {
        return sqlSession.selectList("adminMapper.realManage");
    }

    @Override
    public int updateAuthority(MemberManageVO memberManageVO) {
        return sqlSession.update("adminMapper.updateAuthority", memberManageVO);
    }
}
