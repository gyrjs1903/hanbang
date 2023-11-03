package com.green.hanbang.realtor.vo;

import lombok.NoArgsConstructor;


public class PageVO {
    private int nowPage;
    private int totalDataCnt;
    private int displayDataCnt;
    private int beginPage;
    private int endPage;
    private int displayPageCnt;
    private boolean prev;
    private boolean next;

    public PageVO(){
        nowPage = 1;
        displayPageCnt = 5;
        displayDataCnt = 10;
    }

    //페이징 처리를 하기 위한 모든 변수의 값을 세팅하는 메소드
    public void setPageInfo(){
        //전체 페이지 수
        int totalPageCnt = (int) Math.ceil(totalDataCnt / (double)displayDataCnt);

            endPage = (int) (Math.ceil(nowPage / (double) displayPageCnt)) * displayPageCnt;
            beginPage = endPage - displayPageCnt + 1;




        //next 버튼 유무
        if(endPage < totalPageCnt){
            next = true;
        } else {
            next = false;
            endPage = totalPageCnt;
        }

        //prev 버튼 유무
        prev = beginPage != 1;
    }

    //전체데이터수 setter
    public void setTotalDataCnt(int totalDataCnt){
        this.totalDataCnt = totalDataCnt;
    }

    //한페이지에 보이는 데이터 수 getter
    public int getDisplayDataCnt(){
        return displayDataCnt;
    }

    //현재페이지 getter
    public int getNowPage(){
        return nowPage;
    }

    //현재페이지 setter
    public void setNowPage(int nowPage){
        this.nowPage = nowPage;
    }

    //beginPage getter
    public int getBeginPage(){
        return beginPage;
    }

    //endPage getter
    public int getEndPage(){
        return endPage;
    }

    public boolean getPrev(){
        return prev;
    }

    public boolean getNext(){
        return next;
    }
}

