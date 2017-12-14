window.onload = function() {
  imprimirValor();
}

function imprimirValor(){
  var select = document.getElementById("cuentas");
  var options=document.getElementsByTagName("option");
  console.log(select.value);
 // console.log(options[select.value-1].innerHTML)
}

function add_selectd_multiple(){
    /* Para obtener el texto */
var combo = document.getElementById("cuentas");
var selected = combo.options[combo.selectedIndex].text;
//alert(selected);
}

function cargarFormula()
{
	var combo = document.getElementById("cuentas");
	var selected = combo.options[combo.selectedIndex].text;
	if(selected=='Seleccionar')
	{
		document.getElementById("listarFormula").innerHTML+='';
	}
	else
	{
		document.getElementById("listarFormula").innerHTML+=selected;
	}
	
}
function cargarOperacion(valorDeSpan)
{
	document.getElementById("listarFormula").innerHTML+=valorDeSpan;
}

function eliminarFormula()
{
	document.getElementById("listarFormula").innerHTML='';
}

function cargarP(){
	//document.getElementById("hdnForm").innerHTML+=document.getElementById("listarFormula").value;
	var parrafos = document.getElementById("listarFormula");
	//alert(parrafos[0]);
	//var selected = combo.text;
	//alert(document.getElementById("listarFormula").value);
}
function imprSelec(historial){
  var ficha=document.getElementById(historial);
  document.getElementById("hdnForm").value=ficha.innerHTML;
 // alert(ficha.innerHTML);
}
function imprSelec2(historial){
  var ficha=document.getElementById(historial);
  document.getElementById("hdnFormAlta").value=ficha.innerHTML;
  //alert(ficha.innerHTML);
}

