/* join.js */

/* 닉네임 자동 생성 기능 구현 */
function createNickName(){
    const adjective = ['가냘픈', '가는', '가엾은', '가파른', '같은', '거센', '거친', '검은', '게으른', '고달픈', '고른', '고마운', '고운', '고픈', '곧은', '괜찮은', '구석진'
, '굳은', '굵은', '귀여운', '그런', '그른', '그리운', '기다란', '기쁜', '긴', '깊은', '깎아지른', '깨끗한', '나쁜', '나은', '난데없는', '날랜', '날카로운', '어둠의'
, '낮은', '너그러운', '너른', '널따란', '넓은', '네모난', '노란', '높은', '누런', '눅은', '느닷없는', '느린', '늦은', '다른', '더러운', '더운', '덜된', '지속되는'
, '동그란', '돼먹잖은', '둥그런', '둥근', '뒤늦은', '드문', '딱한', '때늦은', '뛰어난', '뜨거운', '막다른', '많은', '매운', '먼', '멋진', '메마른', '가까운', '침착한'
, '메스꺼운', '모난', '못난', '못된', '못생긴', '무거운', '무딘', '무른', '무서운', '미끄러운', '미운', '바람직한', '반가운', '밝은', '밤늦은', '보드라운', '화난'
, '보람찬', '부드러운', '부른', '붉은', '비싼', '빠른', '빨간', '뻘건', '뼈저린', '뽀얀', '뿌연', '새로운', '서툰', '섣부른', '설운', '성가신', '센', '빛나는'
, '수줍은', '쉬운', '스스러운', '슬픈', '시원찮은', '싫은', '싼', '쌀쌀맞은', '쏜살같은', '쓰디쓴', '쓰린', '쓴', '아니꼬운', '아닌', '아름다운', '아쉬운', '사라진'
, '아픈', '안된', '안쓰러운', '안타까운', '않은', '알맞은', '약빠른', '약은', '얇은', '얕은', '어두운', '어려운', '어린', '언짢은', '엄청난', '없는', '여문', '힘쎈'
, '열띤', '예쁜', '올바른', '옳은', '외로운', '우스운', '의심스런', '이른', '익은', '있는', '작은', '잘난', '잘빠진', '잘생긴', '재미있는', '적은', '젊은', '늙은'
, '점잖은', '조그만', '좁은', '좋은', '주제넘은', '줄기찬', '즐거운', '지나친', '지혜로운', '질긴', '짓궂은', '짙은', '짠', '짧은', '케케묵은', '큰', '영광의'
, '탐스러운', '턱없는', '푸른', '한결같은', '흐린', '희망찬', '흰', '힘겨운', '사랑하는', '증오하는', '끔찍한', '하얀', '파란', '빨간', '찬란한', '약한'];
    const word = ['고양이', '강아지', '거북이', '토끼', '뱀', '사자', '호랑이', '표범', '치타', '하이에나', '기린', '코끼리', '코뿔소', '하마', '악어', '펭귄', '부엉이'
, '올빼미', '곰', '돼지', '소', '닭', '독수리', '타조', '고릴라', '오랑우탄', '침팬지', '원숭이', '코알라', '캥거루', '고래', '상어', '칠면조', '직박구리'
, '쥐', '청설모', '메추라기', '앵무새', '삵', '스라소니', '판다', '오소리', '오리', '거위', '백조', '두루미', '고슴도치', '두더지', '우파루파', '맹꽁이'
, '너구리', '개구리', '두꺼비', '카멜레온', '이구아나', '노루', '제비', '까치', '고라니', '수달', '당나귀', '순록', '염소', '공작', '바다표범', '들소'
, '박쥐', '참새', '물개', '바다사자', '살모사', '구렁이', '얼룩말', '산양', '멧돼지', '카피바라', '도롱뇽', '북극곰', '퓨마', '미어캣', '코요테', '라마'
, '딱따구리', '기러기', '비둘기', '스컹크', '돌고래', '까마귀', '매', '낙타', '여우', '사슴', '늑대', '재규어', '알파카', '양', '다람쥐', '담비', '사막여우'
, '젖소', '개미', '꿀벌', '물고기', '담요', '책', '책더미', '날개', '번개', '해', '햇볕', '숲', '나무', '바다', '절벽', '파도', '눈사람', '똥']
    const randomAdjective = adjective[Math.floor(Math.random() * adjective.length)];
    const randomWord = word[Math.floor(Math.random() * word.length)];

    return randomAdjective + randomWord;
}

document.addEventListener('DOMContentLoaded', function () {
    const joinBtn = document.getElementById('join-btn');
    const nicknameInput = document.getElementById('nickName');


    joinBtn.addEventListener('click', function () {
        const generatedNickName = createNickName();
        nicknameInput.value = generatedNickName;

        const formData = new FormData();
        formData.append('nickname', generatedNickName);

    });
});


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
            var loginType = $("#loginType").val();

            var isUserNameValid = validateUserName(userName);
            var isPasswordValid = validatePassword(passWord);
            var isPasswordMatch = passWord === passWordCheck;

            var isUserNameDuplicationValid = !$("#userNameDuplication").is(':visible');

            var isAllInputsFilled = userName !== '' && passWord !== '' && passWordCheck !== '';
    
            //var isLoginTypeSelected = loginType === 'NULL';

            if (isUserNameValid && isPasswordValid && isPasswordMatch && isUserNameDuplicationValid && isAllInputsFilled) {
                // && isLoginTypeSelected
                joinBtn.prop('disabled', false);
                joinBtn.removeClass("disabled-button");
                joinBtn.addClass("enabled-button");
            } else {
                joinBtn.prop('disabled', true);
                joinBtn.removeClass("enabled-button");
                joinBtn.addClass("disabled-button");
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

        // 초기 페이지 접근 버튼 비활성화 고정
        joinBtn.prop('disabled', true);
        //joinBtn.addClass("disabled-button");
    });
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
