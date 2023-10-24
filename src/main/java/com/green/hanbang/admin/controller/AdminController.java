package com.green.hanbang.admin.controller;

import com.green.hanbang.admin.service.BoardService;
import com.green.hanbang.admin.service.MemberManageService;
import com.green.hanbang.admin.service.MembershipService;
import com.green.hanbang.admin.vo.*;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberManageService memberManageService;
    private final BoardService boardService;
    private final MembershipService membershipService;

    // 관리자 페이지 ( + 맴버쉽 상품의 대분류 조회)
    @GetMapping("/manage")
    public String adminManage(Model model, MemCateVO memCateVO){
        List<MemCateVO> membershipCate = membershipService.selectCategory();
        model.addAttribute("membershipCate", membershipCate );
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
    public String realDetail(String identificationNum, Model model){
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

    // 맴버쉽 카테고리 별 상품 조회 (중분류 및 소분류 조회) --> membershipList에서 조회
    @GetMapping("/membershipList")
    public String membershipList(Model model, String memCateCode){
        List<MembershipVO> membershipList = membershipService.selectMembershipItemList(memCateCode);
        model.addAttribute("membershipList", membershipList);
        System.out.println(membershipList);
        return "admin/membershipList";
    }

    /////////////////////////////  맴버쉽 등록  ///////////////////////////////////////////////////////////////

    // 맴버쉽 등록 페이지 이동
    @GetMapping("/regMembershipForm")
    public String regMembershipForm(@RequestParam(name = "memCateCode", required = false, defaultValue = "CATE_001") String memCateCode, Model model){
        // 대분류 조회
        model.addAttribute("cateList", membershipService.selectCategory());
        // 중분류 조회
        model.addAttribute("midCateList", membershipService.selectMidCategory(memCateCode));
        System.out.println( model.addAttribute("midCateList", membershipService.selectMidCategory(memCateCode)));
        return "admin/reg_membership";
    }

    // 맴버쉽 등록 페이지 -> 대분류 클릭 시 중분류 목록 조회
    @ResponseBody
    @PostMapping("/getMidCateList")
    public Map<String, Object> getMidCateList(String memCateCode){
        //중분류조회
        List<MembershipVO> membershipList =  membershipService.selectMidCategory(memCateCode);

        //각 카테고리에 포함된 전상품 조회
        List<MemItemVO> itemList = membershipService.selectItemListByCate(memCateCode);

        Map<String, Object> map = new HashMap<>();
        map.put("membershipList", membershipList);
        map.put("itemList", itemList);

        return map;
    }

    // 맴버쉽 등록 페이지 -> 중분류 클릭 시 아이템 목록 조회
    @ResponseBody
    @PostMapping("/getItemCateList")
    public List<MemItemVO> selectItemListByMidCate(MemItemVO memItemVO){

        return membershipService.selectItemListByMidCate(memItemVO);
    }

    // 맴버쉽 등록/////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/regMembership")
    public String regMembership(MemCateVO memCateVO, MembershipVO membershipVO, MemItemVO memItemVO){
       // membershipService.insertCategory(memCateVO);
       // membershipService.insertMidCategory(membershipVO);
       // membershipService.insertItem(memItemVO);
        return "redirect:/admin/manege";
    }


    //카테고리 비동기 등록
    @ResponseBody
    @PostMapping("/insertCateFecth")
    public Map<String, Object> insertCateFecth(MemCateVO memCateVO, String type){
        Map<String, Object> map = new HashMap<>();

        if(type.equals("cate")){
            String nextCode =  membershipService.selectNextCateCode();
            memCateVO.setMemCateCode(nextCode);

            membershipService.insertCategory(memCateVO);

            //다시 카테고리 목록 조회
            map.put("data", membershipService.selectCategory());
            map.put("type", "cate");
        }
        else if(type.equals("midCate")){
            String nextCode = membershipService.selectNextMembershipCode();
            memCateVO.setMembershipCode(nextCode);


            membershipService.insertMidCategory(memCateVO);

            //다시 카테고리 목록 조회
            map.put("data", membershipService.selectMidCategory(memCateVO.getMemCateCode()));
            map.put("type", "midCate");
            map.put("memCateCode", memCateVO.getMemCateCode());
        }

        return map;
    }

    @ResponseBody
    @PostMapping("/showItemDetail")
    public List<MemItemVO> selectItemDetail(String itemCode){
        return membershipService.selectItemDetail(itemCode);
    }
}
