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
    @RequestMapping(value = "/userList")
    public String userList(Model model, MemberManageVO memberManageVO){
        List<MemberManageVO> userList = memberManageService.userList(memberManageVO);
        model.addAttribute("userList", userList );
        return "admin/user_list";
    }

    // 회원 상세 조회
    @GetMapping("/userDetail")
    public String userDetail(String userNo, Model model){
        MemberManageVO userDetail = memberManageService.userDetail(userNo);
        model.addAttribute("userDetail", userDetail);
        return "admin/user_detail";
    }

    // 회원 삭제하기
    @GetMapping("/deleteUser")
    public String deleteUser(String userNo){
        memberManageService.deleteUser(userNo);
        return "redirect:/admin/userList";
    }
    // 공인중개사 목록 페이지
    @RequestMapping(value = "/realList")
    public String realList(Model model, MemberManageVO memberManageVO){
        List<MemberManageVO> realList = memberManageService.realList(memberManageVO);
        model.addAttribute("realList", realList );
        return "admin/real_list";
    }

    // 공인중개사 상세 조회
    @GetMapping("/realDetail")
    public String realDetail(int identificationNum, Model model){
        MemberManageVO realDetail = memberManageService.realDetail(identificationNum);
        model.addAttribute("realDetail", realDetail);
        return "admin/real_detail";
    }

    // 공인중개사 삭제
    @GetMapping("/deleteReal")
    public String deleteReal(int identificationNum){
        memberManageService.deleteReal(identificationNum);
        return "redirect:/admin/realList";
    }
    // 공인 중개사 승인
    @GetMapping("/updateAuthority")
    public String updateAuthority(MemberManageVO memberManageVO){
        memberManageService.updateAuthority(memberManageVO);
        return "redirect:/admin/realDetail";
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
    public String updateBoardForm(int boardNum, Model model){
        BoardVO boardInfo = boardService.detailBoard(boardNum);
        model.addAttribute("board", boardInfo);
        return "admin/update_board";
    }

    // 공지사항 수정하기
    @PostMapping("/updateBoard")
    public String updateBoard(BoardVO boardVO){
        boardService.updateBoard(boardVO);
        return "redirect:/admin/boardDetail?boardNum=" + boardVO.getBoardNum();
    }

    // 공지사항 삭제하기
    @GetMapping("/deleteBoard")
    public String deleteBoard(int boardNum){
        boardService.deleteBoard(boardNum);
        return "redirect:/admin/infoBoard";
    }



}
