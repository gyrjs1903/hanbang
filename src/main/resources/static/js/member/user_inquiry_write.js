/* user_inquiry_write.js */

document.addEventListener("DOMContentLoaded", function() {
    const imagePreviewContainer = document.getElementById('imagePreviewContainer');
    const maxImages = 3;
    let imageCount = 0;

    function preView(e, file) {
        const profileImg = document.querySelector('#imagePreview');
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

        imageCount++;

        if (imageCount === maxImages) {
            document.getElementById('fileInput').style.display = 'none';
        }

        if (imageCount < maxImages) {
            const newImageInput = document.createElement('input');
            newImageInput.type = 'file';
            newImageInput.style.display = 'none';
            newImageInput.id = 'fileInput' + imageCount;
            newImageInput.addEventListener('change', function(e) {
                getImageFile(e);
            });

            const newButton = document.createElement('button');
            newButton.type = 'button';
            newButton.classList.add('fake_upload');
            newButton.textContent = '사진 첨부';
            newButton.addEventListener('click', function() {
                document.getElementById('fileInput' + imageCount).click();
            });

            const newDiv = document.createElement('div');
            newDiv.classList.add('imgaddW');
            newDiv.appendChild(newImageInput);
            newDiv.appendChild(newButton);

            imagePreviewContainer.appendChild(newDiv);
        }
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


function saveInquiry() {
    var inquiryType = document.querySelector('select[name="inquiryCode"]').value;
    var inquiryTitle = document.querySelector('input[name="memberInquiryTitle"]').value;
    var inquiryDetail = document.querySelector('textarea[name="memberInquiryDetail"]').value;
    var imageFileName = document.querySelector('#imagePreview').getAttribute('data-file');

    var data = {
        inquiryType: inquiryType,
        inquiryTitle: inquiryTitle,
        inquiryDetail: inquiryDetail,
        imageFileName: imageFileName
    };

    fetch('/memberInquirySave', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {

        alert("문의가 성공적으로 전송되었습니다.");
    })
    .catch(error => {
        alert("문의 전송에 실패했습니다. 다시 시도해주세요.");
        console.error('Error:', error);
    });
}