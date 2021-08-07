<%@page import="java.text.SimpleDateFormat"%>
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
<title>Meus pedidos</title>
<script type="text/javascript" src="js/cliente.js"></script>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/clientePedidos.css">
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
		
			<div class="card">
			
				<form id="filt">
					<label>Filtro: </label>		

                    <select id="filtro" name="filtro">
						<option value="Todos" <%= c.getFiltroPedido().equals("Todos") ? "selected" : ""%>>Todos</option>
						<option value="Pendente" <%= c.getFiltroPedido().equals("Pendente") ? "selected" : ""%>>Pendente</option>
						<option value="Em Preparo" <%= c.getFiltroPedido().equals("Em Preparo") ? "selected" : ""%>>Em Preparo</option>
						<option value="A Caminho" <%= c.getFiltroPedido().equals("A Caminho") ? "selected" : ""%>>A Caminho</option>
						<option value="Entregue" <%= c.getFiltroPedido().equals("Entregue") ? "selected" : ""%>>Entregue</option>
						<option value="Cancelado" <%= c.getFiltroPedido().equals("Cancelado") ? "selected" : ""%>>Cancelado</option>
					</select>
	
					<input type="submit" id="botaoRelatorio" value="Filtrar" formaction="SvClienteLocalizaPedido" formmethod="post">
					
					<p id="msgErro"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
				
					<p id="msgSucess"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>
				</form>
			
				<div class="rolagem">
				
					<table cellspacing="20">
								
						<thead>
									
							<tr>
								
								<th>Pedido</th>
								
								<th>Restaurante</th>
								
								<th>Produtos</th>
											
								<th>Data</th>
											
								<th>Hora</th>
								
								<th class="nq">Valor Total</th>
								
								<th class="nq">Forma de pagamento</th>
								
								<th>Status</th>
										
							</tr>
									
						</thead>
						
						<tbody>
	
						<%
						
						if(c.getListaPedidos().isEmpty() || c.getListaPedidos() == null){
							
							%>
								<tr>
									<td colspan="8">Nenhum pedido encontrado.</td>
								</tr>
								
							<%
								
						} else {
					
							for(int i = 0; i < c.getListaPedidos().size(); i++){
						%>				
								<tr>
									
									<td class="tam"><%=	c.getListaPedidos().get(i).getId() %></td>
										
									<td class="tamNome"><%=	c.getListaPedidos().get(i).getRestaurante().getNome() %></td>
										
									<td class="esp">
										<%
											for(int j = 0; j < c.getListaPedidos().get(i).getListaProdutos().size(); j++){
										%>
											<%= c.getListaPedidos().get(i).getListaProdutos().get(j).getNome() + "<br>" %>
										<%
											}
										%>
									</td>
									
									<%
			   							DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
			   							decimalFormat.setRoundingMode(RoundingMode.DOWN);
			   							    	  	
			   							String valorTotal = decimalFormat.format(c.getListaPedidos().get(i).getValorTotal());
			   							
			   							SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	   									
	   									String data = ft.format(c.getListaPedidos().get(i).getData());
		   							%>
										
									<td class="tam"><%= data %></td>
										
									<td class="tam"><%= c.getListaPedidos().get(i).getHora()%></td>
												
									<td class="esp tam nq">R$ <%= valorTotal %></td>
										
									<td class="nq tam"><%= c.getListaPedidos().get(i).getFormaPagamento() %></td>
										
									<td class="nq">
										<%= c.getListaPedidos().get(i).getStatus() %>
											
										<form>
											<input type="hidden" name="idPedido" value="<%=c.getListaPedidos().get(i).getId()%>">
	
											<input class="btAvalia" type="<%= c.getListaPedidos().get(i).getStatus().equals("Entregue") && c.getListaPedidos().get(i).getAvaliacaoPedido() == null ? "submit" : "hidden" %>" value="Avaliar meu pedido" formaction="SvAvaliacaoTela" formmethod="post">	 		 
											<input class="btCancela" type="<%= c.getListaPedidos().get(i).getStatus().equals("Em Preparo") || c.getListaPedidos().get(i).getStatus().equals("Pendente") ? "submit" : "hidden" %>" value="Cancelar" formaction="SvPedidoCancelar" formmethod="post">	 
										</form>
									</td>
								</tr>				
							<%
								
							}
						}
									
						%>
								
						</tbody>
				
					</table>
				</div>
			</div>
			
		</section>
		
		<footer >
            <div>&copy; 2021 - Cardafast - Todos os
                Direitos Reservados</div>
        </footer>

	</div>

</body>

</html>