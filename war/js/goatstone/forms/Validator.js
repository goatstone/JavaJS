/*
Form Validator
*/

//print(3000);
function Validator(){
    this.name = "goatstone.forms.Validator";
}

Validator.prototype.max = 200;
Validator.prototype.min = 2;

Validator.prototype.isEmail = function (possibleEmail){
    return /.+@.+/.test(possibleEmail);
}
Validator.prototype.isMin = function (num){
    return true;
}
Validator.prototype.isUnderMax = function (num){
    return (num<this.max);
}
Validator.prototype.isOverMin = function (num){
    return (num>this.min);
}

var val = new Validator();

