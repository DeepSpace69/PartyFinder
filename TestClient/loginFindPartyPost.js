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
	
    party.name = "Strawbyrries";
    party.age = 21;
	//party.timeZone = 2;
	party.language = "ru";
	party.strongLanguage = true;
	party.serversGroup = null;
	party.serverName = "Dream";
	party.voiceChat = true;
	party.chatListening = true;
	party.chatSpeaking = true;
	party.pvp = "io";
	party.pve = true;
	party.chatType = "Skype";
	
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
	
	var slot1 = new Object();	
	slot1.name = "Slot 1";
	slot1.role = "DD";
	slot1.classType = "Vanguard";
	slot1.sex = "Male";
	
	var slot2 = new Object();
	slot2.name = "Slot 2";
	slot2.role = "Healer";
	slot2.classType = "Vanguard";
	slot2.sex = "Male";

	var slot3 = new Object();
	slot3.name = "Slot 3";
	slot3.role = "Tank";
	slot3.classType = "Knight";
	slot3.sex = "Male";
	
	party.slots = [slot1,slot2];
	
	party.primeTimes = [primeTime,primeTime2];
	
	var filter1 = new Object();
	filter1.key = "strongLanguage";
	filter1.value = true;
	
	var filter2 = new Object();
	filter2.key = "language";
	filter2.value = "RU";
	
	var filter3 = new Object();
	filter3.key = "chatSpeaking";
	filter3.value = false;

	var filter4 = new Object();
	filter4.key = "age";
	filter4.value = 18;

	var filter5 = new Object();
	filter5.key = "pve";
	filter5.value = true;
	
	var filter6 = new Object();
	filter6.key = "pve";
	filter6.value = null;

	var list = [filter6];
	
    //alert(userPassword+userLogin);
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load', reqListener);
    //xhr.onload = reqListener;
	var path1 = 'http://groupfinder.herokuapp.com/createParty';
	var path2 = 'http://localhost:8080/findParties';
	var path3 = 'http://groupfinder.herokuapp.com/findParties2';
    xhr.open('POST', path2, true);
	

    var toSend = JSON.stringify(list);

    xhr.send(toSend);

}

function showError(errorText) {
    document.querySelector('.errorMessage').innerHTML = errorText;
    document.querySelector('.errorMessage').style.visibility = 'visible';
}