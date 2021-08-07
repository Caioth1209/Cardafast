function habilitaBotao(){
	
	var radio = document.querySelector('input[type="radio"]:checked');

	var botao = document.getElementById("botao");

	if (radio) {
		botao.hidden = false;
		botao.disabled = false;
	}
}

	setTimeout(function() {

		document.getElementById("msgSucess").innerHTML = "";

		document.getElementById("msgErro").innerHTML = "";
		
	}, 10000)

/**
 * 
 */
