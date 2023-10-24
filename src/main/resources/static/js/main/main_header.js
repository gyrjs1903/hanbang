/* main_header.js */

function menuOpen(){
    let click = document.getElementById("my_menu_wrap");
    if(click.style.display === "none" || click.style.display === ""){

        click.style.display = "block";

    } else{
        click.style.display = "none";

    }
}

//알림
//authorityAlarm 안넘어옴
//수정필요
function alarm(loginInfo,authorityAlarm){
    console.log(loginInfo);
    console.log(authorityAlarm)
    let alarmTag = document.querySelector('#alarm_tag');
    if(alarmTag.style.display === 'none' || alarmTag.style.display ===""){
        
        alarmTag.style.display = 'block';

        if(loginInfo == null){
            alarmTag.innerHTML = '로그인 후 사용 가능';
        } else if(loginInfo.loginType == 'USER'){
            alarmTag.innerHTML = 'user 알림';
        } else if(loginInfo.loginType == 'REALTOR'){
            // if(authorityAlarm == 1){
            //     let authority = `<div th:onclick="authority(${loginInfo.userNo});">권한이 승인되었습니다.</div>`;
            //     alarmTag.innerHTML = '권한이 승인되었습니다.';
            // } else {
            //     alarmTag.innerHTML = '새로운 알림이 없습니다.';
            // }
        }
    } else {
        alarmTag.style.display = 'none';
    }
}

