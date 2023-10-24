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
function alarm(loginInfo,authorityAlarm,realtorInquiryList){
    console.log(loginInfo);
    console.log(realtorInquiryList);
    let alarmTag = document.querySelector('#alarm_tag');
    alarmTag.innerHTML = '';
    if(alarmTag.style.display === 'none' || alarmTag.style.display ===""){
        
        alarmTag.style.display = 'block';

        if(loginInfo == null){
            alarmTag.innerHTML = '로그인 후 사용 가능';
        } else if(loginInfo.loginType == 'USER'){
            alarmTag.innerHTML = 'user 알림';
        } else if(loginInfo.loginType == 'REALTOR'){
            //권한승인 알림
            if(authorityAlarm == 1){
                let authority = `<div th:onclick="authority(${loginInfo.userNo});">권한이 승인되었습니다.</div>`;
                alarmTag.insertAdjacentHTML('afterbegin',authority);
            } else {
                alarmTag.innerHTML = '새로운 알림이 없습니다.';
            }
            //매물문의 알림
            let realtorAlarmCnt = null;
            for(txt of realtorInquiryList.inquiryVOList){
                if(txt.inquiryAnswer != null){
                    realtorAlarmCnt += 1;
                    let realtorTxt = `<div>매물 문의가 ${realtorAlarmCnt}건 있습니다.</div>`
                    alarmTag.insertAdjacentHTML('afterbegin',realtorTxt);
                }
            }
        }
    } else {
        alarmTag.style.display = 'none';
    }
}

