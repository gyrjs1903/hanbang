function onPackage(){
    document.querySelector('.generalForm').classList.remove('active');
    document.querySelector('.plusForm').classList.remove('active');
    document.querySelector('.packageForm').classList.add('active');

    document.querySelector('.packageBtn').classList.add('btnActive');
    document.querySelector('.generalBtn').classList.remove('btnActive');
    document.querySelector('.plusBtn').classList.remove('btnActive');
}
function onGeneral(){
    document.querySelector('.packageForm').classList.remove('active');
    document.querySelector('.plusForm').classList.remove('active');
    document.querySelector('.generalForm').classList.add('active');

    document.querySelector('.packageBtn').classList.remove('btnActive');
    document.querySelector('.generalBtn').classList.add('btnActive');
    document.querySelector('.plusBtn').classList.remove('btnActive');
}
function onPlus(){
    document.querySelector('.generalForm').classList.remove('active');
    document.querySelector('.packageForm').classList.remove('active');
    document.querySelector('.plusForm').classList.add('active');
    
    document.querySelector('.packageBtn').classList.remove('btnActive');
    document.querySelector('.generalBtn').classList.remove('btnActive');
    document.querySelector('.plusBtn').classList.add('btnActive');
}