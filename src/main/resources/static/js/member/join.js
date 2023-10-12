/* join.js */

/* 입력 값에 따른 오류 텍스트 출력하는 함수 */
$(document).ready(function() {

    var oldVal = ""; // 이전 입력 값을 저장할 변수
    // 가입 버튼
    var joinButton = $("#join-btn");

    /* 이메일(아이디) */ /* input 태그 text 타입 실시간 값 변경 감지 */
    $("#userName").on("propertychange change keyup paste input", async function() {
        var currentVal = $(this).val();
        if(currentVal == oldVal) {
            return;
        }

        oldVal = currentVal;

        // 이메일(아이디) 데이터 유효성 검사 정규식
        var userNameCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

        var userNameFormCheck = $("#userNameFormCheck");
        var userNameInputCheck = $("#userNameInputCheck");
        var userNameDuplication = $("#userNameDuplication");

        /* 이메일(아이디) 입력 값 */
        var userName = currentVal;

        /* 이메일(아이디) 체크 */
        if (userName == '') {
            userNameFormCheck.hide();
            userNameInputCheck.show();
            userNameDuplication.hide();
        } else if (!userName.match(userNameCheck)) {
            userNameFormCheck.show();
            userNameInputCheck.hide();
            userNameDuplication.hide();
        } else {
            // 이메일(아이디) 중복 체크
            try {
                userNameFormCheck.hide();
                userNameInputCheck.hide();
                const response = await fetch('/member/userNameCheckFetch', {
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' // application/json
                    },
                    body: JSON.stringify({ userName: userName })
                });
                const data = await response.json(userName);
                if (data.isDuplicate) {
                    userNameDuplication.show();
                }
            } catch(err) {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            }
        }
    });

    /* 비밀번호 */
    $("#passWord").on("propertychange change keyup paste input", async function() {
        var currentVal = $(this).val();
        if(currentVal == oldVal) {
            return;
        }

        oldVal = currentVal;
    
        /* 비밀번호 데이터 유효성 검사 정규식 */
        // 문자, 숫자, 특수문자 포함 8-20자 이내
        let passWordCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;

        var passWordFormCheck = $("#passWordFormCheck");
        var passWordInputCheck = $("#passWordInputCheck");

        /* 비밀번호 입력 값 */
        var passWord = currentVal;

        /* 비밀번호 체크 */
        if (passWord == '') {
            passWordFormCheck.hide();
            passWordInputCheck.show();
        } else if (!passWord.match(passWordCheck)) {
            passWordFormCheck.show();
            passWordInputCheck.hide();
        } else {
            try {
                passWordFormCheck.hide();
                passWordInputCheck.hide();
                const response = await fetch('/member/passWordCheckFetch', {
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/json; charset=UTF-8'
                    },
                    body: JSON.stringify({ passWord: passWord })
                });
                const data = await response.json();
            } catch (err) {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
                }
            }
    });

    /* 비밀번호 확인 */
    $("#passWordCheck").on("propertychange change keyup paste input", async function() {
        var currentVal = $(this).val();
        if (currentVal == oldVal) {
            return;
        }
            oldVal = currentVal;

        var passWordCheckInputCheck= $("#passWordCheckInputCheck");
        var passWordFormCheck = $("#passWordCheckCheck");

        /* 비밀번호 확인 입력 값 */
        var passWordCheck = currentVal;

        /* 비밀번호 확인 체크 */
        if (passWordCheck == '') {
            passWordFormCheck.hide();
            passWordCheckInputCheck.show();
        } else {
            try {
                passWordFormCheck.show();
                passWordCheckInputCheck.hide();
                const response = await fetch('/member/passWordCheckCheckFetch', {
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/json; charset=UTF-8'
                    },
                    body: JSON.stringify({ passWordCheckCheck: passWordCheck })
                });
                const data = await response.json();
            } catch (err) {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            }
        }
    });

    $(document).ready(function() {
        
        var oldVal = "";
    
        // 비밀번호 필드
        var passWordField = $("#passWord");
        var passWordFormCheck = $("#passWordFormCheck");
        var passWordInputCheck = $("#passWordInputCheck");
    
        // 비밀번호 확인 필드
        var passWordCheckField = $("#passWordCheck");
        var passWordCheckInputCheck = $("#passWordCheckInputCheck");
        var passWordCheckCheck = $("#passWordCheckCheck");
    
        // 이벤트 핸들러
        passWordCheckField.on("propertychange change keyup paste input", async function() {
            var currentVal = $(this).val();
            if (currentVal == oldVal) {
                return;
            }
            oldVal = currentVal;
    
            // 비밀번호 입력 값
            var passWord = passWordField.val();
            var passWordCheck = currentVal;
    
            // 비밀번호 확인 체크
            if (passWordCheck === '') {
                passWordCheckCheck.hide();
                passWordCheckInputCheck.show();
            } else if (passWord !== passWordCheck) {
                passWordCheckCheck.show();
                passWordCheckInputCheck.hide();
            } else {
                passWordCheckCheck.hide();
                passWordCheckInputCheck.hide();
            }
        });
    });

    function validateInputFields() {
        var isUserNameValid = false;
        var isPassWordValid = false;
        var isPassWordCheckValid = false;
    
        // ... (유효성 검사 로직)
    
        // 이메일(아이디) 유효성 검사 후 변수 업데이트
        if (userName == '') {
            isUserNameValid = false;
        } else if (!userName.match(userNameCheck)) {
            isUserNameValid = false;
        } else {
            // 이메일(아이디) 중복 체크 로직
            if (data.isDuplicate) {
                isUserNameValid = false;
            } else {
                isUserNameValid = true;
            }
        }
    
        // 비밀번호 유효성 검사 후 변수 업데이트
        if (passWord == '') {
            isPassWordValid = false;
        } else if (!passWord.match(passWordCheck)) {
            isPassWordValid = false;
        } else {
            isPassWordValid = true;
        }
    
        // 비밀번호 확인 체크 후 변수 업데이트
        if (passWordCheck === '') {
            isPassWordCheckValid = false;
        } else if (passWord !== passWordCheck) {
            isPassWordCheckValid = false;
        } else {
            isPassWordCheckValid = true;
        }
    
        // 모든 필드가 유효한지 확인
        if (isUserNameValid && isPassWordValid && isPassWordCheckValid) {
            joinButton.prop("disabled", false); // 버튼 활성화
        } else {
            joinButton.prop("disabled", true); // 버튼 비활성화
        }
    }
        // 입력 필드 값이 변경될 때 유효성 검사 함수 호출
        userNameField.on("propertychange change keyup paste input", validateInputFields);
        passWordField.on("propertychange change keyup paste input", validateInputFields);
        passWordCheckField.on("propertychange change keyup paste input", validateInputFields);
        
        // 초기 상태에서는 버튼 비활성화
        joinButton.prop("disabled", true);
});