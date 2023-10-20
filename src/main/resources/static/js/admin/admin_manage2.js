function alarm(){
    fetch('/admin2/alarm', { //요청경로
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
        let alarmTag = document.getElementById('alarm_tag');
        
        let todayAlarm = document.querySelector('#today_alarm');
        todayAlarm.textContent='';
        let alarmCnt ='';
        
        if(data.falseOfferings != undefined){
            alarmCnt = data.falseOfferings.length;
            let falseAlarm = `
                <div class="falseAlram">
                    허위매물신고가 ${alarmCnt}건 들어왔습니다.
                </div>
            `;
            todayAlarm.insertAdjacentHTML('afterbegin',falseAlarm);
        }

        if(data.realtorDetail != undefined){
            alarmCnt = data.realtorDetail.length;
            let authorityAlarm = `
                <div class="authorityAlram">
                    공인중개사 승인요청이 ${alarmCnt}건 있습니다.
                </div>
            `;
            todayAlarm.insertAdjacentHTML('afterbegin',authorityAlarm);
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}