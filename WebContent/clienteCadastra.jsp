<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de cliente</title>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/clienteCadastra.css">
<script type="text/javascript" src="js/cliente.js"></script>
</head>
<body>

    <%
		Cliente c = (Cliente) request.getAttribute("cliente");
	%>

	<div class="container">
	
		<nav>
			<ul>
				<li><a href="login.jsp">Voltar</a></li>
			</ul>
		</nav>
		
		<section>
		
			<form class="card">			
					
                <div class="card-top">
                    <h1>Digite seus dados</h1>
                </div>
						
				<div class="card-group">
                    <label>Nome completo: </label>							
                    <input required="required" type="text" name="nome" value = '<%= c == null?"": c.getNome() %>' onchange="validaNomeCliente(this)" maxlength="40"/>
                    <p id="msgNome" style="display: none;"></p>
                </div> <br>
                    
                <div class="card-group">
                    <label>CPF: </label>
                    <input required="required" type="text" name="cpf" value = '<%= c == null?"": c.getCpf() %>' onchange="validaCpf(this)" maxlength="11"/> 
                    <p  id="msgCpf" style="display: none;"></p>
                    
                </div><br>
                    
                <div class="card-group">
                    <label>Endereço: </label>
                    <textarea rows="3" cols="50" name="endereco" required="required" maxlength="100" onchange="validaEndereco(this)" ><%= c == null?"": c.getEndereco() %></textarea>
                    <p  id="msgEnd" style="display: none;"></p>
                </div><br>
                    
                <div class="card-group">
                    <label>Login: </label>
                    <input required="required" type="text" name="login" value = '<%= c == null?"": c.getLogin() %>' onchange="validaLogin(this)" maxlength="20"/> 
                    <p  id="msgLog" style="display: none;"></p>
                </div><br>
                    
                <div class="card-group">
                    <label>Senha: </label>
                    <input required="required" type="password" id="primPass" name="senha" value = '<%= c == null?"": c.getSenha() %>' onchange="validaSenha(this)" maxlength="12"/> 
                    <p  id="msgPass" style="display: none;"></p>
                </div><br>
                
                 <div class="card-group">
                    <label>Digite a senha novamente: </label>
                    <input required="required" type="password" id="secPass" disabled="disabled" name="senhaDois" onchange="validaSenhaDois(this)" maxlength="12"/> 
                    <p id="msgSecPass" style="display: none;"></p>
               	 </div><br>	

				
				<div class="card-group">
					<p id="msgErro" style="color: red;"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
					
					<p id="msgSucess" style="color: green;"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>
					
					<input type="submit" id="btSubmit" value="Cadastrar" formaction="SvClienteCadastra" formmethod="post"/>
				</div>
				
			</form>
			
		</section>
		
		<footer>
            <div>&copy; 2021 - Cardafast - Todos os
                Direitos Reservados</div>
        </footer>
		
	</div>

</body>

</html>