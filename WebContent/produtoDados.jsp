<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="model.entidade.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meus produtos</title>
<script type="text/javascript" src="js/produto.js"></script>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/produtoDados.css">
</head>
<body>

	<%

		DonoRestaurante dr = (DonoRestaurante) request.getSession().getAttribute("dono");

		Produto p = (Produto) request.getSession().getAttribute("produtoDados");
		
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    	decimalFormat.setRoundingMode(RoundingMode.DOWN);
    	  	
		String preco = decimalFormat.format(p.getPreco());
		
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
		

			<form class="card">			
			
                <div class="card-top">
                    <h3>Editar informações do produto</h3>
                </div>

				<input type="hidden" name="id" value = '<%= p == null?"": p.getId()%>' />
											
				<div class="card-group">
					<label>Nome do produto: </label>							
					<input required="required" type="text" name="nome" value = '<%= p == null?"": p.getNome() %>' onchange="validaNomeProduto(this)" maxlength="30"/>
					<p id="msgNome"></p>
				</div>
						
				<div class="card-group">
					<label>Descrição: </label>
					<textarea rows="5" cols="50" name="descricao" onchange="validaDesc(this)" required="required" maxlength="250" ><%= p == null?"": p.getDescricao() %></textarea>
					<p id="msgDesc"></p>
				</div>
						
				<div class="card-group">
					<label>Preço: </label>
					<input required="required" type="text" name="preco" value = '<%= p == null?"": preco %>' onchange="validaPreco(this)"/>
					<p id="msgPreco"></p>
				</div>
					
				<input type="hidden" name="ativo" value = '<%= p == null?"": p.isAtivo() %>'/>
	
				<div class="card-group">
				
                    <p id="msgErro"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
				
				    <p id="msgSucess"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>

					<input type="submit" value="Editar" formaction="SvProdutoEditar" formmethod="post"/>
					
					<input type="submit" style="background-color: <%= p.isAtivo() ? "red" : "#28A745" %>" value="<%= p.isAtivo() ? "Desativar" : "Ativar" %>" formaction="<%= p.isAtivo() ? "SvProdutoDesativar" : "SvProdutoAtivar" %>" formmethod="post"/>
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