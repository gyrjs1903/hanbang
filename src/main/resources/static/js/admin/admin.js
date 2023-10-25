// 공인 중개사 승인 
function updateAuthority(realTorCode){
    const result = confirm('승인 하시겠습니까?');
    if(result){
        location.href = `/admin/updateAuthority?realTorCode=${realTorCode}`;
    }
}


//대분류에서 클릭 시 실행 --> 중분류 
function getMidCateList(memCateCode, itemCode){
    fetch('/admin/getMidCateList', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'memCateCode' : memCateCode,
            'itemCode' : itemCode
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
        console.log(data);

        //-----------조회한 데이터로 중분류 다시 그리기-------------//
        const midCateDiv = document.querySelector('.midCate');
        midCateDiv.textContent='';

        let midCateStr = '';
        data.membershipList.forEach((element,index) => {
            midCateStr += `<div  class="alert alert-secondary" role="alert" onclick="getItemCateList('${element.memCateCode}', '${element.membershipCode}');">${element.membershipName}</div> `;
            
        });

        midCateStr += `<div class="addNewCate alert alert-light" role="alert"><span onclick='addNewInput(this, "${memCateCode}")'> 추 가 </span></div>`;
        
        midCateDiv.insertAdjacentHTML('afterbegin', midCateStr);

        //-----------조회한 데이터로 상품 목록 다시 그리기-------------//
        const itemListTable = document.querySelector('.itemListTable tbody');
        itemListTable.textContent='';

        let itemCateStr = '';

        let itemCnt = 0;

        data.itemList.forEach((element,idx)=>{
            itemCnt ++;
            itemCateStr += `
                            <tr>
                                <td>${itemCnt}</td>
                                <td class="alert alert-secondary" role="alert" onclick="showItemDetail('${element.itemCode}');">${element.itemName}</td>
                            </tr> `;
        });

        itemListTable.insertAdjacentHTML('afterbegin', itemCateStr);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

// 중분류 클릭 시 --> 아이템 목록 조회

function getItemCateList(memCateCode, membershipCode, itemCode){
    fetch('/admin/getItemCateList', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'memCateCode' : memCateCode,
            'membershipCode':membershipCode,
            'itemCode' : itemCode
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
        console.log(data);

        const itemCateDiv = document.querySelector('.itemListTable tbody');
        itemCateDiv.textContent='';

        let itemCnt = 0;
        let str ='';
        data.forEach((element,index) => {
            itemCnt ++ ;
            str += `
                    <tr>
                        <td>${itemCnt}</td>
                        <td class="alert alert-secondary" role="alert" onclick="showItemDetail('${element.itemCode}');"> ${element.itemName}</td>
                    </tr> `;
                    
        });
        
        itemCateDiv.insertAdjacentHTML('afterbegin', str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}


// 추가 클릭시 스타일 변경 (input 태그 생성)
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

//맴버쉽을 등록하는 화면에서 카테고리를 비동기로 등록하기

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
            
            //화면 하단에 상품 등록에 있는 대분류 셀렉트 박스 다시 그리기
            getNewCateList();
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

            const firstCate  = document.querySelector('#selectFirstCate');
            const secondCate  = document.querySelector('#selectSecondCate');

            firstCate.querySelector('option:first-child').selected = true;
            secondCate.querySelector('option:first-child').selected = true;
        }
       
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

// 아이템 클릭 시 아이템의 세부 정보로 이동
function showItemDetail(itemCode){
    fetch('/admin/showItemDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'itemCode' : itemCode
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
        console.log(data);

        const itemDetailList = document.querySelector('.itemDetailList');
        itemDetailList.textContent='';

        let str ='';

        data.forEach((element,index) => {
            str += `
                    <ul>
                        <li>
                            <p>상품명 :</p>
                            <p><span class="itemNameSpan">${element.itemName}</span></p>
                        </li>
                        <li>
                            <p>상품가격 : </p>
                            <p><span class="itemPriceSpan">${element.itemPrice}</span></p>
                        </li>
                        <li>
                            <p>상품소개 : </p>
                            <p><span class="itemIntroSpan">${element.itemIntro}</span></p>
                        </li>
                        <li>
                            <p>상품 상세설명 : </p>
                            <p><span class="itemContentSpan">${element.itemContent}</span></p>
                        </li>
                    </ul> 
                    <input type="button" onclick="editItemDetail(this, '${element.itemCode}');" value="수정하기">
                    `;
        });

        
        itemDetailList.insertAdjacentHTML('afterbegin', str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

// 수정하기 버튼 클릭 시 input 태그 생성 함수 
function editItemDetail(tag, itemCode){
    if(tag.value == '수정하기'){

        const itemNameSpan =  document.querySelector('.itemNameSpan');
        const itemName =  itemNameSpan.textContent;
        itemNameSpan.textContent = '';
        itemNameSpan.insertAdjacentHTML('afterbegin', `<input type="text" id="itemName" value="${itemName}">`);
    
        const itemPriceSpan =  document.querySelector('.itemPriceSpan');
        const itemPirce =  itemPriceSpan.textContent;
        itemPriceSpan.textContent = '';
        itemPriceSpan.insertAdjacentHTML('afterbegin', `<input type="text" id="itemPrice" value="${itemPirce}">`);
    
        const itemIntroSpan =  document.querySelector('.itemIntroSpan');
        const itemIntro =  itemIntroSpan.textContent;
        itemIntroSpan.textContent = '';
        itemIntroSpan.insertAdjacentHTML('afterbegin', `<textarea id="itemIntro" cols=30 rows=5>${itemIntro}</textarea>`);
    
        const itemContentSpan =  document.querySelector('.itemContentSpan');
        const itemContent =  itemContentSpan.textContent;
        itemContentSpan.textContent = '';
        itemContentSpan.insertAdjacentHTML('afterbegin', `<textarea id="itemContent" cols=30 rows=5>${itemContent}</textarea>`);
        
        tag.value = '완료';

    }

     else{
        updateItemInfo(itemCode);
      
        tag.value = '수정하기';
     }
}


//상품 상세 정보 수정
function updateItemInfo(itemCode){
  
    fetch('/admin/updateItem', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'itemName' : document.querySelector('#itemName').value,
            'itemPrice' : document.querySelector('#itemPrice').value,
            'itemIntro' : document.querySelector('#itemIntro').value,
            'itemContent' : document.querySelector('#itemContent').value,
            'itemCode' : itemCode
        })
    })
    .then((response) => {
        return response.text(); //리턴이 없으므로 json 대신 text
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('수정 완료!');
        showItemDetail(itemCode);

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
   
}

// 대분류 클릭시 포함된 중분류 카테고리만 조회하기
function selectNewMidCate(){
    const firstCate  = document.querySelector('#selectFirstCate').value;
    const secondCate = document.querySelector('#selectSecondCate');

    fetch('/admin/getNewMidCateList', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'memCateCode' : firstCate,
        })
    })
    .then((response) => {
        return response.json(); 
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        secondCate.innerHTML='';

        let str = '';

        str += '<option selected>중분류</option>';
        data.forEach((element,idx)=>{
            str += `<option value="${element.membershipCode}">${element.membershipName}</option>`
        });

        secondCate.insertAdjacentHTML('afterbegin', str);
    })   
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

//화면 하단에 상품 등록에 있는 대분류 셀렉트 박스 다시 그리기
function getNewCateList(){
    fetch('/admin/getNewCateList', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           
        })
    })
    .then((response) => {
        return response.json(); //리턴이 없으므로 json 대신 text
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        const firstCate  = document.querySelector('#selectFirstCate');
        const secondCate  = document.querySelector('#selectSecondCate');
        firstCate.innerHTML = '';

        let str = '';

        str += '<option selected>대분류</option>';
        data.forEach((element,idx)=>{
            str += `<option th:value="${element.memCateCode}">${element.memCateName}</option>`;
        });

        firstCate.insertAdjacentHTML('afterbegin', str);

        firstCate.querySelector('option:first-child').selected = true;
        secondCate.querySelector('option:first-child').selected = true;

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}


// 아이템 등록하기 
function insertItem(){
    const firstCate  = document.querySelector('#selectFirstCate').value;
    const secondCate = document.querySelector('#selectSecondCate').value;

    fetch('/admin/insertItem', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'memCateCode' : firstCate,
            'membershipCode' : secondCate,
            'itemName' : document.querySelector('#itemName').value,
            'itemPrice' : document.querySelector('#itemPrice').value,
            'itemIntro' : document.querySelector('#itemIntro').value,
            'itemContent' : document.querySelector('#itemContent').value
        })
    })
    .then((response) => {
        return response.text(); //리턴이 없으므로 json 대신 text
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
   
}






    
    




