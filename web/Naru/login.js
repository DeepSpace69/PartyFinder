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
    var user = new Object();

    user.login = document.querySelector('.loginInput').value;
    user.password = document.querySelector('.passwordInput').value;
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load', reqListener);
    //xhr.onload = reqListener;
    xhr.open('POST', 'http://groupfinder.herokuapp.com/auth', true);

    var toSend = JSON.stringify(user);

    xhr.send(toSend);

}

function showError(errorText) {
    document.querySelector('.errorMessage').innerHTML = errorText;
    document.querySelector('.errorMessage').style.visibility = 'visible';
}
