/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function myFunction() {
    var z = document.getElementById("hid");
    var buttonAdd = document.getElementById("buttonAdd");
    var buttonNew = document.getElementById("buttonNew");
    var ShowText = document.getElementById("ShowTextWrit");
    var ShowDrop = document.getElementById("ShowDrop");
   
    if (z.style.display === "block") {
        z.style.display = "none";
        list.value = '';
        list.style.display = "block";

        buttonAdd.style.display = "block";
        buttonNew.style.display = "none";
        
        ShowText.style.display = "none";
        ShowDrop.style.display = "block";
        
    } else {
        z.style.display = "block";
        list.style.display = "none";
        z.value = '';
        list.value = false;

        buttonAdd.style.display = "none";
        buttonNew.style.display = "block";
        
        ShowText.style.display = "block";
        ShowDrop.style.display = "none";

    }
}

function Prueba(){
    z.style.display="block";
    list.style.display="none";
}

function confirmar(){
    if (z != "") {
        alert("no hay informacion");
    }else{
        alert("si hay informacion");
    }
}



/*function showNewButton() {
    buttonAdd.style.display = "none";
    buttonNew.style.display = "block";
    
}

function showAddButton() {
    buttonAdd.style.display = "block";
    buttonNew.style.display = "none";
}*/







