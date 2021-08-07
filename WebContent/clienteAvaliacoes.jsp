<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entidade.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Minhas avaliações</title>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/clienteAvaliacoes.css">
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
					<label>Filtro de nota: </label>
				
					<select id="filtro" name="filtro">
						<option value="Todos" <%= c.getFiltroAvaliacao().equals("Todos") ? "selected" : ""%>>Todos</option>
						<option value="1" <%= c.getFiltroAvaliacao().equals("1") ? "selected" : ""%>>1</option>
						<option value="2" <%= c.getFiltroAvaliacao().equals("2") ? "selected" : ""%>>2</option>
						<option value="3" <%= c.getFiltroAvaliacao().equals("3") ? "selected" : ""%>>3</option>
						<option value="4" <%= c.getFiltroAvaliacao().equals("4") ? "selected" : ""%>>4</option>
						<option value="5" <%= c.getFiltroAvaliacao().equals("5") ? "selected" : ""%>>5</option>
					</select>
	
					<input type="submit" id="botaoRelatorio" value="Filtrar" formaction="SvClienteLocalizaAvaliacao" formmethod="post">
				</form>
			
				<div class="rolagem">
					<table cellspacing="15">
							
						<thead>
									
							<tr>
								
								<th>Pedido</th>
	                            
	                            <th>Restaurante</th>
										
								<th>Nota</th>
								
								<th>Descrição</th>
								
								<th>Data</th>									
										
							</tr>
									
						</thead>
						
						<tbody>	
								
					<%
						if(c.getListaAvaliacoesPedidos().isEmpty() || c.getListaAvaliacoesPedidos() == null){
							
							%>
								<tr>
									<td colspan="5">Nenhuma avaliação encontrada.</td>
								</tr>
								
							<%
								
						} else {
							
						for(int i = 0; i < c.getListaAvaliacoesPedidos().size(); i++){
							
							SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
								
							String data = ft.format(c.getListaAvaliacoesPedidos().get(i).getData());
					%>				
						 	<tr>
								
								<td class="tam"><%= c.getListaAvaliacoesPedidos().get(i).getPedido().getId() %></td>
											
	                            <td class="tam"><%= c.getListaAvaliacoesPedidos().get(i).getRestaurante().getNome() %></td>
	
								<td class="tam"><%= c.getListaAvaliacoesPedidos().get(i).getEstrelas() %></td>
									
								<td class="esp"><%= c.getListaAvaliacoesPedidos().get(i).getDescricao() %></td>
								
								<td class="tam"><%= data %></td>	
		
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