/**
 * 
 */

	
	function validaNomeProduto(c){
	
		var idMsg = document.getElementById("msgNome");
	
		if (c.value.trim().length < 5) {
			
			c.value = "";
	
			idMsg.style.display = "block";
			idMsg.style.color = "red";
			idMsg.innerHTML = "Nome do produto mín 5 caracteres!";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
		
			}, 10000)
						
		} else {
			idMsg.style.display = "none";
		}	
	}

	function validaDesc(c){

		var idMsg = document.getElementById("msgDesc");

		if (c.value.trim().length < 15) {
			c.value = "";

			idMsg.style.display = "block";
			idMsg.style.color = "red";
			idMsg.innerHTML = "Coloque uma descrição de no mínimo 15 caracteres!";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
		
			}, 10000)
			
		} else {
			idMsg.style.display = "none";
		}	
	
	}

	function validaPreco(c){
		
		var idMsg = document.getElementById("msgPreco");
		
		
		var preco = c.value.replace(",", ".");

		
		if (preco <= 0 || isNaN(preco)) {
			
			c.value = "";
			
			idMsg.style.display = "block";
			idMsg.style.color = "red";
			idMsg.innerHTML = "Preço inválido!";
					
			setTimeout(function() {
		
				idMsg.innerHTML = "";
				
			}, 10000)
				
		} else {
					
			idMsg.style.display = "none";
		}
	
	}
	


	setTimeout(function() {

		document.getElementById("msgSucess").innerHTML = "";

		document.getElementById("msgErro").innerHTML = "";
		
	}, 10000)
