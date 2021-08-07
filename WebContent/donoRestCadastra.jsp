<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de restaurante</title>
<script type="text/javascript" src="js/donoRest.js"></script>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/donoRestCadastra.css">
</head>
<body>

	<%
		DonoRestaurante dr = (DonoRestaurante) request.getAttribute("donoRest");
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
							<input type="text" name="nomeDono" value = '<%= dr == null?"": dr.getNome() %>' onchange="validaNomeDono(this)"  maxlength="40"/>
							<p id="msgNome" style="display: none;"></p>
						</div><br>
						
						<div class="card-group">
							<label>CPF: </label> 
							<input type="text" name="cpf" value = '<%= dr == null?"": dr.getCpf() %>' onchange="validaCpf(this)" maxlength="11"/>
							<p id="msgCpf" style="display: none;"></p>
						</div><br>
						
						<div class="card-group">
							<label>Login: </label>
							<input type="text" name="login" value = '<%= dr == null?"": dr.getLogin() %>' onchange="validaLogin(this)" maxlength="20"/>
							<p id="msgLog" style="display: none;"></p>
						</div><br>
						
						<div class="card-group">
							<label>Senha: </label>
							<input type="password" name="senha" id="primPass" value = '<%= dr == null?"": dr.getSenha() %>' onchange="validaSenha(this)" maxlength="12"/>
							<p id="msgPass" style="display: none;"></p>
						</div><br>
						
						<div class="card-group">
		                    <label>Digite a senha novamente: </label>
		                    <input required="required" type="password" id="secPass" disabled="disabled" name="senhaDois" onchange="validaSenhaDois(this)" maxlength="12"/> 
		                    <p id="msgSecPass" style="display: none;"></p>
		               	</div><br>
						
						<input type="hidden" name="tipo" value="admin"/>

						<div class="card-top">
                            <h1>Dados do restaurante</h1>
                        </div>
						
						<div class="card-group">
							<label>Nome do estabelecimento: </label>
							<input required="required" type="text" name="nomeRest" value = '<%= dr == null?"": dr.getRestaurante().getNome() %>' onchange="validaNomeRest(this)" maxlength="30"/>
							<p id="msgRest" style="display: none;"></p>
						</div><br>
						
						<div class="card-group">
							<label>CNPJ: </label>
							<input required="required" type="text" name="cnpj" value = '<%= dr == null?"": dr.getRestaurante().getCnpj() %>' onchange="validaCnpj(this)" maxlength="14"/>
							<p id="msgCnpj" style="display: none;"></p>
						</div><br>
						
						<div class="card-group">
							<label>Endereço: </label>
                            <textarea rows="3" cols="50" name="endereco" required="required" maxlength="100" onchange="validaEndereco(this)" ><%= dr == null?"": dr.getRestaurante().getEndereco() %></textarea>
							<p id="msgEnd" style="display: none;"></p>
						</div><br>
												
					
					<div class="card-group">
						<p id="msgErro" style="color: red;"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
					
						<p id="msgSucess" style="color: green;"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>
						
						<input type="submit" id="btSubmit" value="Cadastrar" formaction="SvDonoRestCadastra" formmethod="post"/>
					</div>
				
				</form>

			
		</section>
		
		<footer >
            <div>&copy; 2021 - Cardafast - Todos os
                Direitos Reservados</div>
        </footer>
		
	</div>

</body>

</html>