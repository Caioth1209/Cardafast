<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Avaliações</title>
<script type="text/javascript" src="js/donoRest.js"></script>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/restauranteAvaliacoes.css">
</head>
<body>

	<%
	
		DonoRestaurante d = (DonoRestaurante) request.getSession().getAttribute("dono");	

	%>

	<div class="container">
	
		<nav>
			<form>
				<ul>
					<li><input type="submit" value="Voltar" formaction="SvDonoRestMenu" formmethod="post"/></li>
				</ul>
			</form>
		</nav>
	
		<section>
		
			<div class="card">
				<form id="filt">
					<label>Filtro de nota: </label>
				
					<select id="filtro" name="filtro">
						<option value="Todos" <%= d.getRestaurante().getFiltroAvaliacao().equals("Todos") ? "selected" : ""%>>Todos</option>
						<option value="1" <%= d.getRestaurante().getFiltroAvaliacao().equals("1") ? "selected" : ""%>>1</option>
						<option value="2" <%= d.getRestaurante().getFiltroAvaliacao().equals("2") ? "selected" : ""%>>2</option>
						<option value="3" <%= d.getRestaurante().getFiltroAvaliacao().equals("3") ? "selected" : ""%>>3</option>
						<option value="4" <%= d.getRestaurante().getFiltroAvaliacao().equals("4") ? "selected" : ""%>>4</option>
						<option value="5" <%= d.getRestaurante().getFiltroAvaliacao().equals("5") ? "selected" : ""%>>5</option>
					</select>
	
					<input type="submit" id="botaoRelatorio" value="Filtrar" formaction="SvRestauranteLocalizaAvaliacao" formmethod="post">
				</form>
			
				<div class="rolagem">
					<table cellspacing="15">
							
						<thead>
									
							<tr>
								
								<th>Pedido</th>		
											
								<th>Cliente</th>
									
								<th>Nota</th>
								
								<th>Descrição</th>
								
								<th>Data</th>									
										
							</tr>
									
						</thead>
						
						<tbody>
						
						<%
						if(d.getRestaurante().getListaAvaliacoesPedidos().isEmpty() || d.getRestaurante().getListaAvaliacoesPedidos() == null){
							
							%>
								<tr>
									<td colspan="5">Nenhuma avaliação encontrada.</td>
								</tr>
								
							<%
								
						} else {
							
						for(int i = 0; i < d.getRestaurante().getListaAvaliacoesPedidos().size(); i++){
							
							SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
							
							String data = ft.format(d.getRestaurante().getListaAvaliacoesPedidos().get(i).getData());
					%>				
						 	<tr>
								
								<td class="tam"><%= d.getRestaurante().getListaAvaliacoesPedidos().get(i).getPedido().getId() %></td>
								
								<td class="tam"><%= d.getRestaurante().getListaAvaliacoesPedidos().get(i).getCliente().getNome() %></td>	
									
								<td class="tam"><%= d.getRestaurante().getListaAvaliacoesPedidos().get(i).getEstrelas() %></td>
									
								<td class="esp"><%= d.getRestaurante().getListaAvaliacoesPedidos().get(i).getDescricao() %></td>
								
								<td class="tam"><%= data %></td>	
									
							</tr>				
					<%
						}
					}
						
					%>
								
						</tbody>
					
					</table>
				</div>
				
				<p id="msgErro" style="color: red;"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
				
				<p id="msgSucess" style="color: green;"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>
			</div>
			
		</section>
		
		<footer >
            <div>&copy; 2021 - Cardafast - Todos os
                Direitos Reservados</div>
        </footer>

	</div>
	


</body>
</html>