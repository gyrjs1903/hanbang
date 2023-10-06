package com.green.hanbang.admin.controller;

import com.green.hanbang.admin.service.BoardService;
import com.green.hanbang.admin.service.MemberManageService;
import com.green.hanbang.admin.vo.BoardVO;
import com.green.hanbang.admin.vo.MemberManageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberManageService memberManageService;
    private final BoardService boardService;

    // 관리자 페이지
    @GetMapping("/manage")
    public String adminManage(){
        return "admin/admin_manage";
    }

    // 회원 목록 페이지
    @GetMapping("/userList")
    public String userList(Model model){
        List<MemberManageVO> userList = memberManageService.userList();
        model.addAttribute("userList", userList );
        return "admin/user_list";
    }

    // 공인중개사 목록 페이지
    @GetMapping("/realList")
    public String realList(Model model){
        List<MemberManageVO> realList = memberManageService.realList();
        model.addAttribute("realList", realList );
        return "admin/real_list";
    }

    // 공인 중개사 승인
    @GetMapping("/updateAuthority")
    public String updateAuthority(MemberManageVO memberManageVO){
        memberManageService.updateAuthority(memberManageVO);
        return "redirect:/admin/realList";
    }

    // 공지사항 목록 조회
    @GetMapping("/infoBoard")
    public String selectBoardList(Model model, BoardVO boardVO){
        List<BoardVO> boardList = boardService.selectBoardList();
        model.addAttribute("boardList",boardList);
        return "admin/board_list";
    }

    // 공지사항 작성 페이지
    @GetMapping("/writeForm")
    public String writeForm(){
        return "admin/write_board";
    }

    // 공지사항 등록 페이지
    @PostMapping("/writeBoard")
    public String insertBoard(BoardVO boardVO){
        boardService.insertBoard(boardVO);
        return "redirect:/admin/infoBoard";
    }

    // 공지사항 상세 조회
    @GetMapping("/boardDetail")
    public String detailBoard(int boardNum, Model model){
        BoardVO boardVO = boardService.detailBoard(boardNum);
        model.addAttribute("boardVO", boardVO);
        return "admin/board_detail";
    }

    // 공지사항 수정 페이지로 이동
    @GetMapping("/updateBoardForm")
    public String updateBoardForm(){
        return "admin/update_board";
    }

    // 공지사항 수정하기 --> 코드 수정하기
    @PostMapping("/updateBoard")
    public String updateBoard(BoardVO boardVO){
        boardService.updateBoard(boardVO);
        return "redirect:/admin/boardDetail?boardNum=" + boardVO.getBoardNum();
    }



}
