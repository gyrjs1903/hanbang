/* user_inquiry_write.js */

const inquiryImgInput = document.getElementById("fileInput");
const inquiryImgList = document.getElementById("preview-wrap");

// 파일이 선택되었을 때 이벤트 핸들러 등록

inquiryImgInput.addEventListener("change", function () {
    displaySelectedImages(inquiryImgInput.files, inquiryImgList, "상세내용 사진");
});

function displaySelectedImages(files, imageList, labelText) {
    imageList.innerHTML = ""; // 이미지 목록 초기화

    for (let i = 0; i < files.length; i++) {
        const file = files[i];

        if (file.type.startsWith("image/")) {
            const container = document.createElement("div");
            container.classList.add("image-container"); // 클래스 추가

            const imageElement = document.createElement("img");
            imageElement.src = URL.createObjectURL(file); // 이미지를 표시하기 위해 URL.createObjectURL 사용

            const labelElement = document.createElement("p");
            labelElement.textContent = labelText;

            container.appendChild(imageElement);
            container.appendChild(labelElement);
            imageList.appendChild(container);
        }
    }
}