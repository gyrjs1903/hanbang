//Cookies----------------------------------------------------
// 페이지 로드 후 실행할 함수
function onPageLoad() {
    var url = window.location.href;
    var userNo = getUserNoFromURL(url);
    console.log("userNo::" + userNo);
  
    if (userNo) {
      var recentProducts = getCookie('recent_products');
      var recentuserNos = recentProducts ? recentProducts.split('/') : [];
     
      if (!recentuserNos.includes(userNo)) {
        recentuserNos.push(userNo);
      }
  
      // 최대 10개까지 유지
      if (recentuserNos.length > 30) {
        recentuserNos.shift();
      }
  
      recentProducts = recentuserNos.join('/');
  
      // 24시간 후의 시간 객체 생성
      var expirationDate = new Date();
      expirationDate.setTime(expirationDate.getTime() + (24 * 60 * 60 * 1000));
      console.log("expirationDate::" + expirationDate);
  
      // 쿠키에 recent_products 저장 (유효기간: 24시간)
      setCookie('recent_products', recentProducts, expirationDate);
    }
  }
  
  // 페이지 로드 이벤트 리스너 등록
  window.addEventListener('load', onPageLoad);
  
  
  function setCookie(cookieName, value, expirationDate) {
    var cookieValue = escape(value) + ((expirationDate == null) ? '' : '; expires=' + expirationDate.toUTCString());
    document.cookie = cookieName + '=' + cookieValue;
  }
  
  function getCookie(cookieName) {
    var name = cookieName + '=';
    var decodedCookie = decodeURIComponent(document.cookie);
    var cookieArray = decodedCookie.split(';');
  
    for (var i = 0; i < cookieArray.length; i++) {
      var cookie = cookieArray[i];
      while (cookie.charAt(0) == ' ') {
        cookie = cookie.substring(1);
      }
      if (cookie.indexOf(name) == 0) {
        return cookie.substring(name.length, cookie.length);
      }
    }
    return '';
  }
  
  //url에서 userNo를 가져오는 함수
  function getuserNoFromURL(url) {
    var regex = /[?&]product_id=(\d+)/;
    var match = regex.exec(url);
    if (match && match[1]) {
      return match[1];
    }
    return null;
  }
  
  //Cookies End----------------------------------------------------