var charnumber;
window.addEventListener('load', function()
{
    charnumber=1;
});

function selectCheckbox(incomingCheckbox)
{
if(incomingCheckbox.checked)
{
    $(incomingCheckbox).parent().css("background-color", "cornflowerblue");
}
    else
{
    $(incomingCheckbox).parent().css("background-color", "gainsboro");
}
}

function showLinkButton(incomingCheckbox)
{
    var button = $(incomingCheckbox).parent().prev();
    if(incomingCheckbox.checked)
    {
        $(button).css("visibility", "visible");
    }
    else
    {
        $(button).css("visibility", "hidden");
    }
}

function createNewChar()
{
   var currentChars = document.querySelector('.charListCell').innerHTML;

charnumber=charnumber+1;
var newChar = '<br><div class="charListDiv"><textblock class="charNumber">'+charnumber+'</textblock><div class="charDiv"><select class="charSelector"><option>?</option>'+
'<option>?</option><option>?</option></select><select class="charClassSelector"><option>Hunter</option>'+
'<option>Mage</option><option>Paladin</option></select><button class="charLinkButton">LinkChar</button>'+
'<label class="existLabel">Exist<input type="checkbox" class="existCheckbox" onclick="showLinkButton(this)"></label></div></div>'

    document.querySelector('.charListCell').innerHTML=currentChars+newChar;
}

