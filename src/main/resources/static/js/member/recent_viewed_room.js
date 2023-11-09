    // // 쿠키에서 값을 가져오는 함수
    // function getCookieValue(cookieName) {
    //     var name = cookieName + "=";
    //     var decodedCookie = decodeURIComponent(document.cookie);
    //     var cookieArray = decodedCookie.split(';');
    //     for(var i = 0; i <cookieArray.length; i++) {
    //         var cookie = cookieArray[i];
    //         while (cookie.charAt(0) == ' ') {
    //             cookie = cookie.substring(1);
    //         }
    //         if (cookie.indexOf(name) == 0) {
    //             return cookie.substring(name.length, cookie.length);
    //         }
    //     }
    //     return "";
    // }

    // // 매물 이미지 쿠키 값 가져와서 출력
    // document.getElementById("cookieIsMain").src = getCookieValue("isMain");

    // // 매물 제목 쿠키 값 가져와서 출력
    // document.getElementById("cookieTitle").innerText = getCookieValue("title");

    // // 매물 유형 쿠키 값 가져와서 출력
    // var propertyTypeNameFromCookie = getCookieValue("propertyTypeName");
    // document.getElementById("cookiePropertyTypeName").innerText = propertyTypeNameFromCookie;

    // // 매물 거래 유형 쿠키 값 가져와서 출력
    // var tradeTypeNameFromCookie = getCookieValue("tradeTypeName");
    // document.getElementById("cookieTradeTypeName").innerText = tradeTypeNameFromCookie;

    // // 매물 월세 정보 쿠키 값 가져와서 출력
    // var monthlyLeaseFromCookie = getCookieValue("monthlyLease");
    // document.getElementById("cookieMonthlyLease").innerText = monthlyLeaseFromCookie + "/";

    // // 매물 보증금 정보 쿠키 값 가져와서 출력
    // var depositFromCookie = getCookieValue("deposit");
    // document.getElementById("cookieDeposit").innerText = depositFromCookie;

    // // 매물 관리비 정보 쿠키 값 가져와서 출력
    // var maintenanceCostFromCookie = getCookieValue("maintenanceCost");
    // document.getElementById("cookieMaintenanceCost").innerText = maintenanceCostFromCookie + "만";

    // // 매물 층 정보 쿠키 값 가져와서 출력
    // var titleFromCookie = getCookieValue("floor");
    // document.getElementById("cookieFloor").innerText = titleFromCookie + "층";

    // // 매물 평(m²) 쿠키 값 가져와서 출력
    // var roomSizeMFromCookie= getCookieValue("roomSizeM");
    // document.getElementById("cookieRoomSizeM").innerText = roomSizeMFromCookie + "m²";


    function getRandomImage() {
        var images = ["fec1a14f-93ef-48cf-8081-e610942fcbfd.jpg"
        , "3bb61cad-4722-421e-a3cb-072aab926ce8.jpg"
        , "3ad46480-f9d8-4356-9f7d-308915712bff.jpg"
        , "2add7544-2e8a-41ac-9775-91384ce12ef1.jpg"
        , "1d11ca72-3692-472a-9445-7c2f3f858ae7.jpg",];
        var randomIndex = Math.floor(Math.random() * images.length);
        return images[randomIndex];
    }

    document.addEventListener("DOMContentLoaded", function() {
        var recentViewList = document.querySelectorAll('[id^=randomImg]');
        recentViewList.forEach(function (imgElement) {
            imgElement.src = '/img/room/' + getRandomImage();
        });
    });

    function getRandomImage() {
        var images = [
            "f2e729d3-7629-415e-a671-ef5c6ff580c4.png",
            "dff2fbab-8194-44b1-baba-f811558b2b1c.png",
            "d1e58bf2-d785-48e7-a438-e3a45eed906d.png"
        ];
        var randomIndex = Math.floor(Math.random() * images.length);
        return images[randomIndex];
    }

    document.addEventListener("DOMContentLoaded", function() {
        var recentViewList = document.querySelectorAll('[id^=randomImg]');
        recentViewList.forEach(function (imgElement) {
            imgElement.src = '/img/room/' + getRandomImage();
        });
    });