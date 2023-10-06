/* join.js */

/* 회원가입 */
function joinCheck(){
    /* 이메일(아이디) 데이터 유효성 검사 정규식 */ 
    var userNameCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

    /* 이메일(아이디) 입력 값 */
    const userName = document.querySelector('#userName');

    /* 이메일(아이디) 체크 비동기 통신 */
    fetch('/member/userNameChekFetch', {
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(userName)
    })
    .then((response) => {
        return response.json();
    })
    .then((data) => {
        /* 입력란 공백 시 텍스트 출력 */
        if(joinForm.userName.value == ''){
            document.getElementById("userNameInputCheck").style.display = 'none';
            document.getElementById("userNameFormCheck").style.display = 'block';
            return ;
        }
        /* 입력 형식에 맞지 않을 시 텍스트 출력 */
        else if(joinForm.userName.value == userNameCheck){
            document.getElementById("userNameFormCheck").style.display = 'none';
            document.getElementById("userNameInputCheck").style.display = 'block';
            return ;
        }
    })
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

    /* 비밀번호 데이터 유효성 검사 정규식 */
    // 문자, 숫자, 특수문자 포함 8-20자 이내
    let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/

    /* 비밀번호 입력 값 */
    const passWord = document.querySelector('#passWord');

    /* 비밀번호 체크 비동기 통신 */
    fetch('/member/passWordCheckFetch', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(passWordCheck)
    })
    .then((response) => {
        return response.json();
    })
    .then((data) => {
        /* 입력란 공백 시 텍스트 출력 */
        if(joinForm.userName.value == ''){
            document.getElementById("passWordFormCheck").style.display = 'none';
            document.getElementById("passWordInputCheck").style.display = 'block';
            return ;
        }
        /* 입력 형식에 맞지 않을 시 텍스트 출력 */
        else if(joinForm.userName.value == userNameCheck){
            document.getElementById("passWordInputCheck").style.display = 'none';
            document.getElementById("passWordFormCheck").style.display = 'block';
            return ;
        }
    })
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

    /* 비밀번호 확인 입력 값 */
    const passWordCheck = document.querySelector("#passWordCheck")

    /* 비밀번호 확인 체크 비동기 통신 */
    fetch('/member/passWordCheckCheckFetch', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(passWordCheck)
    })
    .then((response) => {
        return response.json();
    })
    .then((data) => {
        /* 입력란 공백 시 텍스트 출력 */
        if(joinForm.userName.value == ''){
            document.getElementById("passWordCheckCheck").style.display = 'none';
            document.getElementById("passWordCheckInputCheck").style.display = 'block';
            return ;
        }
        /* 입력 형식에 맞지 않을 시 텍스트 출력 */
        else if(joinForm.userName.value == userNameCheck){
            document.getElementById("passWordCheckInputCheck").style.display = 'none';
            document.getElementById("passWordCheckCheck").style.display = 'block';
            return ;
        }
    })
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

/* 회원가입 시 이메일(아이디) 중복 체크 */
function userNameDuplicationCheck(){
    fetch('/member/userNameDuplicationCheckfetch', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
           'userName' : document.querySelector('#userName').value
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
        return response.json(); //return response.text();
    })
    .then((data) => {
        if(data){
            alert('회원가입을 환영합니다.');
            document.querySelector('#join-btn').disabled = false;
            
            location.href='/main/home';
        }
        else{
            alert('이미 가입된 이메일입니다.');
        }
    })
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}