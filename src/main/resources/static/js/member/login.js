// login.js

// 비밀번호 show, hide
$(function(){
    $("#pwShow").on("click",function(){
        $("#pwd").toggleClass('active');
        if($("#pwd").hasClass('active')){
            $('#pwd').prop('type',"text");
            $('#pwShow').attr('src', '/img/member/password_show.png');
        } else {
            $('#pwd').prop('type',"password");
            $('#pwShow').attr('src', '/img/member/password_hide.png');
        }
    });
});

// 비밀번호 찾기 버튼 클릭시 modal창 실행
$(function() {
    $("#passwordFindButton").on("click", function() {
        $("#join-modal").modal("show");
    });
});
