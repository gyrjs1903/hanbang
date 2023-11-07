let pageUrl = window.location.href;

$(window).on('load',()=>{
    if(pageUrl.indexOf('myPage') > -1 || pageUrl.indexOf('PWIdentify') > -1){
        document.querySelector('.myPage').classList.add('active');
    } else if(pageUrl.indexOf('inquiryBoardList') > -1){
        document.querySelector('.boardList').classList.add('active');
    } else if(pageUrl.indexOf('buyItemList') > -1){
        document.querySelector('.buyItemList').classList.add('active');
    }
})