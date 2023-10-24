/* user_info.js */

/* 닉네임 변경 글자수 기능 */
function countCharacters() {
    var inputField = document.getElementById('inputField');
    var currentLength = inputField.value.length;
    var maxLength = inputField.getAttribute('maxlength');
    var countDisplay = document.getElementById('characterCount');
    countDisplay.textContent = currentLength + ' / ' + maxLength;
}

// 닉네임 변경 버튼 클릭시 modal창 실행
// $(function() {
//     $("#updateNN").on("click", function() {
//         $("#updateNN_Btn").modal("show");
//     });
// });

// 비밀번호 변경 버튼 클릭시 modal창 실행
// $(function() {
//     $("#updatePW").on("click", function() {
//         $("updatePW_Btn").modal("show");
//     });
// });