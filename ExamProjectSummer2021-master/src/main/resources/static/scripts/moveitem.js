/**
 * @author Carsten
 */

document.addEventListener("DOMContentLoaded", function (){

    var workers = document.querySelectorAll(".moveWorker");

    var list1 = document.getElementById("myUL");
    var list2 = document.getElementById("assignedUsers");

    function moveItem(e){
        var moveTo = this.parentElement.parentElement === list1 ? list2 : list1;
        moveTo.appendChild(this.parentElement);
        changeAvailability()
    }

    for (var i = 0; i < workers.length; i++) {
        workers[i].addEventListener("click", moveItem);
    }

});

function changeAvailability(){
    var nodes1 = document.getElementById("myUL").getElementsByTagName("input");
    for (let i = 0; i < nodes1.length; i++) {
        nodes1[i].disabled = true;
    }

    var nodes2 = document.getElementById("assignedUsers").getElementsByTagName("input");
    for (let i = 0; i < nodes2.length; i++) {
        nodes2[i].disabled = false;
    }

}