/* main_header.js */
let alarmBox = document.querySelector('#alarm_box');
let alarmTag = document.querySelector('#alarm_tag');
let noAlarm = '<div class="noAlarm">새로운 알림이 없습니다.</div>';
let closeBtn = '';
let alarm = document.querySelector('.alarm');

function menuOpen() {
    let click = document.getElementById("my_menu_wrap");
    if (click.style.display === "none" || click.style.display === "") {

        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

function my_menu_open() {
    let click = document.getElementById("realtor_my_menu_wrap");
    if (click.style.display === "none" || click.style.display === "") {

        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}
//알림
function realtorAlarm(userNo, authorityAlarm, realtorInquiryCnt,alarmCnt) {
    console.log(authorityAlarm);
    alarmTag.innerHTML = '';
    if (alarmBox.style.display === 'none' || alarmBox.style.display === "") {

        alarmBox.style.display = 'block';

        //권한승인 알림
        if (authorityAlarm == 1) {
            let authority = `<div class="authority-alarm-wrap";><div id="authority-alarm">권한이 승인되었습니다.
                <span>다시보지않기<input type="checkbox" onclick="authority(this,${alarmCnt});"></input></span>
            </div></div>
            `;
            alarmTag.insertAdjacentHTML('afterbegin', authority);
        }
        //매물문의 알림
        if (realtorInquiryCnt != 0 && realtorInquiryCnt != null) {
            let realtorTxt = `<div class="realtorInquiry" onclick="location.href='/realtor/inquiryBoardList'">매물 문의가 ${realtorInquiryCnt}건 있습니다.</div>`
            alarmTag.insertAdjacentHTML('afterbegin', realtorTxt);
        } 
        if(authorityAlarm != 1 && realtorInquiryCnt == 0 || authorityAlarm != 1 && realtorInquiryCnt == null) {
            alarmTag.innerHTML = noAlarm;
        }
    } else {
        alarmBox.style.display = 'none';
    }
    alarm.style.color = "#fff";
    alarm.addEventListener('mouseover',()=>{
        alarm.style.color = "#bfc9d0";
    })
    alarm.addEventListener('mouseout',()=>{
        alarm.style.color = "#fff";
    })
}

//권한 승인 알림 지우기
function authority(chk,alarmCnt){
    if(chk.checked == true){
        fetch('/realtorAuthorityAlarm', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify({
            // 데이터명 : 데이터값
            })
        })
        .then((response) => {
            return response.text(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            // setTimeout(()=>{
            //     document.querySelector('#authority-alarm').slideUP();
            // },100);
            $('.authority-alarm-wrap').slideUp(400);
            if(alarmCnt != 0){
                let cngAlarmCnt = alarmCnt - 1;
                console.log(cngAlarmCnt);
                document.querySelector('.alarmCnt').innerHTML = cngAlarmCnt;
                if(cngAlarmCnt == 0){
                    document.querySelector('.alarmCnt').remove();
                    setTimeout(() => {
                        document.querySelector('.close-btn').click();
                        location.reload();
                    }, 400);
                }
            }
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
    
}

function userAlarm(userNo,nickName,userInquiryAnswer){
    alarmTag.innerHTML = '';
    if (alarmBox.style.display === 'none' || alarmBox.style.display === "") {

        alarmBox.style.display = 'block';

        //문의 답글 알림
        if (userInquiryAnswer >= 1) {
            let answer = `<div id="user-room-alarm" onclick="location.href='/member/memberCall'">
                ${nickName}님이 문의하신 글<br> ${userInquiryAnswer}개에 답글이 달렸습니다.
            </div>
            `;
            alarmTag.insertAdjacentHTML('afterbegin', answer);
        }
        if(userInquiryAnswer == 0) {
            alarmTag.innerHTML = noAlarm;
        }
    } else {
        alarmBox.style.display = 'none';
    }

}
function alarm_close(){
    alarmBox.style.display = "none";
}

//공인중개사 미승인 시 방내놓기 비활성
function authorityYet(){
    let roomReg = document.querySelector('.roomReg_a');
    roomReg.style.color = "#fff";
    roomReg.addEventListener('mouseover',()=>{
        roomReg.style.color = "#bfc9d0";
    })
    roomReg.addEventListener('mouseout',()=>{
        roomReg.style.color = "#fff";
    })
    
    alert('승인완료 후 매물 등록이 가능합니다.\n관리자에게 문의하세요.');
    location.href='/realtor/myPage';
}