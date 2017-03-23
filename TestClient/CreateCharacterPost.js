window.addEventListener('load', function () {
    var button = document.querySelector('.submitButton');
    button.addEventListener('click', signIn);
});

function reqListener(sender) {

    var xhr = sender.srcElement;
    if (xhr.status === 200) {
       showError('OK '+  xhr.responseText);
    }
    else {
        showError('Unexpected error: ' + xhr.responseText);
    }
    // console.log(this);
}


function signIn() {
    	var character = new Object();	
    	character.name = "Newer";
	character.serverName = "DREAM";
	character.role  ="DD";
	character.classType  ="GUNSLINGER";
	character.level = 28;
	character.serverName = "DREAM";
	
	var primeTime = new Object();
	
	primeTime.day = "MONDAY";
	primeTime.timeZone = 2;
	primeTime.start = new Object();
	primeTime.end = new Object();
	primeTime.start.hour = 21;
	primeTime.start.minute = 30;
	primeTime.end.hour = 01;
	primeTime.end.minute = 45;
	
	var primeTime2 = new Object();
	
	primeTime2.day = "SUNDAY";
	primeTime2.timeZone = 2;
	primeTime2.start = new Object();
	primeTime2.end = new Object();
	primeTime2.start.hour = 21;
	primeTime2.start.minute = 30;
	primeTime2.end.hour = 01;
	primeTime2.end.minute = 45;

	
	character.primeTimes = [primeTime,primeTime2];

    //alert(userPassword+userLogin);
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load', reqListener);
    //xhr.onload = reqListener;

	var path2 = 'http://localhost:8080/createCharacter';
	var path3 = 'http://groupfinder.herokuapp.com/findParties2';
    xhr.open('POST', path2, true);
	

    var toSend = JSON.stringify(character);

    xhr.send(toSend);

}

function showError(errorText) {
    document.querySelector('.errorMessage').innerHTML = errorText;
    document.querySelector('.errorMessage').style.visibility = 'visible';
}