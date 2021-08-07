/**
 * 
 */


	
	function habilitaBotao(){
	
		const select = document.getElementsByClassName("nota");
		
		var botao = document.getElementById("botao");
		
		var conta = 0;
	
		for (var i = 0; i < select.length; i++) {
  			
  			if(select[i].value == 0){
  				conta++;
  			}
  			
		} 

		if (conta == 0) {
			botao.style.backgroundColor = "#28A745";
			botao.disabled = false;
		} else {
			botao.disabled = true;
			botao.style.backgroundColor = "red";
		}
	}
	