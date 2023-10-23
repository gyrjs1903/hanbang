// 공인 중개사 승인 
function updateAuthority(realTorCode){
    const result = confirm('승인 하시겠습니까?');
    if(result){
        location.href = `/admin/updateAuthority?realTorCode=${realTorCode}`;
    }
}

// 맴버쉽 등록 폼 --> catogry에 따라 양식 변경

const selectCategory = document.querySelector('#select_category');
const selectMideCategory = document.querySelector('#select-midCategory');

selectCategory.addEventListener('change',function(){
    const selectedCateCode = selectCategory.value;
    const optionForm0 = document.querySelector('.optionForm0');
    const optionForm1 = document.querySelector('.optionForm1');
    const optionForm2 = document.querySelector('.optionForm2');
    const addNew = document.querySelector('.addNewCategory');
    

    console.log(selectedCateCode);

    if(selectedCateCode == 'CATE_001'||selectedCateCode == 'addNew'){
        optionForm0.style.display = 'block';
        optionForm1.style.display = 'block';
        optionForm2.style.display = 'none';

        //disabled 속성 추가
        optionForm0.querySelector('select').disabled = false;
        optionForm0.querySelector('input').disabled = false;

        const form1_inputs = optionForm1.querySelectorAll('input');
        form1_inputs.forEach((inputTag, idx) => {
            inputTag.disabled = false;
        });
        const form1_texts = optionForm1.querySelectorAll('textarea');
        form1_texts.forEach((textTag, idx) => {
            textTag.disabled = false;
        });

        const form2_inputs = optionForm2.querySelectorAll('input');
        form2_inputs.forEach((inputTag, idx) => {
            inputTag.disabled = true;
        });
        optionForm2.querySelector('textarea').disabled = true;


        // 대분류 신규 등록 input 생성 
        if(selectedCateCode == 'addNew'){
            addNew.style.display = 'block';
            addNew.disabled = false;
        }else{
            addNew.style.display = 'none';
            addNew.disabled = true;
        }
        

    } else if(selectedCateCode != 'CATE_001'){
        optionForm0.style.display = 'none';
        optionForm1.style.display = 'none';
        optionForm2.style.display = 'block';
        addNew.style.display = 'none';

        //disabled 속성 추가
        optionForm0.querySelector('select').disabled = true;
        optionForm0.querySelector('input').disabled = true;

        const form1_inputs = optionForm1.querySelectorAll('input');
        form1_inputs.forEach((inputTag, idx) => {
            inputTag.disabled = true;
        });
        const form1_texts = optionForm1.querySelectorAll('textarea');
        form1_texts.forEach((textTag, idx) => {
            textTag.disabled = true;
        });

        const form2_inputs = optionForm2.querySelectorAll('input');
        form2_inputs.forEach((inputTag, idx) => {
            inputTag.disabled = false;
        });
        optionForm2.querySelector('textarea').disabled = false;

    }

});

// 중분류 신규 등록 input 생성 
selectMideCategory.addEventListener('change',function(){
    const selectMideCateCode = selectMideCategory.value;
    const addMidNew = document.querySelector('.addNewMidCategory');

    console.log(selectMideCateCode);

    if(selectMideCateCode == 'addMidNew'){
        addMidNew.style.display = 'block';
        addMidNew.disabled = false;
    }else{
        addMidNew.style.display = 'none';
        addMidNew.disabled = true;
    }

});

//대분류에서 클릭 시 실행 --> 중분류 
function getMidCateList(memCateCode){
    
    fetch('/admin/getMidCateList', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           'memCateCode' : memCateCode
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
       
        console.log(data);

       const midCateDiv = document.querySelector('.midCate');
       midCateDiv.textContent='';


       let str = '';
        data.forEach((element,index) => {
            str += `<div  class="alert alert-secondary" role="alert" onclick="getItemCateList('${element.membershipCode}');">${element.membershipName}</div> `;
            
        });

        str += `<div class="addNewCate alert alert-light" role="alert"><span onclick='addNewInput(this, "${memCateCode}")'> 추 가 </span></div>`;
       
       midCateDiv.insertAdjacentHTML('afterbegin', str);

       
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

// 중분류 클릭 시 --> 소분류

function getItemCateList(membershipCode){

        fetch('/admin/getItemCateList', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
               'membershipCode' : membershipCode
            })
        })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
           
           const itemCateDiv = document.querySelector('.addItemCate');
           itemCateDiv.textContent='';
           
           data.map(function(element,index){
                const newItemDiv = document.createElement('div');
                newItemDiv.className = 'alert alert-secondary';
                newItemDiv.textContent = element.itemName;
                itemCateDiv.appendChild(newItemDiv);
           });

           let str = `<div class="addNewCate alert alert-light" role="alert" ><span  onclick='addNewInput(this)'>추 가</span></div>`;

           itemCateDiv.insertAdjacentHTML('beforeend', str);
           
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

   
}

// 추가 클릭시 스타일 변경 
function addNewInput(tag, memCateCode){
    //span을 감싸고 있는 div 태그 
    const divTag = tag.parentElement;

    //기존의 추가 글자 제거
    tag.remove();

    const topDiv = divTag.parentElement.id;

    let str = '';
    str +='<input type="text" id="newCateInput" placeholder="New Category">';
    str +=`<input type="button" value="등록" onclick="addWholeCateFetch('${topDiv}', '${memCateCode}');">`;

    divTag.insertAdjacentHTML('afterbegin',str);
};

//맴버쉽을 등록하는 화면에서 카테고리 비동기 등록
function addWholeCateFetch(type, memCateCode){
    fetch('/admin/insertCateFecth', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'type' : type,
            'memCateCode':memCateCode,
            'memCateName': document.querySelector('#newCateInput').value
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
       
        console.log(data);

        let str = '';

        if(data.type == 'cate'){
            data.data.forEach((element, idx) => {
                str += `<div class="alert alert-secondary" role="alert" th:onclick="getMidCateList(${element.memCateCode});">
                            ${element.memCateName}
                        </div>
                        `;
            });

            str += `<div class="addNewCate alert alert-light" role="alert" >
                        <span onclick='addNewInput(this, "")'>추 가</span>
                    </div>`;

            document.querySelector('.cate').innerHTML = '';  
            document.querySelector('.cate').insertAdjacentHTML('afterbegin', str);      
        }
        else if(data.type == 'midCate'){
            data.data.forEach((element, idx) => {
                str += `<div class="alert alert-secondary" role="alert" th:onclick="getMidCateList(${element.membershipCode});">
                            ${element.membershipName}
                        </div>
                        `;
            });

            str += `<div class="addNewCate alert alert-light" role="alert" >
                        <span onclick='addNewInput(this, '${ data.data[0].memCateCode}')'>추 가</span>
                    </div>`;

            document.querySelector('.midCate').innerHTML = '';  
            document.querySelector('.midCate').insertAdjacentHTML('afterbegin', str);      
        }




    //    const itemCateDiv = document.querySelector('.addItemCate');
    //    itemCateDiv.textContent='';
       
    //    data.map(function(element,index){
    //         const newItemDiv = document.createElement('div');
    //         newItemDiv.className = 'alert alert-secondary';
    //         newItemDiv.textContent = element.itemName;
    //         itemCateDiv.appendChild(newItemDiv);
    //    });

    //    let str = `<div class="addNewCate alert alert-light" role="alert" ><span  onclick='addNewInput(this)'>추 가</span></div>`;

    //    itemCateDiv.insertAdjacentHTML('beforeend', str);
       
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}














    
    




