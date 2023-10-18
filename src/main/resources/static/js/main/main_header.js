/* main_header.js */

function my_menu_open(){
    let click = document.getElementById("my_menu_wrap");
    if(click.style.display === "none" || click.style.display === ""){
        click.style.display = "block";

    }else{
        click.style.display = "none";

    }
}