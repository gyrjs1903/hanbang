function deleteFalseOfferings(roomCode){
    if(confirm('해당 허위매물을 삭제 하시겠습니까?')){
        location.href=`/admin/deleteFalseOfferings?roomCode=${roomCode}`;
    }
}