let map;
let container = document.getElementById('map');
let options = {
    center: new kakao.maps.LatLng(35.5419831733752, 129.338238429286), //지도의 중심좌표.
    level: 3 //지도의 레벨(확대, 축소 정도)
}
// 지도를 생성합니다
map = new kakao.maps.Map(container, options);
