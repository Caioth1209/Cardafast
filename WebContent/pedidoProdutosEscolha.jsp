<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import = "model.entidade.*" %>
<%@ page import = "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Escolha de produtos</title>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/pedidoProdsEscolha.css">
</head>
<body>

	<%
	
		Pedido p = (Pedido) request.getSession().getAttribute("pedido");
		
		List<Produto> lista = (List<Produto>) request.getSession().getAttribute("listaProdutos");
		
		if(lista == null){
			lista = new ArrayList<Produto>();
		}	
	
		List<Produto> carrinho = (List<Produto>) request.getSession().getAttribute("carrinho");
	
		if(carrinho == null){
			carrinho = new ArrayList<Produto>();
		}
	
	%>
	
	<div class="container">
	
		<nav>
			<ul>
				<li><a href="pedidoRestauranteEscolha.jsp">Voltar</a></li>
			</ul>
		</nav>
	
		<section>
            
            <div id="restaurante">
                <h3><%= p.getRestaurante().getNome() %></h3>
            </div>
			
			<div class="carrinho">	

				<label>Carrinho: </label>	
				<select>
					<%
						double total = 0;
					
						if(carrinho.isEmpty()){
							out.println("<option> Nenhum produto no carrinho</option>");
						} else {
							for(int i = 0; i < carrinho.size(); i++){
								
								DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
							    decimalFormat.setRoundingMode(RoundingMode.DOWN);
							    	  	
								String preco = decimalFormat.format(carrinho.get(i).getPreco());				
								
								out.println("<option>" + carrinho.get(i).getNome() + " | R$ " + preco + "</option>");
								total += carrinho.get(i).getPreco();
							}
						}
					%>
					
				</select>

                <p id="msgErro"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
				
			    <p id="msgSucess"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>

				<%
			   		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
					decimalFormat.setRoundingMode(RoundingMode.DOWN);
			   							    	  	
			   		String valorTotal = decimalFormat.format(total);
				%>
				
				<p>Total em produtos: <span id="preco"><%= " R$ " + valorTotal %></span></p>

                <form>
					<input id="btProsseguir" type="<%= carrinho.isEmpty() ? "hidden" : "submit" %>" value="Prosseguir com o pedido"  formaction="SvPedidoProdutosFinal" formmethod="post">
				</form>
            </div> 
            
            <form class="procuraProduto">

				<div class="card-group">
					<label>Procurar produto: </label>
					<input placeholder="Nome do produto" type="text" name="nomeProcurado" />
				</div> <br>
			
				<div class="card-group">
				
					<input id="btLocaliza" type="submit" value="Localizar produto" formaction="SvPedidoLocalizarProduto" formmethod="post">
					
					<input id="btListar" type="submit" value="Listar todos" formaction="SvPedidoProdutosListarTodos" formmethod="post">
					
				</div>
				
				
			</form>
				
			<div class="produtos">
			
				<h4>Escolha os produtos que deseja pedir</h4>

                <hr class="hr"> 

				<%
					if(!lista.isEmpty()){
						for(int i = 0; i < lista.size(); i++){
												    	  	
					   		String preco = decimalFormat.format(lista.get(i).getPreco());
				%>
					<form>
						<div class="prod">
							<input type="hidden" name='produto' value='<%=lista.get(i).getId()%>' />

                                <label id="info"> 
                                	<span class="nomeProd"> <%=lista.get(i).getNome() %> </span>
                                	<br>
                                  <span class="precoProd">R$ <%= preco %></span>
                                 </label> 
			
                                <label class="descProd">- <%= lista.get(i).getDescricao() %></label>

							<input type="<%=  lista.get(i).isAtivo() ? "submit" : "button" %>" 
								id="<%=  lista.get(i).isAtivo() ? "btAdiciona" : "btIndisponivel" %>"
								 value="<%=  lista.get(i).isAtivo() ? "Adicionar" : "Indisponivel" %>"
							 formaction="<%=  lista.get(i).isAtivo() ? "SvPedidoAdicionaProduto" : "#" %>" 
							 formmethod="post">					
						</div>
					</form>
	
					<hr class="hr">
					
				<%
						}
					} else {
				%>
					<label id="nProd">Nenhum produto encontrado.</label>
				<%
					}
				%>
            <br>

            </div>
			
		</section>
		
		<footer>
            <div>&copy; 2021 - Cardafast - Todos os
                Direitos Reservados</div>
        </footer>
	</div>
	
</body>

<script type="text/javascript" src="js/pedido.js"></script>

</html>