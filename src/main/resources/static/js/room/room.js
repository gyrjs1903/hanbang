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


    // // 마커가 표시될 위치입니다 
    // var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

    // // 마커를 생성합니다
    // var marker = new kakao.maps.Marker({
    //     position: markerPosition
    // });

    // marker.setMap(map);
}


// //검색 검색 시 map 정보 변경
// function changeMap(pos){
//     console.log(pos.x);
//     console.log(pos.y);
//     let container = document.getElementById('map');
//     let options = {
//         center: new kakao.maps.LatLng(pos.y, pos.x), //지도의 중심좌표.
//         level: 3 //지도의 레벨(확대, 축소 정도)
//     }
//     // 지도를 생성합니다
//     map = new kakao.maps.Map(container, options);

//     // 마커가 표시될 위치입니다 
//     var markerPosition  = new kakao.maps.LatLng(pos.y, pos.x); 

//     // 마커를 생성합니다
//     var marker = new kakao.maps.Marker({
//         position: markerPosition
//     });

//     //marker.setMap(null);
//     marker.setMap(map);
// }


////////////////////////////////////////

// async function readPosition(address){
//     const placeAddress = address;
//     await fetch('https://dapi.kakao.com/v2/local/search/address.json?query=' + encodeURI(placeAddress), { //요청경로
//         method: 'get',
//         cache: 'no-cache',
//         headers: {
//             'Content-Type': 'application/json; charset=UTF-8',
//             'Authorization': 'KakaoAK ae4173e8e574073dcc363265fc534ea4'
//         },
//         //컨트롤러로 전달할 데이터
//         //body: JSON.stringify({
//            // 데이터명 : 데이터값
//         //})
//     })
//     .then((response) => {
//         return response.json(); //나머지 경우에 사용
//     })
//     //fetch 통신 후 실행 영역
//     .then((data) => {//data -> controller에서 리턴되는 데이터!
//         console.log(data);
//         console.log(data.documents[0].x);
//         console.log(data.documents[0].y);
//         console.log(2);

//         const pos = {
//             'x' : data.documents[0].x,
//             'y' : data.documents[0].y
//         };

//         changeMap(pos);
        
//     })
//     //fetch 통신 실패 시 실행 영역
//     .catch(err=>{
//         alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
//         console.log(err);
//     });
// }





