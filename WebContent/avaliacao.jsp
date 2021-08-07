<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Avaliação do pedido</title>
<script type="text/javascript" src="js/avaliacao.js"></script>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/avaliacao.css">
</head>
<body>

	<%

		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
	
		Pedido p = (Pedido) request.getSession().getAttribute("pedidoAvaliado");
	
	%>

	<div class="container">
	
		<nav>
			<ul>
				<li><a href="clientePedidos.jsp">Voltar</a></li>
			</ul>
		</nav>
				
		<section>
		
			<form class="card">
				
                <div class="card-top">
                    <h3>Avaliação do pedido</h3>
                </div>
						
				<div class="card-group">
					<label>Nota: </label>
				
					<select class="nota" name="nota" onchange="habilitaBotao()">
						<option value="0" selected="selected">ESCOLHA</option>
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
					</select>
				</div>
						
				<div class="card-group">
					<label>Descreva sua experiência (opcional): </label>
					<textarea rows="5" cols="50" name="descricao" maxlength="400"></textarea>
				</div>
				
			    <div class="card-group">
				    <input type="submit" id="botao" value="Enviar" disabled="disabled" formaction="SvAvaliaPedido" formmethod="post">
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