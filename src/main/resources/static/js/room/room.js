//전역변수
let map;
let geocoder = new kakao.maps.services.Geocoder();

//화면 오픈 시 바로 실행되는 함수
init();


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






