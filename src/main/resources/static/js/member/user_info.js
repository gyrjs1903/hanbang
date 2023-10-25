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

/* 프로필 이미지 등록, 미리보기 구현 */
// function getImgFiles(e){
//     const uploadFiles = [];
//     const files = e.currentTarget.files;
//     const imagePreview = document.querySelector('.img_preview');
//     const docFrag = new DocumentFragment();

//     if ([...files].length >= 1) {
//         alert('프로필 이미지는 최대 1개만 설정이 가능합니다.')
//         return;
//     }

//     // 파일 타입 검사
//     [...files].forEach(file => {
//         if (!files.type.match("image/.*")) {
//             alert('이미지 파일만 업로드가 가능합니다.');
//             return
//         }

//         // 파일 개수 검사
//         if([...files].length < 2) {
//             uploadFiles.push(file);
//             const reader = new FileReader();
//             reader.onload = (e) => {
//                 const preview = createElement(e, file);
//                 imagePreview.appendChild(preview);
//             };
//             reader.readAsDataURL(file);
//         }
//     });
// }

// function createElement(e, file) {
//     const div = document.createElement('div');
//     div.setAttribute('class', 'img_preview')
//     const img = document.createElement('img');
//     img.setAttribute('src', e.target.result);
//     img.setAttribute('data-file', file.name);
//     div.appendChild(img);

//     return div;
// }

//     const realUpload = document.querySelector('.real_upload');
//     const upload = document.querySelector('.upload')

//     upload.addEventListener('click', () => realUpload.click());

//     realUpload.addEventListener('change', getImageFiles);

//     window.addEventListener('DOMContentLoaded', () => {
//     const imgPreview = document.querySelector('.img_preview')
    //    const defaultImageSrc = '/img/member/default_profile_image.png';
    //    const defaultImg = document.createElement('img');
    //    defaultImg.setAttribute('src', defaultImageSrc);
    //    imagePreview.appendChild(defaultImg);
//     })

function chooseImg(obj) {
    let file = obj.files[0];
    if (!file.type.match("image.*")) {
        alert("이미지를 등록해야 합니다.");
        return;
    }
    let reader = new FileReader();

    reader.onload = function (e) {
        let imagePreview = document.getElementById('imagePreview');
        imagePreview.innerHTML = '<img src="' + e.target.result + '" style="max-width: 100%; max-height: 100%;">';
    }

    reader.readAsDataURL(file);
}