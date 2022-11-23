/**
 * @author Carsten
 */

function createProject(){
    let usersAdded, availableTitle;

    usersAdded = haveUsersBeenAdded();
    availableTitle = titleCheck();

    if(!usersAdded){
        alert("Add users to Project");
        return false;
    }
    else if(availableTitle){
        alert("Project title already in use")
        return false;
    }
    else {
        return true;
    }
}

function haveUsersBeenAdded(){
    return document.getElementById("assignedUsers").children[0] != null;
}

function titleCheck() {
    let input = document.getElementById("title").value;
    return list.includes(input);
}