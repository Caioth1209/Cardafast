<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dados do cliente</title>
<script type="text/javascript" src="js/cliente.js"></script> 
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/dados.css"> 
</head>
<body>

	<%

		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
	%>
	
	<div class="container">
	
		<nav>
			<form>
				<ul>
					<li><input type="submit" value="Voltar" formaction="SvClienteMenu" formmethod="post"/></li>
				</ul>
			</form>
		</nav>
	
		<section>
	
			<form class="card">

					<div class="card-top">
						<h3>Meus dados</h3>
					</div>
					
					<div>
						<input type="hidden" name="id" required="required" value = '<%= c == null?"": c.getId() %>' />
					</div> <br>
					
					<div class="card-group">
						<label>Nome completo: </label>							
						<input required="required" type="text" name="nome" value = '<%= c == null?"": c.getNome() %>' onchange="validaNomeCliente(this)" maxlength="40"/>
						<p style="display: none;" id="msgNome"></p>
					</div><br>
						
					<div class="card-group">
						<label>CPF: </label>
						<input required="required" type="text" name="cpf" value = '<%= c == null?"": c.getCpf() %>' onchange="validaCpf(this)" maxlength="11"/> 
						<p style="display: none;" id="msgCpf"></p>
					</div><br>
						
					<div class="card-group">
						<label>Endereço: </label>
                   		<textarea rows="3" cols="50" name="endereco" required="required" maxlength="100" onchange="validaEndereco(this)" ><%= c == null?"": c.getEndereco() %></textarea>
                   		<p  id="msgEnd" style="display: none;"></p>
					</div><br>
						
					<div class="card-group">
						<label>Login: </label>
						<input required="required" type="text" name="login" value = '<%= c == null?"": c.getLogin() %>' onchange="validaLogin(this)" maxlength="20"/> 
						<p style="display: none;" id="msgLog"></p>
					</div><br>
						
					<div class="card-group">
						<label>Senha: </label>
						<input required="required" type="password" id="primPass" name="senha" value = '<%= c == null?"": c.getSenha() %>' onchange="validaSenha(this)" maxlength="12"/> 
						<p style="display: none;" id="msgPass"></p>
					</div><br>
					
					<div class="card-group">
	                    <label>Digite a senha novamente: </label>
	                    <input required="required" type="password" id="secPass" name="senhaDois" onchange="validaSenhaDois(this)" maxlength="12"/> 
	                    <p id="msgSecPass" style="display: none;"></p>
	               	 </div><br>		
					
					<input type="hidden" name="tipo" value="cliente">
					
					<div class="card-group">	
					    <p id="msgErro"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
				
					    <p id="msgSucess"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>

                        <input type='submit' id="botao"  value="Confirmar edição" formaction="SvClienteEditar" formmethod="post">
					
                    </div> <br>
			</form>
	
		</section>
		
		<footer >
            <div>&copy; 2021 - Cardafast - Todos os
                Direitos Reservados</div>
        </footer>

	</div>	

</body>

</html>