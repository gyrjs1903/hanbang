//import gg from '../../json/sido.json';
//const jsonData = require('../../json/sido.json');


//html실행과 동시에 실행되는 함수
let map;
let positonData;
setMap();

let container = document.getElementById('map');
let options = {
    center: new kakao.maps.LatLng(35.5419831733752, 129.338238429286), //지도의 중심좌표.
    level: 5 //지도의 레벨(확대, 축소 정도)
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
            const gData= getPolygonData();
            //console.log(gData);
         gData.then(res => {
                          console.log(res.features[0].geometry);
            //     console.log(res.features[0].geometry.coordinates);
            //     console.log(res.features[0].geometry.coordinates[0]);
            //     console.log(res.features[0].geometry.coordinates[0][0][0]);
            //     console.log(res.features[0].geometry.coordinates[0][0][1]);
           });

            let polygonPath = [];
            
            gData.then(res => {

                //console.log(res.features[0].geometry.coordinates);
                //console.log(res.features[0].geometry.coordinates[0]);
                //console.log(res.features[0].geometry.coordinates[0][0][0]);
                //console.log(res.features[0].geometry.coordinates[0][0][1]);
              
                // res.features[0].geometry.coordinates[0].forEach((element, idx) => {
                //     //console.log(`x = ${element[1]} / y = ${element[0]}`);
                //     //console.log(`x = ${element[idx][1]} / y = ${element[idx][0]}`);
                //     polygonPath.push(new kakao.maps.LatLng(element[1],element[0]));
                // });

                


              


                // res.features.forEach((element, idx) => {
                //     //console.log(`x = ${element[1]} / y = ${element[0]}`);
                //     //console.log(`x = ${element[idx][1]} / y = ${element[idx][0]}`);
                //     console.log(element.geometry.coordinates[0][1][1]);
                //     polygonPath.push(new kakao.maps.LatLng(element.geometry.coordinates[0][1][0],element.geometry.coordinates[0][1][1]));
                // });
            })
            

            //console.log(11);
            //console.log('@@@@@@@@@'+polygonPath);


            // 지도를 생성합니다
            map = new kakao.maps.Map(container, options);


            let positions = data.map(addrData => {

                return {
                    title: addrData.addr,
                    latlng: new kakao.maps.LatLng(parseFloat(addrData.coordinateY), parseFloat(addrData.coordinateX))
                }
            });



        // 다각형을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 다각형을 표시합니다 
        var polygonPath1 = [
            new kakao.maps.LatLng(33.450965145649576, 126.57020280169624),
            new kakao.maps.LatLng(33.450958085478554, 126.57011679275952),
            new kakao.maps.LatLng(33.45098043867851, 126.57006290510864),
            new kakao.maps.LatLng(33.45097328515681, 126.56995000794919),
            new kakao.maps.LatLng(33.450990859851004, 126.56981816664641),
            new kakao.maps.LatLng(33.45101296099739, 126.5696916806749),
            new kakao.maps.LatLng(33.45098334215462, 126.56960040542974),
            new kakao.maps.LatLng(33.450985176064975, 126.56947939729541),
            new kakao.maps.LatLng(33.450917277011726, 126.56939906680272),
            new kakao.maps.LatLng(33.45086322853736, 126.56941277823229),
            new kakao.maps.LatLng(33.45081577388131, 126.56937805752437),
            new kakao.maps.LatLng(33.450779813154405, 126.56940781273165),
            new kakao.maps.LatLng(33.45081633405741, 126.56953938654304),
            new kakao.maps.LatLng(33.45080569884398, 126.56972228175628),
            new kakao.maps.LatLng(33.450752219367345, 126.56990001069012),
            new kakao.maps.LatLng(33.45065801908533, 126.57003491859678),
            new kakao.maps.LatLng(33.45064506393239, 126.5701990028593),
            new kakao.maps.LatLng(33.45063139100987, 126.57015604858434)
        ];
        console.log(11);
        console.log(polygonPath1);


        data111 = [
            [128.7213262450712, 36.00636035178296],
            [128.72189352615018, 36.00619860741209],
            [128.7309901087172, 35.99767247099226],
            [128.73187299735793, 35.997527171299716],
            [128.73470063430165, 35.993075802741764],
            [128.73485570688902, 35.99295604003034],
            [128.73415626662583, 35.991806537428346],
            [128.73316403261364, 35.99068829761383],
            [128.73206401923736, 35.99010720002594],
            [128.73159182966748, 35.98938130012162],
            [128.73118684774465, 35.98751143292643],
            [128.73140117827168, 35.98601429264223],
            [128.73097394553133, 35.985361435873465],
            [128.73223979296566, 35.98375306944311],
            [128.73319290390504, 35.98310317364049],
            [128.74363020524913, 35.94510567122465],
            [128.7437041617869, 35.94492522278742],
            [128.74408670694478, 35.943958073105826],
            [128.74381378715702, 35.9429783690565],
            [128.74304792451628, 35.942274788373474],
            [128.73983979749383, 35.93760678987219],
            [128.7397672408868, 35.9375029898236],
            [128.75550760597136, 35.91464484971569],
            [128.75565181427106, 35.91439938622806],
            [128.7613815432334, 35.88723788944046],
            [128.76128637388868, 35.88706562490887],
            [128.7598178227882, 35.86685930984265],
            [128.75981857260155, 35.866784111738006],
            [128.74032326310075, 35.85220076728609],
            [128.73988154210204, 35.85199427699523],
            [128.7374535240631, 35.85116064830068],
            [128.73628227309777, 35.851284856918596],
            [128.72497076697286, 35.85329074769074],
            [128.72557729319746, 35.85034325988758],
            [128.72653353651162, 35.84800548731046],
            [128.72676964616883, 35.844842050756206],
            [128.7263471311609, 35.84256087986203],
            [128.72613197307535, 35.842317815982895],
            [128.72434784131892, 35.84031296046613],
            [128.72406310338536, 35.839426060178475],
            [128.7242141017945, 35.83866793458516],
            [128.72507972759868, 35.83615857082713],
            [128.72507251360403, 35.83604898872359],
            [128.72440707802312, 35.835761228003854],
            [128.72240445355493, 35.83500728788938],
            [128.72238701227232, 35.83497176409156],
            [128.72226501840004, 35.834843476234504],
            [128.72209886003142, 35.83478591484457],
            [128.72199394103438, 35.834721736258885],
            [128.72180163715853, 35.834671585024196],
            [128.72169664697066, 35.8346782442631],
            [128.7215565374535, 35.834883790585714],
            [128.72139001748337, 35.8351749029518],
            [128.7212847338874, 35.83535926959967],
            [128.72115320889262, 35.83548668806004],
            [128.7154888327596, 35.83317869947243],
            [128.71412235160616, 35.83178805529844],
            [128.71317004718932, 35.831066398302426],
            [128.71267678310016, 35.83070759673058],
            [128.71239815491782, 35.830323246360734],
            [128.71227892371564, 35.83022053274917],
            [128.71119570554396, 35.82941763838131],
            [128.71090650498414, 35.82910699057312],
            [128.71037257507487, 35.828428769399615],
            [128.71035361434193, 35.82829975382099],
            [128.71031656709215, 35.828043493285605],
            [128.71030266593868, 35.82785919873221],
            [128.71023528608177, 35.827796795312196],
            [128.71001922945322, 35.82783281735054],
            [128.70862298975973, 35.826381997054476],
            [128.7087452139889, 35.826212003391824],
            [128.70857062454414, 35.823372132268055],
            [128.70866725043237, 35.82287907671741],
            [128.71706571802, 35.80852810680546],
            [128.7170897650298, 35.8085205572397],
            [128.7143282855512, 35.80475816185505],
            [128.71019923491795, 35.80426598751514],
            [128.7085215601008, 35.80292115122969],
            [128.68251170282812, 35.79014861991538],
            [128.68912569787108, 35.739772794681855],
            [128.6891106166496, 35.73923002367715],
            [128.6832397085274, 35.72145444422488],
            [128.6244976030435, 35.70335495481642],
            [128.61479842452982, 35.73094200818587],
            [128.58051590399288, 35.73866660199441],
            [128.52790486607597, 35.71274079417096],
            [128.52985121593642, 35.68301533440054],
            [128.50945505836145, 35.67471463736342],
            [128.50623621037877, 35.639556969852805],
            [128.45909489107632, 35.640273388200384],
            [128.45901827212916, 35.64026787811151],
            [128.4309803226448, 35.621638800488626],
            [128.37174762364575, 35.61088094277428],
            [128.40125141360602, 35.632728696053555],
            [128.4014182978501, 35.633438194198014],
            [128.38368627024056, 35.65848588282504],
            [128.38266941694133, 35.65954413424245],
            [128.35718642311096, 35.6821952331281],
            [128.35693445710064, 35.68263837560539],
            [128.35887829808627, 35.70870122987159],
            [128.41220175603553, 35.69584190063279],
            [128.43398078084127, 35.707070292041124],
            [128.41150864211912, 35.73855726955638],
            [128.4086866758349, 35.73973535132349],
            [128.3936963726598, 35.74682288793781],
            [128.3932443940482, 35.74708427426636],
            [128.38329416135437, 35.758585218680025],
            [128.42082951598775, 35.809354575222386],
            [128.47043315408257, 35.8059223363035],
            [128.48271590445026, 35.81551710952425],
            [128.4828275850767, 35.816692669593934],
            [128.48288998679786, 35.81735036892825],
            [128.48295230525162, 35.81800575212713],
            [128.48299134847161, 35.81891557318833],
            [128.48301345875836, 35.81943615495395],
            [128.48302581262448, 35.8197281752041],
            [128.4830589247011, 35.82050544251713],
            [128.48306896006807, 35.82113880777162],
            [128.48299736685053, 35.8224215904517],
            [128.4824594965352, 35.826002999737355],
            [128.481969925052, 35.82721759931332],
            [128.48145861204262, 35.828751339419235],
            [128.48121712907113, 35.8300864765222],
            [128.4767314903397, 35.8316469716899],
            [128.4752834681512, 35.83215067319142],
            [128.47415503649057, 35.83254327338718],
            [128.47318775873808, 35.833179456892],
            [128.47278363274324, 35.83344939550027],
            [128.47254620952359, 35.833608330168374],
            [128.468919315182, 35.839345827153906],
            [128.46899205350073, 35.83963096777291],
            [128.38351017125282, 35.85283465086274],
            [128.39806677041497, 35.89270855459797],
            [128.43103622596146, 35.93052647248881],
            [128.47641886601411, 35.934439337455935],
            [128.4683790422405, 35.89947083416591],
            [128.46909837797443, 35.899013522731224],
            [128.47847679821518, 35.89682429582409],
            [128.47916501839438, 35.89661456382814],
            [128.50478828611696, 35.89137522049469],
            [128.5345213218195, 35.93868815807367],
            [128.52665053698558, 35.97544743179838],
            [128.5282794993138, 35.979008268449114],
            [128.5283538521437, 35.98034994120992],
            [128.5616645865135, 35.97253036133529],
            [128.5656770998791, 35.9737329674323],
            [128.56567040421973, 35.974920818802445],
            [128.5690717857875, 35.97651703426509],
            [128.57283756882677, 35.97831254293741],
            [128.57430153839093, 35.97782284818138],
            [128.57503192622565, 35.977924174254525],
            [128.57856128906258, 35.97843202265338],
            [128.5812427078083, 35.97804535550383],
            [128.58233771498902, 35.97824716519045],
            [128.58331417591256, 35.97785468280457],
            [128.58322029710388, 35.97728087943594],
            [128.58509702240704, 35.977957602650015],
            [128.5873638411499, 35.97939366083716],
            [128.58798603283356, 35.97897598918454],
            [128.59015554229657, 35.97923540064925],
            [128.59087467316112, 35.98007802177191],
            [128.59304155877055, 35.98084165111779],
            [128.59292518288288, 35.98336183401906],
            [128.59416334635716, 35.98378610378364],
            [128.5961238206889, 35.984548824754626],
            [128.59839560696295, 35.98506039671276],
            [128.60350435358643, 35.986013434130804],
            [128.60349908416848, 35.986422356373005],
            [128.60349379380537, 35.9868109907817],
            [128.60335923053947, 35.98798734396207],
            [128.60360071742093, 35.98822601388796],
            [128.6048102308872, 35.9894207330141],
            [128.60599289304102, 35.99082503096723],
            [128.60613397041328, 35.991203584643976],
            [128.6065089805702, 35.99208347593722],
            [128.60704146144633, 35.99255381861844],
            [128.60769318375165, 35.99311392448162],
            [128.60830937859535, 35.99341143755218],
            [128.60864341837433, 35.99359652607783],
            [128.60883809343383, 35.99393624011929],
            [128.60900277604858, 35.99463980333418],
            [128.60954479511366, 35.9950512986295],
            [128.60957858135694, 35.99507703075352],
            [128.61021862722347, 35.996102756639374],
            [128.61019018001957, 35.996116379107306],
            [128.61019523767132, 35.996280201782646],
            [128.6101956776179, 35.996285269423865],
            [128.61019819522159, 35.99632353305128],
            [128.61013693501997, 35.99644656652364],
            [128.61006373148894, 35.996681555757654],
            [128.61005254997616, 35.996747147072654],
            [128.61038938542632, 35.99691570088881],
            [128.61055192736796, 35.99698504796746],
            [128.61069255357995, 35.99705018683192],
            [128.61085797653803, 35.997093352744294],
            [128.61083393147302, 35.99711812176804],
            [128.6108091786371, 35.99714492462769],
            [128.6110300993498, 35.99751170226147],
            [128.61137821381337, 35.99767457704121],
            [128.6118236547161, 35.997955764150866],
            [128.6120432935831, 35.99837696954391],
            [128.6125159054562, 35.99929678594049],
            [128.61355287406565, 35.99952105019799],
            [128.61571659335465, 36.000977155652464],
            [128.6161513579533, 36.00214219240173],
            [128.61628770994176, 36.00255767890544],
            [128.61665188806717, 36.00364199882694],
            [128.61742843564923, 36.00652384430621],
            [128.61748582034008, 36.00673736606678],
            [128.63242994354897, 36.008139888634005],
            [128.63302527467488, 36.008270052417316],
            [128.63484631574687, 36.00903035901048],
            [128.6362433291671, 36.009095372613835],
            [128.6373973372334, 36.009192102800334],
            [128.63995920834478, 36.00957135211684],
            [128.6401301090318, 36.00980897061375],
            [128.64078254646668, 36.010549961362074],
            [128.6541914088714, 36.00945538853135],
            [128.65426791497893, 36.00944685050893],
            [128.66417966762262, 36.01011125779763],
            [128.66418455167434, 36.01011287618484],
            [128.6736468735633, 36.01338298645338],
            [128.67745313563154, 36.013246491457856],
            [128.7213262450712, 36.00636035178296]
        ];

        data111.forEach((element, idx) => {
            //console.log(`x = ${element[1]} / y = ${element[0]}`);
            //console.log(`x = ${element[idx][1]} / y = ${element[idx][0]}`);
            polygonPath.push(new kakao.maps.LatLng(element[1],element[0]));
        });

        console.log(22);
        console.log(polygonPath);


        // 지도에 표시할 다각형을 생성합니다
        var polygon = new kakao.maps.Polygon({
        path:polygonPath, // 그려질 다각형의 좌표 배열입니다
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: '#39DE2A', // 선의 색깔입니다
        strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid', // 선의 스타일입니다
        fillColor: '#A2FF99', // 채우기 색깔입니다
        fillOpacity: 0.7 // 채우기 불투명도 입니다
        });

        // 지도에 다각형을 표시합니다
        polygon.setMap(map);



            // // 다각형을 생성합니다 
            // var polygon = new kakao.maps.Polygon({
            //     //map: map, // 다각형을 표시할 지도 객체
            //     //path: area.path,
            //     path :  polygonPath,
            //     strokeWeight: 2,
            //     strokeColor: '#004c80',
            //     strokeOpacity: 0.8,
            //     fillColor: '#fff',
            //     fillOpacity: 0.7 
            // });
            // polygon.setMap(map);


            let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

            for (let i = 0; i < positions.length; i++) {

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
                contentText.textContent = room.content;
                roomInfo.appendChild(contentText);

                roomElement.appendChild(roomInfo);

                roomContainer.appendChild(roomElement);

                selectRoomElement.appendChild(roomContainer);

            });

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
});
function detailRoom(roomCode) {
    location.href = `/room2/roomDetailInfo?roomCode=${roomCode}`
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
async function getPolygonData(){
    return await fetch("/json/sido.json") // json 파일 읽어오기
      .then(function (response) {
        return response.json(); // 읽어온 데이터를 json으로 변환
      });
}
      