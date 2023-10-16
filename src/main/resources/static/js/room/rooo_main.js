//html실행과 동시에 실행되는 함수
map();




let coordinateXList = document.querySelectorAll('.coordinate-X')
let coordinateYList = document.querySelectorAll('.coordinate-Y')

function map(){
    let map;
    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(35.5419831733752, 129.338238429286), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
    }
    // 지도를 생성합니다
    map = new kakao.maps.Map(container, options);

    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 3 // 클러스터 할 최소 지도 레벨 
    });
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("/download/web/data/chicken.json", function(data) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        var markers = $(data.positions).map(function(i, position) {
            return new kakao.maps.Marker({
                position : new kakao.maps.LatLng(position.lat, position.lng)
            });
        });

        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });
}


// function setMap(){

//     fetch('/room/setMap', { //요청경로
//         method: 'POST',
//         cache: 'no-cache',
//         headers: {
//             'Content-Type': 'application/json; charset=UTF-8'
            
//         },
//         //컨트롤러로 전달할 데이터
//         body: JSON.stringify({
//         // 데이터명 : 데이터값
        
//         })
//     })
//     .then((response) => {
//         return response.json(); //나머지 경우에 사용
//     })
//     //fetch 통신 후 실행 영역
//     .then((data) => {//data -> controller에서 리턴되는 데이터!
        
//     })
//     //fetch 통신 실패 시 실행 영역
//     .catch(err=>{
//         alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
//         console.log(err);
//     });

// }