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

// 중분류 클릭 시 --> 소분류

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
                            <p> ${element.itemName}</p>
                        </li>
                        <li>
                            <p>상품가격 : </p>
                            <p> ${element.itemPrice}</p>
                        </li>
                        <li>
                            <p>상품소개 : </p>
                            <p> ${element.itemIntro}</p>
                        </li>
                        <li>
                            <p>상품 상세설명 : </p>
                            <p> ${element.itemContent}</p>
                        </li>
                    </ul> `;
        });

        itemDetailList.insertAdjacentHTML('afterbegin', str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}













    
    




