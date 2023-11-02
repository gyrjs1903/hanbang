/* user_info.js */

/* ----------------- 닉네임 변경 모달창 관련 --------------------- */
    /* 닉네임 변경 글자수 기능 */
    function countCharacters() {
        var inputField = document.getElementById('inputField');
        var currentLength = inputField.value.length;
        var maxLength = inputField.getAttribute('maxlength');
        var countDisplay = document.getElementById('characterCount');
        countDisplay.textContent = currentLength + ' / ' + maxLength;
    }

    // 닉네임 변경 버튼 클릭시 modal창 실행
    $(function() {
        $("#NNCBtn").on("click", function() {
            $("#NNC").modal("show");
        });
    });

    // 비밀번호 변경 버튼 클릭시 modal창 실행
    $(function() {
        $("#PWCBtn").on("click", function() {
            $("#PWC").modal("show");
        });
    });

    /* 개인정보 동의 상세보기 */
    function openNewWindow(url) {
        window.open(url, '_blank');
    };

/* ----------------- 비밀번호 변경 모달창 관련 --------------------- */
$(document).ready(function() {

    var oldVal = ""; // 이전 입력 값을 저장할 변수

    /* 기존 비밀번호 */
    $("#oldPassWord").on("propertychange change keyup paste input", async function() {
        var currentVal = $(this).val();
        if(currentVal == oldVal) {
            return;
        }
    
        oldVal = currentVal;

        var passWordFormCheck = $("#oldPassWordInputCheck");
        var passWordDuplicationCheck = $("#oldPassWordFormCheck");

        var oldPassWord = currentVal;

        /* 기존 비밀 번호 일치 확인 */
        if (oldPassWord == '') {
            passWordFormCheck.show();
            passWordDuplicationCheck.hide();
        } else if (!oldPassWord.match()) {
            
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
    /* 새로운 비밀번호 */
    $("#newPassWord").on("propertychange change keyup paste input", async function() {
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
    /* 새로운 비밀번호 확인 */
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
});

/* 비밀번호 확인 버튼 활성화/비활성화 */
    $(document).ready(function() {
        var updatePassWord = $("#updatePassWord");

        function checkInputs() {
            var oldPassWord = $("#oldPassWord");
            var newPassWord = $("#newPassWord");
            var newPassWordCheck = $("#newPassWordCheck");
            
            var is
            var isNewPasswordValid = validatePassword(newPassWord);
            var isNewPasswordMatch = newPassWord === newPassWordCheck;


        }
    });





// 비밀번호 show, hide for pwd1
$(function(){
    $("#pwShow1").on("click",function(){
        $(".pwd1").toggleClass('active');
        if($(".pwd1").hasClass('active')){
            $('.pwd1').prop('type',"text");
            $('#pwShow1').attr('src', '/img/member/password_show.png');
        } else {
            $('.pwd1').prop('type',"password");
            $('#pwShow1').attr('src', '/img/member/password_hide.png');
        }
    });
});

// 비밀번호 show, hide for pwd2
$(function(){
    $("#pwShow2").on("click",function(){
        $(".pwd2").toggleClass('active');
        if($(".pwd2").hasClass('active')){
            $('.pwd2').prop('type',"text");
            $('#pwShow2').attr('src', '/img/member/password_show.png');
        } else {
            $('.pwd2').prop('type',"password");
            $('#pwShow2').attr('src', '/img/member/password_hide.png');
        }
    });
});

// 비밀번호 show, hide for pwd3
$(function(){
    $("#pwShow3").on("click",function(){
        $(".pwd3").toggleClass('active');
        if($(".pwd3").hasClass('active')){
            $('.pwd3').prop('type',"text");
            $('#pwShow3').attr('src', '/img/member/password_show.png');
        } else {
            $('.pwd3').prop('type',"password");
            $('#pwShow3').attr('src', '/img/member/password_hide.png');
        }
    });
});