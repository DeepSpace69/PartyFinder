window.addEventListener('load', function () {
    var button = document.querySelector('.submitButton');
    button.addEventListener('click', signIn);
});

function reqListener(sender) {

    var xhr = sender.srcElement;
    if (xhr.status === 200) {
        localStorage.setItem('auth', xhr.responseText);
        location.href = 'MainPage.html';
    }

    else if (xhr.status === 401) {
        showError('bad login/pass');
    }
    else {
        showError('Unexpected error: ' + xhr.responseText);
    }
    // console.log(this);
}


function signIn() {
    var party = new Object();

    party.name = "Dragons";
    party.age = 21;
	party.timeZone = 2;
	party.language = "ru";
	party.strongLanguage = true;
	party.groupServer = "TheFirst";
	party.serverName = "Dream";
	party.voiceChat = true;
	party.chatListening = true;
	party.chatSpeaking = true;
	party.pvp = true;
	party.pve = true;
	party.chatType = "Skype";

    //alert(userPassword+userLogin);
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load', reqListener);
    //xhr.onload = reqListener;
    xhr.open('POST', 'http://localhost:8080/createParty', true);

    var toSend = JSON.stringify(party);

    xhr.send(toSend);

}

function showError(errorText) {
    document.querySelector('.errorMessage').innerHTML = errorText;
    document.querySelector('.errorMessage').style.visibility = 'visible';
}

function testFunction() {
    location.href = 'MainPage.html';}