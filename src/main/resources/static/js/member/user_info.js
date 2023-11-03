/* user_info.js */
/* ----------------- 기타 등등 --------------------- */
    /* 개인정보 동의 상세보기 */
    function openNewWindow(url) {
        window.open(url);
    };



/* ----------------- 프로필 사진 관련--------------------- */
    // const profileImage = document.getElementById("proImg"); // <img>
    // const imageInput = document.getElementById("profileImg"); // <input>

    // let initCheck; // 초기 프로필 이미지 상태를 저장하는 변수

    // let deleteCheck = -1;

    // let originalImage; // 초기 프로필 이미지 파일 경로 저장

    // if(imageInput != null){

    //     // 프로필 이미지가 출력되는 img 태그의 src
    //     originalImage = profileImage.getAttribute("src");

    //     // 서버로부터 이미지 URL 받아오기
    //     fetch('/member/getProfileImageUrlFetch', {
    //         method: 'POST',
    //         cache: 'no-cache',
    //         headers: {
    //             'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    //         },
    //     })
    //     .then((response) => {
    //         if(!response.ok){
    //             alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
    //             return ;
    //         }
    //         return response.json();
    //     })
    //     .then((data) => {
            
    //         const originalImageUrl = data.imageURL;

    //         // 회원 프로필 화면 진입 시
    //         // 현재 회원의 프로필 이미지 상태 확인
    //         if(originalImage == originalImageUrl){
    //             // 기본 이미지인 경우
    //             initCheck = false;
    //         } else {
    //             initCheck = true;
    //         }

    //         imageInput.addEventListener("change", e => {
    //             // 2MB로 최대 크기 제한 (파일 최대 크기 지정 byte)
    //             const maxSize = 1 * 1024 * 1024 * 2;

    //             // 업로드한 파일의 정보가 담긴 객체
    //             const file = e.target.files[0];

    //             // 선택된 파일의 크기가 최대 크기를 초과한 경우
    //             if(file.size > maxSize){
    //                 alert("2MB 이하의 이미지를 선택해 주세요.");
    //                 imageInput.value = "";
                    
    //                 // 취소 == 파일 없음 == 초기 상태
    //                 deleteCheck = -1;

    //                 // 기존 프로필 이미지로 변경
    //                 profileImage.setAttribute("src", originalImage);

    //                 return;
    //             }

    //             // 파일을 읽고 파일을 저장
    //             const reader = new FileReader();

    //             // 매개변수에 작성된 파일을 읽어서 저장 후
    //             // 파일을 나타내는 URL을 result 속성으로 얻어올 수 있게 함
    //             reader.readAsDataURL(file);
            
    //             // 다 읽었을 떄
    //             reader.onload = e => {

    //                 const url = e.target.result;

    //                 // 프로필 이미지(img) 태그에 src 속성으로 추가
    //                 profileImage.setAttribute("src", url);

    //                 deleteCheck = -1;

    //             }

    //         });

    //         document.getElementById("profileForm").addEventListener("submit", e =>{
            
    //             let flag = true;
    
    //             // 프로필 이미지가 없다 -> 있다.
    //             if(!initCheck && deleteCheck == 1) flag = false;
    
    //             // 이전 프로필 이미지가 있다 -> 삭제
    //             if(initCheck && deleteCheck == 0) flag = false;
    
    //             // 이전 프로필이미지가 있다 -> 새 이미지
    //             if(initCheck && deleteCheck == 1) flag = false;
    
    //             if(flag) {
    //                 // form 기본 이벤트 제거
    //                 e.preventDefault();
    //                 alert('이미지 변경 후 클릭하세요.');
    //             }
    //         });

    //     });
    // }

    // if(!File.prototype.match("image.*")){
    //     alert("이미지 파일 형식만 등록 가능합니다.");
    //     return;
    // }

    // document.addEventListener('DOMContentLoaded', function () {
    //     const input = document.getElementById('profileImg');
    //     const img = document.getElementById('proImg');
    //     const form = document.getElementById('profileForm');
    
    //     input.addEventListener('change', function (event) {
    //         const file = event.target.files[0];
    //         const reader = new FileReader();
    //         reader.onload = function (e) {
    //             img.src = e.target.result;
    //         }
    //         reader.readAsDataURL(file);
    //     });
    
    //     form.addEventListener('submit', function (event) {
    //         event.preventDefault();
    //         const formData = new FormData(form);
    
    //         fetch('/member/updateProfile', {
    //             method: 'POST',
    //             body: formData
    //         })
    //         .then(response => response.json())
    //         .then(data => {
    //             alert("프로필 등록이 완료 되었습니다!");
    //             console.log(data);

    //         })
    //         .catch(error => {
    //             console.error('Error:', error);
    //         });
    //     });
    // });


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

