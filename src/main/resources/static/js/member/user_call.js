
function inquiryAnswer(td,inquiryCode) {
    let answer = td.parentElement.nextElementSibling;
    console.log(answer.classList.contains('room-inquiry-answer'));
    if(answer.classList.contains('toggle-tr')){
        if(answer.classList.contains('room-inquiry-answer')){
            console.log(inquiryCode);
            answer.classList.remove('room-inquiry-answer');
            fetch('/member/readInquiryAnswer', { //요청경로
                method: 'POST',
                cache: 'no-cache',
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                },
                //컨트롤러로 전달할 데이터
                body: JSON.stringify({
                    inquiryCode : inquiryCode
                })
            })
            .then((response) => {
                return response.text(); //나머지 경우에 사용
            })
            //fetch 통신 후 실행 영역
            .then((data) => {//data -> controller에서 리턴되는 데이터!
                let userInquiryCnt = document.querySelector('.userInquiryCnt');
                if(userInquiryCnt != null){
                    if(parseInt(userInquiryCnt.textContent) > 0){
                        userInquiryCnt.innerHTML = parseInt(userInquiryCnt.textContent)-1;
                        if(parseInt(userInquiryCnt.textContent) == 0){
                            document.querySelector('.alarmCnt').remove();
                            document.querySelector('#alarm_tag').innerHTML = '<div class="noAlarm">새로운 알림이 없습니다.</div>';
                        }
                    }
                }
               
                const originData = parseInt(document.querySelector('.reduceCnt').value);

                document.querySelector('.reduceCnt').value = originData + 1;

            })
            //fetch 통신 실패 시 실행 영역
            .catch(err=>{
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
            
        } else {
            answer.classList.add('room-inquiry-answer');
        }
    }
}