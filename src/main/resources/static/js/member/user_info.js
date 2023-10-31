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


    /* 프로필 사진 변경 시 데이터 베이스로 바로 전달 */
    // function sendProfile() {
    //     var fileInput = document.getElementById('profileImg');
    //     var file = fileInput.files;
    //     var formData = new FormData();
    //     formData.append('profileImg', file);
    //     fetch('/member/updateProfile', {
    //         method: 'POST',
    //         body: JSON.stringify(),
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     })
    //     .then(response => response.json())
    //     .then(data => {
    //         alert('프로필 사진이 등록되었습니다!');
    //         console.log(data);
    //     })
    //     .catch(error => {
    //         alert('프로필 사진 등록에 실패했습니다. 다시 시도해 주세요!');
    //         console.error('Error:', error);
    //     });
    // }
    /* input file 기능 전달, 이미지 미리보기 */
    document.addEventListener("DOMContentLoaded", function() {
        function preView(e, file) {
            const profileImg = document.querySelector('#imagePreview img');
            profileImg.setAttribute('src', e.target.result);
            profileImg.setAttribute('data-file', file.name);
            sendProfileImageToServer(e.target.result); // 파일 데이터를 서버로 전송
        }
    
        function getImageFile(e) {
            const file = e.currentTarget.files[0];
            if (!file.type.match("image/.*")) {
                alert('이미지 파일만 업로드가 가능합니다.');
                return;
            }
    
            const reader = new FileReader();
            reader.onload = function (e) {
                preView(e, file);
            };
            reader.readAsDataURL(file);
        }
    
        const fakeUpload = document.querySelector('.fake_upload');
        const realUpload = document.querySelector('.real_upload');
    
        fakeUpload.addEventListener("click", function() {
            realUpload.click();
        });
    
        realUpload.addEventListener('change', function(e) {
            getImageFile(e);
        });
    });
    