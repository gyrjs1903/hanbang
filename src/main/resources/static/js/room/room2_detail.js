let myModal = null;
let myModal2 = null;

// 옵션값 없을 시 '없음'표시
window.addEventListener('load', () => {
    // 인증완료 후 띄울 모달 창
    myModal = new bootstrap.Modal('#report-inner-modal');
    myModal2 = new bootstrap.Modal('#elDAS-modal');

    fetch('/room2/roomDetailFetch', { //요청경로
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
        for(option of data){
            if(option == 'DO_035' || option == 'DO_036'){
                document.querySelector('#balcony-check').innerHTML = '있음';
            }
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });  
});

//본인확인하기
function elDAS(){
    let userName = document.querySelector('#elDAS-userName').value;
    let passWord = document.querySelector('#elDAS-password').value;
    console.log(userName);
    console.log(passWord);
    fetch('/room2/elDAS', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            userName : userName,
            passWord : passWord
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        if(data){
            myModal2.hide();
            myModal.show();
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });  
}

//허위매물 신고하기


