/* user_info.js */

/* 닉네임 변경 글자수 기능 */
function countCharacters() {
    var inputField = document.getElementById('inputField');
    var currentLength = inputField.value.length;
    var maxLength = inputField.getAttribute('maxlength');
    var countDisplay = document.getElementById('characterCount');
    countDisplay.textContent = currentLength + ' / ' + maxLength;
}