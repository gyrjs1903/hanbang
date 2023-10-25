function inquiryDetail(inquiryCode){
    location.href=`/realtor/inquiryDetail?inquiryCode=${inquiryCode}`;
}

function sendInquiry(){
    if(confirm('답변을 등록하시겠습니까?')){
        document.querySelector('#inquiryAnswerForm').submit();
    }
}