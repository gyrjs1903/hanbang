package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberSaveVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveServiceServiceImpl implements SaveService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public int insertSaveRoom(MemberSaveVO memberSaveVO) {
        return sqlSession.insert("saveMapper.insertSaveRoom", memberSaveVO);
    }

    @Override
    public int insertSaveApart(MemberSaveVO memberSaveVO) {
        return sqlSession.insert("saveMapper.insertSaveApart", memberSaveVO);
    }

    @Override
    public List<MemberSaveVO> selectSaveRoomList(String userNo) {
        return sqlSession.selectList("saveMapper.selectSaveRoomList", userNo);
    }

    @Override
    public List<MemberSaveVO> selectSaveApartList() {
        return sqlSession.selectList("saveMapper.selectSaveApartList");
    }

    @Override
    public boolean checkIfSaved(MemberSaveVO memberSaveVO) {
        return sqlSession.selectOne("saveMapper.checkIfSaved", memberSaveVO);
    }

    @Override
    public void deleteSavedItem(MemberSaveVO memberSaveVO) {
        sqlSession.delete("saveMapper.deleteSavedItem", memberSaveVO);
    }
}
