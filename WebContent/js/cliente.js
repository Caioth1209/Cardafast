/**
 * 
 */

	
	function validaNomeCliente(c){
	
		var idMsg = document.getElementById("msgNome");
		
		
		var nome = c.value.replace(" ", "");

		var regex = /[0-9]/;
		
		if(regex.test(nome)){
		
			c.value = "";
	
			idMsg.style.display = "block";	
			idMsg.style.color = "red";
			idMsg.innerHTML = "Nome inválido!";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
		
			}, 10000)
			
		} else {
		
			if (c.value.trim().length < 5) {
			
				c.value = "";
		
				idMsg.style.display = "block";	
				idMsg.style.color = "red";
				idMsg.innerHTML = "Mín 5 caracteres!";
				
				setTimeout(function() {

					idMsg.innerHTML = "";
		
				}, 10000)
			
							
			} else {
			
				if(c.value.trim().split(" ").length < 2){
				
					c.value = "";
					
					idMsg.style.display = "block";	
					idMsg.style.color = "red";
					idMsg.innerHTML = "Digite seu nome completo!";
					
					setTimeout(function() {

						idMsg.innerHTML = "";
		
					}, 10000)
						
				} else {
				
					idMsg.style.display = "none";	
				
				}
			}

		}
			
	}
	

	function validaCpf(c){

		var idMsg = document.getElementById("msgCpf");

		if (c.value.trim().length != 11 || isNaN(c.value)) {
			c.value = "";

			idMsg.style.display = "block";	
			idMsg.style.color = "red";
			idMsg.innerHTML = "Coloque um cpf correto de 11 dígitos!";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
		
			}, 10000)
			
		} else {
			idMsg.style.display = "none";
		}	
	
	}

	function validaEndereco(c){
		
		var idMsg = document.getElementById("msgEnd");
	
		if (c.value.trim().length < 10) {
			
			c.value = "";
	
			idMsg.style.display = "block";	
			idMsg.style.color = "red";
			idMsg.innerHTML = "Endereço mín 10 caracteres!";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
		
			}, 10000)
			
		} else {
			idMsg.style.display = "none";
		}	
	
	}

	function validaLogin(c){
		
		var idMsg = document.getElementById("msgLog");
		
	
		if (c.value.trim().length < 5) {
	
			c.value = "";
	
			idMsg.style.display = "block";	
			idMsg.style.color = "red";
			idMsg.innerHTML = "Mín 5 caracteres!";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
		
			}, 10000)
			
		} else {
		
			if(c.value.trim().split(" ").length > 1){
			
				c.value= "";
				
				idMsg.style.display = "block";	
				idMsg.style.color = "red";
				idMsg.innerHTML = "Não pode conter espaços!";
				
				setTimeout(function() {

					idMsg.innerHTML = "";
		
				}, 10000)
					
			} else {
			
				idMsg.style.display = "none";	
			}
		}	
	
	}

	function validaSenha(c){
		
		var idMsg = document.getElementById("msgPass");
		
		var secPass = document.getElementById("secPass");
		
	
		if (c.value.trim().length < 5) {
	
			c.value = "";
	
			idMsg.style.display = "block";
			idMsg.style.color = "red";
			idMsg.innerHTML = "Mín 5 caracteres!";
			
			secPass.disabled = true;
			secPass.value = "";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
		
			}, 10000)
			
		} else {
		
			if(c.value.trim().split(" ").length > 1){
			
				c.value = "";
			
				idMsg.style.display = "block";	
				idMsg.style.color = "red";
				idMsg.innerHTML = "Não pode conter espaços!";
				
				secPass.disabled = true;
				secPass.value = "";
								
				setTimeout(function() {

					idMsg.innerHTML = "";
		
				}, 10000)
					
			} else {
				
				secPass.disabled = false;
				secPass.value = "";
				idMsg.style.display = "none";	
				document.getElementById("msgSecPass").style.display = "none";	
			}
		}	
	
	}
	
	function validaSenhaDois(c){
	
		var idMsg = document.getElementById("msgSecPass");
		
		var primPass = document.getElementById("primPass").value;
				
		if(c.value.trim() != primPass.trim()){
			
			c.value = "";
			
			idMsg.style.display = "block";
			idMsg.style.color = "red";
			idMsg.innerHTML = "Senhas não correspondem!";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
				idMsg.style.display = "none";
				
			}, 10000)
			
		} else {
		
			idMsg.style.display = "block";
			idMsg.style.color = "#10e542";
			idMsg.innerHTML = "Senhas correspondem!";
			
			setTimeout(function() {

				idMsg.innerHTML = "";
				idMsg.style.display = "none";
		
			}, 10000)
			
		}
		
	}
	
	function habilitaBotao(){
	
		const select = document.querySelector('#filtro');
		
		var botao = document.getElementById("botaoRelatorio");
	
		if (select.value == 0) {
			botao.disabled = true;
		} else {
			botao.disabled = false;
		}
	}
	

	setTimeout(function() {

		document.getElementById("msgSucess").innerHTML = "";

		document.getElementById("msgErro").innerHTML = "";
		
	}, 10000)
