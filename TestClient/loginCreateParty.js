window.addEventListener('load', function () {
    var button = document.querySelector('.submitButton');
    button.addEventListener('click', signIn);
});

function reqListener(sender) {

    var xhr = sender.srcElement;
    if (xhr.status === 200) {
       showError('OK');
    }
    else {
        showError('Unexpected error: ' + xhr.responseText);
    }
    // console.log(this);
}


function signIn() {
    var party = new Object();
	
    party.name = "Sisters";
    party.age = 21;
	//party.timeZone = 2;
	party.language = "RU";
	party.strongLanguage = true;
	party.serversGroup = null;
	party.serverName = "DREAM";
	party.voiceChat = true;
	party.chatListening = true;
	party.chatSpeaking = true;
	party.pvp = true;
	party.pve = true;
	party.chatType = "SKYPE";
	
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
	
	var slot1 = new Object();	
	slot1.name = "Slot 1";
	slot1.role = "DD";
	slot1.classType = "VANGUARD";
	slot1.sex = "MALE";
	
	var slot2 = new Object();
	slot2.name = "Slot 2";
	slot2.role = "HEALER";
	slot2.classType = "VANGUARD";
	slot2.sex = "MALE";

	var slot3 = new Object();
	slot3.name = "Slot 3";
	slot3.role = "Tank";
	slot3.classType = "Knight";
	slot3.sex = "Male";
	
	party.slots = [slot1,slot2];
	
	party.primeTimes = [primeTime,primeTime2];
	
    //alert(userPassword+userLogin);
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load', reqListener);
    //xhr.onload = reqListener;
	var path1 = 'http://groupfinder.herokuapp.com/createParty';
	var path2 = 'http://localhost:8080/createParty';
    xhr.open('POST', path2, true);
	

    var toSend = JSON.stringify(party);

    xhr.send(toSend);

}

function showError(errorText) {
    document.querySelector('.errorMessage').innerHTML = errorText;
    document.querySelector('.errorMessage').style.visibility = 'visible';
}