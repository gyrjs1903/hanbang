package com.green.hanbang.admin.service;

import com.green.hanbang.admin.vo.BoardVO;

import java.util.List;

public interface BoardService {

    // 공지사항 조회하기
    public List<BoardVO> selectBoardList();

    // 공지사항 등록하기
    public int insertBoard(BoardVO boardVO);

    // 공지사항 상세 조회
    public BoardVO detailBoard(int boardNum);

    // 공지사항 수정하기
    public int updateBoard(BoardVO boardVO);

    // 삭제
    public int deleteBoard(int boardNum);
}
