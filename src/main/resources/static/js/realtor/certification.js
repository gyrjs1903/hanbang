function duplication(){
    let num = document.querySelector('#identificationNum').value;
    let realtorName = document.querySelector('#realtorName').value;
    let officeName = document.querySelector('#officeName').value;
    let licenseImg = document.querySelector('#licenseImg').value;
    console.log(num);
    if(num != '' && num != null){
        fetch('/realtor/identificationNum', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify({
            identificationNum : document.querySelector('#identificationNum').value
            })
        })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            if(data){
                if(realtorName == null || realtorName == ''){
                    inputInvalidate('#realtor-name-error-div','대표자명은 필수입력사항입니다.');
                } if(officeName == null || officeName == ''){
                    inputInvalidate('#name-error-div','중개사무소명은 필수입력사항입니다.');
                }
                if(licenseImg == null || licenseImg == ''){
                    inputInvalidate('#img-error-div','사업자등록증 파일을 첨부해주십시오.');
                }
                if(officeName != '' && licenseImg != ''){
                    document.querySelector('#certificationForm').submit();
                }
            } else{
                inputInvalidate('#id-error-div','사업자등록번호를 확인해주세요.');
            }
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    } else {
        alert('모든 정보를 입력해주세요.')
}
}

function inputInvalidate(tagId, message){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}