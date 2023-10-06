package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<BoardVO> selectBoardList() {
        return sqlSession.selectList("adminMapper.selectBoard");
    }

    @Override
    public int insertBoard(BoardVO boardVO) {
        return sqlSession.insert("adminMapper.insertBoard",boardVO);
    }

    @Override
    public BoardVO detailBoard(int boardNum) {
        return sqlSession.selectOne("adminMapper.detailBoard",boardNum);
    }

    @Override
    public int updateBoard(BoardVO boardVO) {
        return sqlSession.update("adminMapper.updateBoard", boardVO);
    }
}
