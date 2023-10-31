/* user_inquiry_write.js */

// 문의 버큰 클릭 시 이벤트 처리
// document.getElementById('inquirySubmitButton').addEventListener('click', function() {
//     event.preventDefault();

//     var selectedValue = document.querySelector('select[name="inquiryCode"]').value;
//     var memberInquiryTitle = document.querySelector('input[name="memberInquiryTitle"]').value;
//     var memberInquiryDetail = document.querySelector('textarea[name="memberInquiryDetail"]').value;
//     var attachedFiles = document.querySelector('input[type="file"]').files;

//     var formData = new FormData();
//     formData.append('inquiryCode', selectedValue);
//     formData.append('memberInquiryTitle', memberInquiryTitle);
//     formData.append('memberInquiryDetail', memberInquiryDetail);

//     for (var i = 0; i < attachedFiles.length; i++) {
//         formData.append('attachedFiles', attachedFiles[i]);
//     }

//     fetch('/member/memberInquirySave', {
//         method: 'POST',
//         body: formData
//     })
//     .then(response => response.text())
//     .then(data => {
//         alert("문의가 성공적으로 전송되었습니다.");
//         console.log(data);
//     })
//     .catch(error => {
//         alert("문의 전송에 실패했습니다. 다시 시도해주세요.");
//         console.error('Error:', error);
//     });
// });

/* 파일 첨부 기능 div로 전달 */
const fileButton = document.querySelector(".file-button");
const realFile = document.querySelector(".real-file");
const previewImages = document.querySelectorAll(".previewImg");

let fileCount = previewImages.length; // 현재 첨부된 파일 수

fileButton.addEventListener("click", function(event){
    const clickEvent = new MouseEvent("click", {
        bubbles: true,
        cancelable: true,
        view: window
    });
    realFile.dispatchEvent(event);
    // if (fileCount < 3) {
    //     realFile.click(); // 실제 파일 클릭 이벤트를 트리거
    //     fileCount++;
    // }
    // if (fileCount >= 3) {
    //     event.stopPropagation(); // 파일 첨부가 3번 이상이면 이벤트 전파 중지
    // }
});

// 파일 첨부 취소 기능 구현
// previewImages.forEach(function(previewImage) {
//     const deleteButton = document.createElement("div"); // 삭제 버튼 생성
//     deleteButton.classList.add("imgDeleteBtn"); // 삭제 버튼에 X 텍스트 추가
//     deleteButton.style.backgroundImage = "url('/path/to/your/image.png')";
//     previewImage.parentNode.appendChild(deleteButton); // 삭제 버튼을 이미지와 함께 부모 요소에 추가

//     deleteButton.addEventListener("click", function() {
//         // 이미지 삭제 버튼 클릭 시 해당 이미지 삭제 및 파일 첨부 수 감소
//         this.parentNode.style.display = "none";
//         fileCount--;
//     });
// });

/* 이미지 최대 업로드 수, 파일 확장자 제한 및 업로드 할 사진 미리보기 기능 구현 */
// $(document).ready(function(){
//     // 파일 첨부 이벤트
//     $('.file-button').on('click', function(){
//         if($('.previewImg:visible').length >= 3){
//             alert("최대 3개까지만 첨부할 수 있습니다.");
//             return;
//         }
//         $('.real-file').click();
//     });

//     $('.real-file').on('change', function(){
//         const file = this.files[0];
//         const reader = new FileReader();
//         const previewImgs = $('.previewImg');

//         if(file){
//             if(!validFileType(file.name)){
//                 alert("이미지 파일만 업로드가 가능합니다.");
//                 return;
//             }
//             if(!validFileSize(file)){
//                 alert("파일 사이즈가 20MB를 초과합니다.");
//                 return;
//             }

//             const emptyPreview = $(previewImgs).filter(function() {
//                 return $(this).css('display') === 'none';
//             });

//             if(emptyPreview.length > 0) {
//                 const imgElement = emptyPreview.first();
//                 const reader = new FileReader();
//                 reader.onload = function(e) {
//                     imgElement.attr('src', e.target.result).show();
//                 }
//                 reader.readAsDataURL(file);
//             }
//         }
//     });

//     function validFileType(filename) {
//         const fileTypes = ["png", "jpg", "jpeg"];
//         return fileTypes.indexOf(filename.substring(filename.lastIndexOf(".")+1, filename.length).toLowerCase()) >= 0;
//     }

//     function validFileSize(file){
//         return file.size <= 20000000; // 20MB
//     }
// });

// // 이미지 원본 팝업 띄우기
// function popImage(url) {
//     var img = new Image();
//     img.src = url;
//     var img_width = img.width;
//     var win_width = img.width + 25;
//     var img_height = img.height;
//     var win = img.height + 30;
//     var popup = window.open('', '_blank', 'width=' + img_width + ', height=' + img_height + ', menubars=no, scrollbars=auto');
//     popup.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
// }
