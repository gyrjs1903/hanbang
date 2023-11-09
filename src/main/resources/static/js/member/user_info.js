/* user_info.js */
/* ----------------- 기타 등등 --------------------- */
    /* 개인정보 동의 상세보기 */
    function openNewWindow(url) {
        window.open(url);
    };

const profileImg = document.getElementById("profileImg");
const proImg = document.getElementById("proImg");


// 파일이 선택되었을 때 이벤트 핸들러 등록
profileImg.addEventListener("change", function () {
    displaySelectedImages(profileImg.files[0], proImg, "프로필");
});

function displaySelectedImages(file, image, label) {
    image.innerHTML = ""; // 이미지 목록 초기화

    if (file.type.startsWith("image/")) {
        const reader = new FileReader();
        reader.onload = function (e) {
            image.src = e.target.result; // 선택한 이미지 미리보기
        }
        reader.readAsDataURL(file);
    } else {
        console.error(label + " 파일을 선택하세요.");
    }
}

/* ----------------- 닉네임 변경 모달창 관련 --------------------- */
    /* 닉네임 변경 글자수 기능 */
    function countCharacters() {
        var input = document.getElementById('nickName');
        var counter = document.getElementById('characterCount');
        if (input.value.length > 10) {
            input.value = input.value.substring(0, 10);
        } else {
            counter.textContent = input.value.length + '/10';
        }
    }

    // 닉네임 변경 버튼 클릭시 modal창 실행
    $(function() {
        $("#NNCBtn").on("click", function() {
            $("#NNC").modal("show");
        });
    });

/* ----------------- 비밀번호 변경 모달창 관련 --------------------- */
    // 비밀번호 변경 버튼 클릭시 modal창 실행
    $(function() {
        $("#PWCBtn").on("click", function() {
            $("#PWC").modal("show");
        });
    });
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
    
            /* 비밀 번호 입력값에 따른 텍스트 출력 */
            if (oldPassWord === '') {
                passWordFormCheck.show();
                passWordDuplicationCheck.hide();
            } else {
                var passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
                if (!oldPassWord.match(passwordRegex)) {
                    passWordFormCheck.hide();
                    passWordDuplicationCheck.show();
                } else {
                    passWordFormCheck.hide();
                    passWordDuplicationCheck.hide();
                }
            }
            async function checkOldPasswordOnServer(oldPassWord) {
                
                // 기존 비밀번호 일치 체크
                const response = await fetch('/member/changeForPassWordDuplicationCheckFetch', {
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    },
                    body: new URLSearchParams({
                        'oldPassWord': oldPassWord
                    })
                });
        
                if (!response.ok) {
                    alert('fetch error!\n컨트롤러로 통신 중에 오류가 발생했습니다.');
                    return;
                }
        
                const data = await response.text();
                if (data === 'valid') {
                    passWordFormCheck.hide();
                    passWordDuplicationCheck.hide();
                } else {
                    passWordFormCheck.hide();
                    passWordDuplicationCheck.show();
                }
            }
    
            checkOldPasswordOnServer(oldPassWord); // 함수 호출을 추가하여 실행하도록 함
        }); 
    });

    //     /* 새로운 비밀번호 */
    //     $("#newPassWord").on("propertychange change keyup paste input", async function() {
    //         var currentVal = $(this).val();
    //         if (currentVal === oldVal) {
    //             return;
    //         }
    
    //         oldVal = currentVal;
    
    //         /* 비밀번호 데이터 유효성 검사 정규식 */
    //         // 대문자, 소문자, 숫자, 특수 문자 각 1개 이상 포함하여 8-20자
    //         let passWordCheck = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,20}$/;
    
    //         var newPassWordInputCheck = $("#newPassWordInputCheck");
    //         var newPassWordFormCheck = $("#newPassWordFormCheck");
    
    //         /* 비밀번호 입력 값 */
    //         var newPassWord = currentVal;
    
    //         /* 비밀번호 체크 */
    //         if (passWord === '') {
    //             newPassWordInputCheck.show();
    //             newPassWordFormCheck.hide();
    //         } else if (!passWord.match(passWordCheck)) {
    //             newPassWordInputCheck.hide();
    //             newPassWordFormCheck.show();
    //         } else {
    //             try {
    //                 newPassWordInputCheck.hide();
    //                 newPassWordFormCheck.hide();
    //                 const response = await fetch('/member/newPassWordCheckFetch', {
    //                     method: 'POST',
    //                     cache: 'no-cache',
    //                     headers: {
    //                         'Content-Type': 'application/json; charset=UTF-8'
    //                     },
    //                     body: JSON.stringify({ newPassWord: newPassWord })
    //                 });
    //                 const data = await response.json();
    //                 // 서버로부터의 응답에 따른 추가 처리
    //             } catch (err) {
    //                 alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    //                 console.error(err);
    //             }
    //         }
    //     });
    
    //     /* 새로운 비밀번호 확인 */
    //     $("#passWordCheck").on("propertychange change keyup paste input", async function() {
    //         var currentVal = $(this).val();
    //         if (currentVal === oldVal) {
    //             return;
    //         }
    //         oldVal = currentVal;
    
    //         var newPassWordCheckInputCheck = $("#newPassWordCheckInputCheck");
    //         var newPassWordCheckCheck = $("#newPassWordCheckCheck");
    
    //         /* 비밀번호 확인 입력 값 */
    //         var newPassWordCheck = currentVal;
    
    //         /* 비밀번호 확인 체크 */
    //         if (passWordCheck === '') {
    //             newPassWordCheckInputCheck.show();
    //             newPassWordCheckCheck.hide();
    //         } else {
    //             try {
    //                 newPassWordCheckInputCheck.hide();
    //                 newPassWordCheckCheck.hide();
    //                 const response = await fetch('/member/newPassWordCheckCheckFetch', {
    //                     method: 'POST',
    //                     cache: 'no-cache',
    //                     headers: {
    //                         'Content-Type': 'application/json; charset=UTF-8'
    //                     },
    //                     body: JSON.stringify({ newPassWordCheck: newPassWordCheck })
    //                 });
    //                 const data = await response.json();
    //                 // 서버로부터의 응답에 따른 추가 처리
    //             } catch (err) {
    //                 alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    //                 console.error(err);
    //             }
    //         }
    //     });
    // });

/* 비밀번호 확인 버튼 활성화/비활성화 */
    // $(document).ready(function() {
    //     var updatePassWord = $("#updatePassWord");

    //     function checkInputs() {
    //         var oldPassWord = $("#oldPassWord");
    //         var newPassWord = $("#newPassWord");
    //         var newPassWordCheck = $("#newPassWordCheck");
            
    //         var is
    //         var isNewPasswordValid = validatePassword(newPassWord);
    //         var isNewPasswordMatch = newPassWord === newPassWordCheck;


    //     }
    // });


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


  function deleteMember() {
    var result = confirm("정말 탈퇴하시겠습니까?");
    if (result == true) {
      alert("탈퇴 완료.");
      window.location.href = "/member/deleteMember";
    } else {
        window.location.href = "/";
    }
  };