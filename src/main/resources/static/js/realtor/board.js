function inquiryDetail(inquiryCode){
    location.href=`/realtor/inquiryDetail?inquiryCode=${inquiryCode}`;
}

function sendInquiry(){
    let answer = document.querySelector('#inquiryAnswer').value;
    if(answer == ''){
        alert('내용을 입력해주세요.');
    } else {
        if(confirm('답변을 등록하시겠습니까?')){
            document.querySelector('#inquiryAnswerForm').submit();
        }
    }
}