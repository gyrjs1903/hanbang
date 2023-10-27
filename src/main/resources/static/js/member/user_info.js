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


    /* input file 기능 전달, 이미지 미리보기 */
    document.addEventListener("DOMContentLoaded", function() {
        function preView(e, file) {
            const profileImg = document.querySelector('#imagePreview img');
            profileImg.setAttribute('src', e.target.result);
            profileImg.setAttribute('data-file', file.name);
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
    
        function submitForm() {
            var fileInput = document.getElementById('profileImg');
            var file = fileInput.files[0];
            var formData = new FormData();
            formData.append('profileImg', file);
            $.ajax({
                url: '/member/updateProfile',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(data){
                    console.log('정상적으로 프로필 사진 등록이 되었습니다.');
                },
                error: function(){
                    console.log('에러가 발생했습니다. 다시 시도해주세요.');
                }
            });
        }
    });