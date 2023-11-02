//전역변수
let map;
let geocoder = new kakao.maps.services.Geocoder();

//화면 오픈 시 바로 실행되는 함수
init();
changeSubPropertyTypeCode();
setInitialSubcategory();


function openPost() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.getElementById('addr').value = data.roadAddress;

            // 주소로 좌표를 검색합니다
            geocoder.addressSearch(data.roadAddress, function (result, status) {

                // 정상적으로 검색이 완료됐으면 
                if (status === kakao.maps.services.Status.OK) {

                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);


                    // 결과값으로 받은 위치를 마커로 표시합니다
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });

                    map.setCenter(coords);

                    //좌표값 html로 넘기기
                    let coordinateY = document.getElementById("coordinate_y")
                    let coordinateX = document.getElementById("coordinate_x")
                    let Y = result[0].y
                    let X = result[0].x

                    coordinateY.value = Y;
                    coordinateX.value = X;

                }

            });

        }
    }).open();
}

//화면 실행 시 최초 실행되는 map
function init() {
    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(35.5419831733752, 129.338238429286), //지도의 중심좌표.
        draggable: false, //// 지도를 생성할때 지도 이동 및 확대/축소를 막으려면 draggable: false 옵션을 추가하세요
        level: 3 //지도의 레벨(확대, 축소 정도)
    }
    // 지도를 생성합니다
    map = new kakao.maps.Map(container, options);
}

function changeSubPropertyTypeCode() {
    document.querySelectorAll('input[name="propertyTypeCode"]').forEach(function (radio) {
        radio.addEventListener('change', function () {
            var selectedPropertyTypeCode = radio.value;
            var subcategoryWrapper = document.querySelectorAll('.subproperty-wrapper');

            subcategoryWrapper.forEach(function (wrapper) {
                var parentPropertyTypeCode = wrapper.getAttribute('data-property-type-code');
                if (parentPropertyTypeCode === selectedPropertyTypeCode) {
                    wrapper.style.display = 'block';
                } else {
                    wrapper.style.display = 'none';
                }
            });
        });
    });
}


function setInitialSubcategory() {
    let selectedPropertyTypeCode = document.querySelector('input[name="propertyTypeCode"]:checked').value;
    let subcategoryWrapper = document.querySelectorAll('.subproperty-wrapper');

    subcategoryWrapper.forEach(function (wrapper) {
        var parentPropertyTypeCode = wrapper.getAttribute('data-property-type-code');
        if (parentPropertyTypeCode === selectedPropertyTypeCode) {
            wrapper.style.display = 'block';
        } else {
            wrapper.style.display = 'none';
        }
    });
}

const roomSizePInput = document.getElementById("roomSizeP");
const roomSizeMInput = document.getElementById("roomSizeM");

// 평수를 제곱미터로 변환하는 함수
function convertPToM(p) {
    return (p * 3.305785).toFixed(2); // 1평 ≈ 3.305785 제곱미터
}

// 제곱미터를 평수로 변환하는 함수
function convertMToP(m) {
    return (m / 3.305785).toFixed(0);
}

// 평수 입력 시 제곱미터 값 업데이트
roomSizePInput.addEventListener("input", function () {
    const pValue = parseFloat(roomSizePInput.value);
    if (!isNaN(pValue)) {
        const mValue = convertPToM(pValue);
        roomSizeMInput.value = mValue;
    } else {
        roomSizeMInput.value = "";
    }
});

// 제곱미터 입력 시 평수 값 업데이트
roomSizeMInput.addEventListener("input", function () {
    const mValue = parseFloat(roomSizeMInput.value);
    if (!isNaN(mValue)) {
        const pValue = convertMToP(mValue);
        roomSizePInput.value = pValue;
    } else {
        roomSizePInput.value = "";
    }
});


const selectAllCheckbox = document.querySelector(".all_check");
const detailOptionCheckboxes = document.querySelectorAll(".detailOptionCheckbox");

// "전체선택" 체크박스가 클릭되면 모든 하위 체크박스를 선택 또는 선택 해제
selectAllCheckbox.addEventListener("change", function () {
    const isChecked = selectAllCheckbox.checked;
    detailOptionCheckboxes.forEach(function (checkbox) {
        checkbox.checked = isChecked;
    });
});

//전월세
document.addEventListener("DOMContentLoaded", function () {
    // 라디오 버튼 및 보여줄/숨길 요소에 대한 참조를 가져옵니다.
    var monthlyRadio = document.getElementById("TTC_001");
    var jeonseRadio = document.getElementById("TTC_002");
    var monthlyDiv = document.querySelector(".monthly");
    var jeonseDiv = document.querySelector(".jeonse");

    // 초기 상태 설정: "monthly" 표시, "jeonse" 숨김
    monthlyDiv.style.display = "flex";
    jeonseDiv.style.display = "none";

    // 라디오 버튼에 이벤트 리스너를 추가합니다.
    monthlyRadio.addEventListener("change", function () {
        if (monthlyRadio.checked) {
            // "TTC_001" 선택 시, "monthly" 표시, "jeonse" 숨김
            monthlyDiv.style.display = "flex";
            jeonseDiv.style.display = "none";
        }
    });

    jeonseRadio.addEventListener("change", function () {
        if (jeonseRadio.checked) {
            // "TTC_002" 선택 시, "jeonse" 표시, "monthly" 숨김
            jeonseDiv.style.display = "block";
            monthlyDiv.style.display = "none";
        }
    });
});

//관리비
document.addEventListener("DOMContentLoaded", function () {
    // 라디오 버튼과 관리비 입력 필드에 대한 참조를 가져옵니다.
    var maintenanceNoneRadio = document.getElementById("maintenanceNone");
    var maintenanceYesRadio = document.getElementById("maintenanceYes");
    var maintenanceCostInput = document.getElementById("maintenanceCost");

    // 초기 상태: 관리비 입력 필드 비활성화
    maintenanceCostInput.disabled = true;

    // 라디오 버튼에 이벤트 리스너를 추가합니다.
    maintenanceNoneRadio.addEventListener("change", function () {
        if (maintenanceNoneRadio.checked) {
            // "없음"이 선택된 경우, 관리비 입력 필드 비활성화
            maintenanceCostInput.disabled = true;
        }
    });

    maintenanceYesRadio.addEventListener("change", function () {
        if (maintenanceYesRadio.checked) {
            // "있음"이 선택된 경우, 관리비 입력 필드 활성화
            maintenanceCostInput.disabled = false;
        }
    });
});
//입주일
document.addEventListener("DOMContentLoaded", function () {
    var immediateOccupancyYes = document.getElementById("immediateOccupancyYes");
    var moveInDateField = document.getElementById("moveInDateField");

    // 페이지 로드시 확인하여 필드 상태를 설정
    if (immediateOccupancyYes.checked) {
        moveInDateField.disabled = true; // 즉시 입주 선택 시, 비활성화
    }

    // 라디오 버튼의 변경 이벤트 리스너 추가
    immediateOccupancyYes.addEventListener("change", function () {
        if (immediateOccupancyYes.checked) {
            moveInDateField.disabled = true; // 즉시 입주 선택 시, 비활성화
        }
    });

    var immediateOccupancyNo = document.getElementById("immediateOccupancyNo");
    immediateOccupancyNo.addEventListener("change", function () {
        if (immediateOccupancyNo.checked) {
            moveInDateField.disabled = false; // 일자 선택 선택 시, 활성화
        }
    });
});

// 페이지 로딩이 완료될 때 실행됩니다.
window.addEventListener('DOMContentLoaded', (event) => {
    // 제목 입력 필드와 상세설명 입력 필드를 선택합니다.
    const titleInput = document.querySelector('input[name="title"]');
    const contentTextarea = document.querySelector('textarea[name="content"]')

    // 글자 수를 표시할 요소를 선택합니다.
    const countTitle = document.getElementById('count_title');
    const countContent = document.getElementById('count_content');

    // 제목과 상세설명의 최대 글자 수를 정의합니다.
    const titleMax = 40; // 제목의 최대 글자 수
    const contentMax = 1000; // 상세설명의 최대 글자 수

    // 정규 표현식을 사용하여 제목에서 허용된 문자에 공백, 모음, 자음을 포함한 조건을 설정합니다.
    const titleAllowedCharactersRegex = /[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9+/\-,.㎡]/;

    // 함수를 만들어 입력 값을 최대 글자 수를 초과하지 않도록 잘라냅니다.
    function trimText(text, maxLength) {
        return text.slice(0, maxLength);
    }

    // 함수를 만들어 텍스트에서 허용된 문자 이외의 문자를 제거합니다.
    function filterText(text, regex) {
        return text.split('').filter(char => regex.test(char)).join('');
    }

    // 제목 입력 필드의 입력 변화를 모니터링합니다.
    titleInput.addEventListener('input', function () {
        // 현재 제목 입력 값과 길이를 가져옵니다.
        let titleValue = this.value;
        let titleLength = titleValue.length;

        // 입력 텍스트에서 허용된 문자 이외의 문자를 제거합니다.
        titleValue = filterText(titleValue, titleAllowedCharactersRegex);

        // 최대 글자 수를 초과하는 경우 입력 값을 잘라냅니다.
        if (titleLength > titleMax) {
            titleValue = trimText(titleValue, titleMax);
            titleLength = titleValue.length;
        }

        // 제목 입력 필드에 수정된 값을 설정합니다.
        this.value = titleValue;

        // 제목 글자 수를 표시합니다.
        countTitle.textContent = `${titleLength}/${titleMax}`;

        // 글자 수가 최대 글자 수를 초과하면 글자 수를 빨간색으로 표시합니다.
        if (titleLength > titleMax) {
            countTitle.style.color = 'red';
        } else {
            // 그렇지 않으면 글자 수를 검은색으로 표시합니다.
            countTitle.style.color = 'black';
        }
    });

    // 상세설명 입력 필드의 입력 변화를 모니터링합니다.
    contentTextarea.addEventListener('input', function () {
        // 현재 상세설명 입력 값과 길이를 가져옵니다.
        let contentValue = this.value;
        let contentLength = contentValue.length;

        // 최대 글자 수를 초과하는 경우 입력 값을 잘라냅니다.
        if (contentLength > contentMax) {
            contentValue = trimText(contentValue, contentMax);
            contentLength = contentValue.length;
        }

        // 상세설명 입력 필드에 수정된 값을 설정합니다.
        this.value = contentValue;

        // 상세설명 글자 수를 표시합니다.
        countContent.textContent = `${contentLength}/${contentMax}`;

        // 글자 수가 최대 글자 수를 초과하면 글자 수를 빨간색으로 표시합니다.
        if (contentLength > contentMax) {
            countContent.style.color = 'red';
        } else {
            // 그렇지 않으면 글자 수를 검은색으로 표시합니다.
            countContent.style.color = 'black';
        }
    });
});

const mainImgInput = document.getElementById("mainImg");
const mainImgList = document.getElementById("mainImgList");

const subImgInput = document.getElementById("subImg");
const subImgList = document.getElementById("subImgList");

// 파일이 선택되었을 때 이벤트 핸들러 등록
mainImgInput.addEventListener("change", function () {
    displaySelectedImages(mainImgInput.files, mainImgList, "대표사진");
});

subImgInput.addEventListener("change", function () {
    displaySelectedImages(subImgInput.files, subImgList, "상세내용 사진");
});

function displaySelectedImages(files, imageList, labelText) {
    imageList.innerHTML = ""; // 이미지 목록 초기화

    for (let i = 0; i < files.length; i++) {
        const file = files[i];

        if (file.type.startsWith("image/")) {
            const container = document.createElement("div");
            container.classList.add("image-container"); // 클래스 추가

            const imageElement = document.createElement("img");
            imageElement.src = URL.createObjectURL(file); // 이미지를 표시하기 위해 URL.createObjectURL 사용

            const labelElement = document.createElement("p");
            labelElement.textContent = labelText;

            container.appendChild(imageElement);
            container.appendChild(labelElement);
            imageList.appendChild(container);
        }
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function setApplyItemInfo(){
    const checkedRadio = document.querySelector('#ltem-list-modal')
                                .querySelector('input[type="radio"]:checked');

    document.querySelector('#apply-item-input').value = checkedRadio.value;

    const buyCode = checkedRadio.dataset.buyCode;
    document.querySelector('#apply-item-buyCode').value = buyCode;
    
    if(checkedRadio.dataset.buyType == undefined){
        document.querySelector('#apply-item-buyType').value = '';
    }
    else{
        document.querySelector('#apply-item-buyType').value = checkedRadio.dataset.buyType;
    }

    document.querySelector('#apply-item-memCateCode').value = checkedRadio.dataset.memCateCode;
    
   
    document.querySelector('#modalCloseBtn').click();


}