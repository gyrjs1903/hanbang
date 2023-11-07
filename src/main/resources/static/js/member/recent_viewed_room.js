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
