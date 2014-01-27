
function controlla() {
var f = document.Mioform;
if (f.password.value != f.pass2.value) {
alert("Le due Password non sono uguali.");
return false;
}else{return true;}
}

