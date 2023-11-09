// itemContent + 기준으로 내용 자르기

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

