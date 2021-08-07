<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produtos</title>
<script type="text/javascript" src="js/produto.js"></script>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/produtoCadastra.css">
</head>
<body>

	<%

		DonoRestaurante dr = (DonoRestaurante) request.getSession().getAttribute("dono");
		
		Produto p = (Produto) request.getAttribute("produto");
	%>

	<div class="container">
	
		<nav>
			<form>
				<ul>
					<li><input type="submit" value="Voltar" formaction="SvProdutoMenu" formmethod="post"/></li>
				</ul>
			</form>
		</nav>
				
		<section>
		
			<!--  enctype="multipart/form-data" -->
			<form class="card">			
			
                <div class="card-top">
                    <h3>Digite as informações do produto</h3>
                </div>
						
				<div class="card-group">
					<label>Nome do produto: </label>							
					<input required="required" type="text" name="nome" value = '<%= p == null?"": p.getNome() %>' onchange="validaNomeProduto(this)" maxlength="60"/>
					<p id="msgNome"></p>
				</div>
						
				<div class="card-group">
					<label>Descrição: </label>
					<textarea rows="5" cols="50" name="descricao" required="required" maxlength="250" onchange="validaDesc(this)" ><%= p == null?"": p.getDescricao() %></textarea>
					<p id="msgDesc"></p>
				</div>
						
				<div class="card-group">
					<label>Preço: </label>
					<input required="required" type="text" name="preco" value = '<%= p == null?"": p.getPreco() %>' onchange="validaPreco(this)"/>
					<p id="msgPreco"></p>
				</div>
				
				
				<div class="card-group">

                    <p id="msgErro"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
				
				    <p id="msgSucess"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>

					<input type="submit" id="btSubmit" value="Cadastrar" formaction="SvProdutoCadastra" formmethod="post">
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