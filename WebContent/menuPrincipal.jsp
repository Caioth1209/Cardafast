<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu principal</title>
<link rel="stylesheet" href="css/menuPrincipal.css">
<link rel="stylesheet" href="css/default.css">
</head>
<body>


	<%
		Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogado");
	
	%>
	
	<div class="container">
	
		<%
		
			switch(u.getTipo()){
			
				case "cliente":
					
					Cliente c;
					
					if(request.getSession().getAttribute("cliente") == null){
						
						c = new Cliente(u.getId());
						
						c = c.pegarDados();
						
						request.getSession().setAttribute("cliente", c);
						
					} else {
						c = (Cliente) request.getSession().getAttribute("cliente");
					}
					
			
					int totalPedidos = 0;
					int totalPendente = 0;
					int totalEmPreparo = 0;
					int totalCaminho = 0;
					int totalCancelados = 0;
					int totalEntregue = 0;
					int totalAvaliacoes = c.getListaAvaliacoesPedidos().size();
					int totalNaoAvaliados = 0;
					
					if(!c.getListaPedidos().isEmpty()){
						
						for(int i = 0; i < c.getListaPedidos().size(); i++){
							
							switch(c.getListaPedidos().get(i).getStatus()){
								
								case "Pendente":{
									totalPendente++;
									break;
								}
								
								case "Em Preparo":{
									totalEmPreparo++;
									break;
								}
								
								case "A Caminho":{
									totalCaminho++;
									break;
								}
								
								case "Entregue":{
									totalEntregue++;
									break;
								}
								
								case "Cancelado":{
									totalCancelados++;
									break;
								}
								
							}
												
							if(c.getListaPedidos().get(i).getStatus().equals("Entregue") && c.getListaPedidos().get(i).getAvaliacaoPedido() == null){
								totalNaoAvaliados++;
							}
							
							if(totalCaminho > 0){
								request.setAttribute("msgSucess", "Seu pedido está a caminho. Fique atento(a) a portaria!");
							}
							
							if(totalEmPreparo > 0){
								request.setAttribute("msgSucess", "Seu pedido foi aceito e já está em preparo!");
							}
												
							totalPedidos++;
						}
						
					} 
				
		%>
	
		<nav>
			<form>
				<ul>
					<li><input type="submit" value="Meus dados" formaction="SvClienteDados" formmethod="post"/></li>
					
					<li><input type="submit" value="Fazer pedido" formaction="SvPedidoTela" formmethod="post"/></li>
					
					<li><input type="submit" value="Meus pedidos" formaction="SvClientePedidos" formmethod="post"/></li>
					
					<li><input type="submit" value="Minhas avaliações" formaction="SvClienteAvaliacoes" formmethod="post"/></li>
					
					<li><a href="login.jsp">Voltar</a></li>
				</ul>
			</form>
		</nav>
		
		<section>
			<div class="bemvindo">
				<h1>Bem vindo, <%= c.getNome() %>!</h1>

				<p>Agora você pode ver suas informações e fazer seus pedidos no seu restaurante favorito!</p>
				
				<div id="mensagens" style="display: <%= request.getAttribute("msgErro") == null && request.getAttribute("msgSucess") == null ? "none" : "block" %> ">
			
					<p id="msgErro"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
				
					<p id="msgSucess"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>
				
				</div>
			</div>
			
			<div class="informacoes">
				<div class="resumo">
					<h2>Minha conta</h2>
					
					<hr id="hr">
					
					<ul>
						<li>Total de Pedidos: <%= totalPedidos %></li>
						<li>Pendentes: <%= totalPendente %></li>
						<li>Em preparo: <%= totalEmPreparo %></li>
						<li>A caminho: <%= totalCaminho %></li>
						<li>Recebidos: <%= totalEntregue %> </li>
						<li>Cancelados: <%= totalCancelados %></li>
						<li>Não avaliados: <%= totalNaoAvaliados %>
						<li>Avaliados: <%= totalAvaliacoes %> </li>
					</ul>
				</div>
			</div>
			
		</section>
		
		<%
				break;
		
				case "dono":
					
					DonoRestaurante dr;
					
					
					if(request.getSession().getAttribute("dono") == null){
						
						dr = new DonoRestaurante(u.getId());
						
						dr = dr.pegarDados();
						
						request.getSession().setAttribute("dono", dr);
						
					} else {
						dr = (DonoRestaurante) request.getSession().getAttribute("dono");
					}
					
					totalPedidos = 0;
					totalPendente = 0;
					totalEmPreparo = 0;
					totalCaminho = 0;
					totalCancelados = 0;
					totalEntregue = 0;
					totalAvaliacoes = dr.getRestaurante().getListaAvaliacoesPedidos().size();
					totalNaoAvaliados = 0;
					
					if(!dr.getRestaurante().getListaPedidos().isEmpty()){
						
						for(int i = 0; i < dr.getRestaurante().getListaPedidos().size(); i++){
							
							switch(dr.getRestaurante().getListaPedidos().get(i).getStatus()){
							
								case "Pendente":{
									totalPendente++;
									break;
								}
								
								case "Em Preparo":{
									totalEmPreparo++;
									break;
								}
								
								case "A Caminho":{
									totalCaminho++;
									break;
								}
								
								case "Entregue":{
									totalEntregue++;
									break;
								}
								
								case "Cancelado":{
									totalCancelados++;
									break;
								}
							
							}
											
							if(dr.getRestaurante().getListaPedidos().get(i).getStatus().equals("Entregue") && dr.getRestaurante().getListaPedidos().get(i).getAvaliacaoPedido() == null){
								totalNaoAvaliados++;
							}
							
							if(totalPendente > 0){
								request.setAttribute("msgSucess", "Você recebeu pedidos!!!");
							}
											
							totalPedidos++;
						}
						
					} 
					
					DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
			    	decimalFormat.setRoundingMode(RoundingMode.DOWN);
			    	  	
					String faturamento = decimalFormat.format(dr.getRestaurante().getFaturamento());
					
					dr.getRestaurante().listarPedidos1d();
					
					String faturamento1D = decimalFormat.format(dr.getRestaurante().getFaturamento1d());
					
					dr.getRestaurante().listarPedidos15d();
					
					String faturamento15D = decimalFormat.format(dr.getRestaurante().getFaturamento15d());
					
					dr.getRestaurante().listarPedidos30d();
					
					String faturamento30D = decimalFormat.format(dr.getRestaurante().getFaturamento30d());
					
		%>
		
		<nav>
			<form>
				<ul>
					<li><input type="submit" value="Meus dados" formaction="SvDonoRestDados" formmethod="post"/></li>
					
					<li><input type="submit" value="Dados do meu restaurante" formaction="SvRestauranteDados" formmethod="post"/></li>
					
					<li><input type="submit" value="Administrar produtos" formaction="SvProdutoMenu" formmethod="post"/></li>
					
					<li><input type="submit" value="Ver pedidos" formaction="SvRestaurantePedidos" formmethod="post"/></li>
					
					<li><input type="submit" value="Avaliações" formaction="SvRestauranteAvaliacoes" formmethod="post"/></li>
					
					<li><a href="login.jsp">Voltar</a></li>
				</ul>
			</form>
		</nav>
		
		<section>
		
			<div class="bemvindo">
				<h1>Bem vindo, <%= dr.getNome() %>!</h1>

				<p>Agora você pode ver as suas informações e a do seu restaurante, cadastrar seus produtos,
				 receber pedidos e faturar!</p>
				 
				 <div id="mensagens" style="display: <%= request.getAttribute("msgErro") == null && request.getAttribute("msgSucess") == null ? "none" : "block" %> ">
			
					<p id="msgErro"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
					
					<p id="msgSucess"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>
				
				</div>
			</div>
			
			<div class="informacoes">
				<div class="resumo">
					<h2>Minha conta</h2>
					
					<hr id="hr">
					
					<ul>
						<li>Total de Pedidos: <%= totalPedidos %></li>
						<li>Pendentes: <%= totalPendente %></li>
						<li>Em preparo: <%= totalEmPreparo %></li>
						<li>A caminho: <%= totalCaminho %></li>
						<li>Entregues: <%= totalEntregue %> </li>
						<li>Cancelados: <%= totalCancelados %></li>
						<li>Não avaliados: <%= totalNaoAvaliados %>
						<li>Avaliados: <%= totalAvaliacoes %> </li>
						<li>Faturamento total: R$ <%= faturamento %></li>
						<li>Faturamento hoje: R$ <%= faturamento1D %></li> 
						<li>Faturamento últimos 15 dias: R$ <%= faturamento15D %></li> 
						<li>Faturamento últimos 30 dias: R$ <%= faturamento30D %></li> 
					</ul>
				</div>
			</div>
			
		
		</section>
		
		<%
				break;
		
			}
		%>
		
		<footer>
            <div>&copy; 2021 - Cardafast - Todos os
                Direitos Reservados</div>
        </footer>
	</div>
	
</body>

<script type="text/javascript" src="js/menuPrincipal.js"></script>

</html>