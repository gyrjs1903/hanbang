

//html실행과 동시에 실행되는 함수
let map;
let positonData;
setMap();

let container = document.getElementById('map');
let options = {
    center: new kakao.maps.LatLng(35.5419831733752, 129.338238429286), //지도의 중심좌표.
    level: 3 //지도의 레벨(확대, 축소 정도)
}



function setMap() {

    fetch('/room/setMap', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'

        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
        })
    })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            // 지도를 생성합니다
            map = new kakao.maps.Map(container, options);


            let positions = data.map(addrData => {

                return {
                    title: addrData.addr,
                    latlng: new kakao.maps.LatLng(parseFloat(addrData.coordinateY), parseFloat(addrData.coordinateX))
                }
            });


            let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

            for (let i = 0; i < positions.length; i++) {
                console.log(positions[i])
                // 마커 이미지의 이미지 크기 입니다
                let imageSize = new kakao.maps.Size(24, 35);

                // 마커 이미지를 생성합니다    
                let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                var clusterer = new kakao.maps.MarkerClusterer({
                    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
                    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
                    minLevel: 3 // 클러스터 할 최소 지도 레벨 
                });

                var markers = $(positions).map(function (i, position) {
                    return new kakao.maps.Marker({
                        map: map, // 마커를 표시할 지도
                        position: new kakao.maps.LatLng(positions[i].latlng.Ma, positions[i].latlng.La), // 마커를 표시할 위치
                        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                        image: markerImage // 마커 이미지 
                        //position: new kakao.maps.LatLng(position.lat, position.lng)
                    });
                });
                clusterer.addMarkers(markers);


            }

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}


document.getElementById("searchButton").addEventListener("click", function () {
    // 입력 필드와 선택 상자의 값을 가져옵니다.
    const searchTradeTypeCode = document.querySelector('select[name="searchTradeTypeCode"]').value;
    const searchPropertyTypeCode = document.querySelector('input[name="searchPropertyTypeCode"]:checked').value;
    const searchAddr = document.querySelector('input[name="searchAddr"]').value;
    const searchRoomSizePMin = document.querySelector('input[name="searchRoomSizePMin"]').value;
    const searchRoomSizePMax = document.querySelector('input[name="searchRoomSizePMax"]').value;
    const searchRoomSizeMMin = document.querySelector('input[name="searchRoomSizeMMin"]').value;
    const searchRoomSizeMMax = document.querySelector('input[name="searchRoomSizeMMax"]').value;
    const searchFloor = document.querySelector('input[name="searchFloor"]').value;
    const searchMonthlyLeaseMax = document.querySelector('input[name="searchMonthlyLeaseMax"]').value;
    const searchDeposit = document.querySelector('input[name="searchDeposit"]').value;
    const searchJeonseCost = document.querySelector('input[name="searchJeonseCost"]').value;
    const searchMaintenanceCost = document.querySelector('input[name="searchMaintenanceCost"]').value;
    const searchDetailOptions = Array.from(document.querySelectorAll('.detailOptionCheckbox:checked'))
        .map(checkbox => checkbox.value);


    // 필요한 데이터를 가지고 fetch 요청을 생성합니다.

    // 예를 들어, JSON 형식의 데이터를 전송하려면 다음과 같이 할 수 있습니다.
    const searchData = {
        searchTradeTypeCode,
        searchPropertyTypeCode,
        searchAddr,
        searchRoomSizePMin,
        searchRoomSizePMax,
        searchRoomSizeMMin,
        searchRoomSizeMMax,
        searchFloor,
        searchMonthlyLeaseMax,
        searchDeposit,
        searchJeonseCost,
        searchMaintenanceCost,
        searchDetailOptions
    };

    fetch('/room/roomSearch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(
            // 데이터명 : 데이터값
            searchData
        )
    })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            const radioElement = document.querySelector('input[name="searchPropertyTypeCode"]:checked');
            const propertyTypeName = radioElement.getAttribute('data-propertytypename');
            console.log(propertyTypeName);
            console.log(data)

            // .select_room 요소
            const selectRoomElement = document.querySelector('.select_room');
            const roomContainer = document.createElement('div');

            // 이전에 표시된 내용을 삭제
            selectRoomElement.innerHTML = '';
            data.forEach((room, idx) => {
                const roomElement = document.createElement('div');
                roomElement.className = 'room';
                const imgElement = document.createElement('div');
                roomElement.append(imgElement);

                // 방 이미지 추가
                const roomImage = document.createElement('img');
                roomImage.src = '/img/room/' + room.imgList[0].attachedFileName;
                roomImage.alt = '';
                imgElement.appendChild(roomImage);
                
                // 방정보 div
                const roomInfo = document.createElement('div');
                roomInfo.className = 'room-info';

                //전월세 추가
                if (room.tradeTypeCode !== 'TTC_001') {
                    const jeonseInfo = document.createElement('h4');
                    jeonseInfo.textContent = '전세 ' + room.jeonseCost;
                    roomInfo.appendChild(jeonseInfo);
                } else {
                    const leaseInfo = document.createElement('h4');
                    leaseInfo.textContent = '월세 ' + room.deposit + '/' + room.monthlyLease;
                    roomInfo.appendChild(leaseInfo);
                }


                //주소
                const addrInfo = document.createElement('div')
                addrInfo.textContent = room.roomAddrVO.addr;
                roomInfo.appendChild(addrInfo);
                
                //매물유형, 평, 층
                const propertyTypeInfo = document.createElement('p');
                propertyTypeInfo.textContent = propertyTypeName + ', ' + room.roomSizeP + '평, ' + room.floor + '층';
                roomInfo.appendChild(propertyTypeInfo);

                //상세내용
                const contentText = document.createElement('p');
                contentText.textContent= room.content;
                roomInfo.appendChild(contentText);

                roomElement.appendChild(roomInfo);
                
                roomContainer.appendChild(roomElement);
                
                selectRoomElement.appendChild(roomContainer);
                console.log(roomContainer);
                // selectRoomElement.insertAdjacentHTML("afterbegin",roomContainer);
                
            });
            
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
});
function detailRoom(roomCode){
    location.href=`/room2/roomDetailInfo?roomCode=${roomCode}`
}


// kakao.maps.event.addListener(map, 'dragend', function() {
//     // 현재 지도 화면의 경계 좌표를 가져옵니다.
//     var bounds = map.getBounds();
//     var swLatLng = bounds.getSouthWest();
//     var neLatLng = bounds.getNorthEast();

//     // 서버에 경계 좌표를 전송하여 방 데이터를 가져옵니다.
//     fetch('/room/getRoomsInBounds', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json; charset=UTF-8'
//         },
//         body: JSON.stringify({
//             swLat: swLatLng.getLat(),
//             swLng: swLatLng.getLng(),
//             neLat: neLatLng.getLat(),
//             neLng: neLatLng.getLng()
//         })
//     })
//     .then((response) => response.json())
//     .then((roomData) => {
//         // 방 데이터를 HTML로 표시합니다.
//         renderRoomData(roomData);
//     })
//     .catch(err => {
//         console.error('방 데이터를 가져오는 데 실패했습니다:', err);
//     });
// });

// function renderRoomData(roomData) {
//     // HTML 페이지에서 기존 방 데이터를 지웁니다.
//     document.getElementById('room-list').innerHTML = '';

//     // 방 데이터를 반복하고 HTML 요소를 만듭니다.
//     roomData.forEach(function(room) {
//         var roomElement = document.createElement('div');
//         roomElement.innerHTML = '방 이름: ' + room.name + ', 가격: ' + room.price;
//         document.getElementById('room-list').appendChild(roomElement);
//     });
// }
