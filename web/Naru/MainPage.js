window.addEventListener('load', function()
{
    var response = localStorage.getItem('auth');
    var user = JSON.parse(response);

    document.querySelector('.name').innerHTML=user.name;
    //document.querySelector('.login').innerHTML=user.login;
    //document.querySelector('.pass').innerHTML=user.password;
});


function hRequestListener(sender)
{
    var answer = sender.srcElement;
    if (answer.status === 200) {
        var partiesList=JSON.parse(answer.responseText);
        var divText='';
        for(var i=0; i<partiesList.length; i++) {
            var partyName = partiesList[i].name;
            var partyAge = partiesList[i].memberAge;
            var partyTime='';
            for(j=0;j<partiesList[i].primeTimes.length; j++)
            {
                var  startTime = partiesList[i].primeTimes[j].start;
                var endTime = partiesList[i].primeTimes[j].end;
                var day = partiesList[i].primeTimes[j].day;
                partyTime= partyTime+makeTimeString(startTime, endTime, day);
            }
            divText = divText+makePartyDiv(partyName, partyAge, partyTime);
            document.querySelector('.listOfParties').innerHTML = divText;
        }
    }
    else{
       alert('reqest error='+answer.status);
    }
}

function showPartyList(){
    var hRequest = new XMLHttpRequest();
    hRequest.addEventListener('load', hRequestListener);
    hRequest.open('GET', 'https://groupfinder.herokuapp.com/findParties', true);
    hRequest.send();
}

function makePartyDiv(incomingName, incomingAge, incomingTime)
{
var result='<div class="oneParty">Name:'+incomingName+'<br>Age:'+incomingAge+'<br>Prime Times:'+incomingTime+'</div><br>';
    return result;
}

function makeTimeString(startTime, endTime, day)
{
    var correctedStartMinute=startTime.minute;
    var correctedEndMinute=endTime.minute;
    if(startTime.minute<10)
    {
        correctedStartMinute='0'+startTime.minute;
    }
    if(endTime.minute<1)
    {
        correctedEndMinute='0'+endTime.minute;
    }
    var result='<br>'+day+': from '+startTime.hour+':'+correctedStartMinute+' to '+endTime.hour+':'+correctedEndMinute;
    return result;
}

function showAuth()
{
    var response = localStorage.getItem('auth');
    var user = JSON.parse(response);
alert('Login='+user.login+'   Password='+user.password);
}

function createNewParty()
{
    location.href = 'CreateParty.html';
}