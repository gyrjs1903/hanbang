// 가격에 단위 붙이기 

    // let itemPrice = document.querySelector('.itemPrice').textContent;
    // document.querySelector('.itemPrice').innerHTML = `￦${itemPrice.toLocaleString('ko-KR')}`;
   

    // let won = Intl.NumberFormat('ko-KR', {
    //     style: 'currency',
    //     currency: 'KRW',
    // });

    // won.format(itemPrice);


// 특정 문자열 기준으로 자르기

//조회한 모든 내용이 있는 태그
let itemContentTags = document.querySelectorAll('.itemContent');


for(const itemContentTag of itemContentTags){
    const originData = itemContentTag.textContent;
    itemContentTag.innerHTML = '';
    
    let str = '';
    const originArr = originData.split('+');

    for(const origin of originArr){
        str += `<p>	&diams; ${origin}</p>`;
    }

    itemContentTag.insertAdjacentHTML('afterbegin', str)

}








