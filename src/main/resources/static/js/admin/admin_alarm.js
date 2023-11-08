let adminAlarmTag = document.getElementById('admin_alarm_tag');

function alarm(){
    if(adminAlarmTag.style.display === "none" || adminAlarmTag.style.display === ""){

        adminAlarmTag.style.display = "block";

    } else{
        adminAlarmTag.style.display = "none";
    }
    fetch('/admin/alarm', { //요청경로
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
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        console.log(Object.keys(data).length);
        let adminTodayAlarm = document.querySelector('#admin_today_alarm');
        adminTodayAlarm.textContent='';
        let alarmCnt ='';
        if(Object.keys(data).length == 0){
            let noneAlarm = `
                <div>
                    새로운 알림이 없습니다.
                </div>
            `;
            adminTodayAlarm.insertAdjacentHTML('afterbegin',noneAlarm);
        }
        if(data.falseOfferings != undefined){
            alarmCnt = data.falseOfferings.length;
            let falseAlarm = `
                <div class="falseAlram cursor" onclick="location.href='/admin/falseOfferingsList'";>
                    허위매물신고가 <span class="cnt">${alarmCnt}</span>건 들어왔습니다.
                </div>
            `;
            adminTodayAlarm.insertAdjacentHTML('afterbegin',falseAlarm);
        }

        if(data.realtorDetail != undefined){
            alarmCnt = data.realtorDetail.length;
            let authorityAlarm = `
                <div class="authorityAlram cursor" onclick="location.href='/admin/realList';">
                    공인중개사 승인요청이 <span class="cnt">${alarmCnt}</span>건 있습니다.
                </div>
            `;
            adminTodayAlarm.insertAdjacentHTML('afterbegin',authorityAlarm);
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function admin_alarm_close(){
    adminAlarmTag.style.display = "none";
}