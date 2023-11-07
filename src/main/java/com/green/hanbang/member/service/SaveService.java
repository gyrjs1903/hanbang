package com.green.hanbang.member.service;

import com.green.hanbang.member.vo.MemberSaveVO;

import java.util.List;

public interface SaveService {

    // 찜하기 (원룸, 오피스텔)
    public int insertSaveRoom(MemberSaveVO memberSaveVO);

    // 찜하기 (아파트)
    public int insertSaveApart(MemberSaveVO memberSaveVO);

    // 찜목록 (원룸, 오피스텔)
    public List<MemberSaveVO> selectSaveRoomList(String userNo);

    // 찜목록 (아파트)
    public List<MemberSaveVO> selectSaveApartList();

    // 이미 찜한 매물 확인
    public boolean checkIfSaved(MemberSaveVO memberSaveVO);

    // 찜취소
    public void deleteSavedItem(MemberSaveVO memberSaveVO);

}
