function onPackage(){
    document.querySelector('.generalForm').classList.remove('active');
    document.querySelector('.plusForm').classList.remove('active');
    document.querySelector('.packageForm').classList.add('active');
}
function onGeneral(){
    document.querySelector('.packageForm').classList.remove('active');
    document.querySelector('.plusForm').classList.remove('active');
    document.querySelector('.generalForm').classList.add('active');
}
function onPlus(){
    document.querySelector('.generalForm').classList.remove('active');
    document.querySelector('.packageForm').classList.remove('active');
    document.querySelector('.plusForm').classList.add('active');
}