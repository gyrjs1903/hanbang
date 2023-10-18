// 공인 중개사 승인 
function updateAuthority(realTorCode){
    const result = confirm('승인 하시겠습니까?');
    if(result){
        location.href = `/admin/updateAuthority?realTorCode=${realTorCode}`;
    }
}

// 맴버쉽 등록 폼 --> catogry에 따라 양식 변경

// 수정 필요 --> 동일한 id 값이 아닌 id 값을 따로 줘야 하고 쿼리설렉터로 불러오는 걸로 수정  
const selectCate = document.getElementById('select_category');
const optionForm1 = document.getElementById('optionForm1');
const optionForm2 = document.getElementById('optionForm2');

const aas = document.querySelectorAll('.??');

selectCate.addEventListener('change',function(){
    const selectedCateCode = selectCate.value;

    console.log(selectedCateCode);

    optionForm1.innerHTML = '';
    optionForm2.innerHTML = '';

    if(selectedCateCode == 'CATE_001'){
        optionForm1.style.display = 'block';
        optionForm2.style.display = 'none';
    } else if(selectedCateCode != 'CATE_001'){
        optionForm1.style.display = 'none';
        optionForm2.style.display = 'block';
    }
});


