/* join.js */

/* 입력 값에 따른 오류 텍스트 출력하는 함수 */
$(document).ready(function() {

    var oldVal = ""; // 이전 입력 값을 저장할 변수

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
            userNameDuplicationCheckFetch();
        }
        async function userNameDuplicationCheckFetch() {
            // 회원가입 시 아이디 중복 체크
            const response = await fetch('/member/userNameDuplicationCheckFetch', {
                method: 'POST',
                cache: 'no-cache',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                body: new URLSearchParams({
                    'userName': document.querySelector('#userName').value
                })
            });
    
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신 중에 오류가 발생했습니다.');
                return;
            }
    
            const data = await response.text();
            if (data) {
                userNameFormCheck.hide();
                userNameInputCheck.hide();
                userNameDuplication.show();
            } else {
                userNameFormCheck.hide();
                userNameInputCheck.hide();
                userNameDuplication.hide();
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
                    body: JSON.stringify({passWord: passWord })
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
                    body: JSON.stringify({passWordCheckCheck: passWordCheck })
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
            } else{
                passWordCheckCheck.hide();
                passWordCheckInputCheck.hide();
            }
        });
    });

    $(document).ready(function() {
        
        var oldVal = "";
    
        // 비밀번호 확인 필드
        var passWordCheckField = $("#passWordCheck");
        var passWordCheckCheck = $("#passWordCheckCheck");
    
        // 비밀번호 필드
        var passWordField = $("#passWord");

        // 이벤트 핸들러
        passWordField.on("propertychange change keyup paste input", async function() {
            var currentVal = $(this).val();
            if (currentVal == oldVal) {
                return;
            }

            oldVal = currentVal;
    
            // 비밀번호 입력 값
            var passWordCheck = passWordCheckField.val();
            var passWord= currentVal;
    
            // 비밀번호 체크
            if (passWord === '') {
                passWordCheckCheck.hide();
                passWordCheckInputCheck.hide();
            } else if (passWordCheck !== passWord) {
                passWordCheckCheck.show();
                passWordCheckInputCheck.hide();
            } else{
                passWordCheckCheck.hide();
                passWordCheckInputCheck.hide();
            }
        });
    });

    /* 회원가입 버튼 활성화/비활성화 */
    $(document).ready(function() {
        var joinBtn = $("#join-btn"); // 회원가입 버튼

        function checkInputs() {
            var userName = $("#userName").val();
            var passWord = $("#passWord").val();
            var passWordCheck = $("#passWordCheck").val();

            var isUserNameValid = validateUserName(userName);
            var isPasswordValid = validatePassword(passWord);
            var isPasswordMatch = passWord === passWordCheck;

            var isUserNameDuplicationValid = !$("#userNameDuplication").is(':visible');

            var isAllInputsFilled = userName !== '' && passWord !== '' && passWordCheck !== '';
            if (isUserNameValid && isPasswordValid && isPasswordMatch && isUserNameDuplicationValid && isAllInputsFilled) {
                joinBtn.prop('disabled', false);
            } else {
                joinBtn.prop('disabled', true);
            }
        }

        function validateUserName(userName) {
            var userNameCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
            return userName !== '' && userName.match(userNameCheck);
        }

        function validatePassword(password) {
            var passWordCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
            return password === '' || password.match(passWordCheck);
        }

        $("#userName, #passWord, #passWordCheck, #userNameDuplication").on("propertychange change keyup paste input", function() {
            checkInputs();
        });

        joinBtn.prop('disabled', true); // 페이지 접근 초기 버튼 비활성화 고정
    });
});

$(document).ready(function(){
    $('.passWordArea1 i').on('click',function(){
        $('input').toggleClass('active');
        if($('input').hasClass('active')){
            $(this).attr('class',"fa fa-eye-slash fa-lg")
            .prev('input').attr('type',"text");
        }else{
            $(this).attr('class',"fa fa-eye fa-lg")
            .prev('input').attr('type','password');
        }
    });
});

$(document).ready(function(){
    $('.passWordArea2 i').on('click',function(){
        $('input').toggleClass('active');
        if($('input').hasClass('active')){
            $(this).attr('class',"fa fa-eye-slash fa-lg")
            .prev('input').attr('type',"text");
        }else{
            $(this).attr('class',"fa fa-eye fa-lg")
            .prev('input').attr('type','password');
        }
    });
});
