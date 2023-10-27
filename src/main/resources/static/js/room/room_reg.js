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
            geocoder.addressSearch(data.roadAddress, function(result, status) {

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
function init(){
    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(35.5419831733752, 129.338238429286), //지도의 중심좌표.
        draggable: false, //// 지도를 생성할때 지도 이동 및 확대/축소를 막으려면 draggable: false 옵션을 추가하세요
        level: 3 //지도의 레벨(확대, 축소 정도)
    }
    // 지도를 생성합니다
    map = new kakao.maps.Map(container, options);
}

function changeSubPropertyTypeCode(){
    document.querySelectorAll('input[name="propertyTypeCode"]').forEach(function(radio) {
        radio.addEventListener('change', function() {
            var selectedPropertyTypeCode = radio.value;
            var subcategoryWrapper = document.querySelectorAll('.subproperty-wrapper');

            subcategoryWrapper.forEach(function(wrapper) {
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

    subcategoryWrapper.forEach(function(wrapper) {
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
roomSizePInput.addEventListener("input", function() {
    const pValue = parseFloat(roomSizePInput.value);
    if (!isNaN(pValue)) {
        const mValue = convertPToM(pValue);
        roomSizeMInput.value = mValue;
    } else {
        roomSizeMInput.value = "";
    }
});

// 제곱미터 입력 시 평수 값 업데이트
roomSizeMInput.addEventListener("input", function() {
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
selectAllCheckbox.addEventListener("change", function() {
    const isChecked = selectAllCheckbox.checked;
    detailOptionCheckboxes.forEach(function(checkbox) {
        checkbox.checked = isChecked;
    });
});

