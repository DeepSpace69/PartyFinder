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
    	character.name = "Candy";
	character.serverName = "Dream";
	character.role  ="DD";
	character.classType  ="Gunslinger";
	character.level = 28;
	character.serverName = "Dream";
	
	var primeTime = new Object();
	
	primeTime.day = "Monday";
	primeTime.timeZone = 2;
	primeTime.start = new Object();
	primeTime.end = new Object();
	primeTime.start.hour = 21;
	primeTime.start.minute = 30;
	primeTime.end.hour = 01;
	primeTime.end.minute = 45;
	
	var primeTime2 = new Object();
	
	primeTime2.day = "Sunday";
	primeTime2.timeZone = 2;
	primeTime2.start = new Object();
	primeTime2.end = new Object();
	primeTime2.start.hour = 21;
	primeTime2.start.minute = 30;
	primeTime2.end.hour = 01;
	primeTime2.end.minute = 45;

	
	character.primeTimes = [primeTime,primeTime2];
	
	var fkUser = 2;

    //alert(userPassword+userLogin);
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load', reqListener);
    //xhr.onload = reqListener;

	var path2 = 'http://localhost:8080/getCharacters';
	var path3 = 'http://groupfinder.herokuapp.com/findParties2';
    xhr.open('POST', path2, true);
	

    var toSend = JSON.stringify(fkUser);

    xhr.send(toSend);

}

function showError(errorText) {
    document.querySelector('.errorMessage').innerHTML = errorText;
    document.querySelector('.errorMessage').style.visibility = 'visible';
}