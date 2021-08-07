<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/login.css">
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>

	<%
		if(request.getSession().getAttribute("cliente") != null){
			request.getSession().setAttribute("cliente", null);
		} else {
			if(request.getSession().getAttribute("dono") != null){
				request.getSession().setAttribute("dono", null);
			}
		}
	
	%>

	<div class="container">
	
		<nav>
			<ul>
				<li>
					<a href="index.html">Página Inicial</a>
				</li>
			</ul>
		</nav>
	
		<section>
		
			<form class="card">
		
                <div class="card-top">
                    <h1>Faça seu login aqui</h1>
                </div>
				
				<div class="card-group">
					<label>Login</label>
					<input type="text" required="required" name="login" maxlength="20" />
				</div> <br>
				
				<div class="card-group">
					<label>Senha</label>
					<input type="password" required="required" name="senha" maxlength="12"/>
				</div> <br>
				
				<div class="card-group">
						
					<p id="msgSucess" style="color: green;"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>
					
					<p id="msgErro" style="color: red;" > <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
					
					<input type="submit" id="btSubmit" value="Entrar" formaction="SvLogin" formmethod="post">
										
				</div> <br>
				
				<div class="card-group links">
                    <div class="card-top">
                        <h1>Não tenho cadastro</h1>
                    </div>
					<p>Sou cliente: <a href="clienteCadastra.jsp">criar uma conta</a></p>
					<p>Sou dono de restaurante: <a href="donoRestCadastra.jsp">criar uma conta</a></p>
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