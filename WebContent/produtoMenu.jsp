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
<title>Tela de produtos</title>
<script type="text/javascript" src="js/produto.js"></script>
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/produtoMenu.css">
</head>
<body>

	<%

		DonoRestaurante dr = (DonoRestaurante) request.getSession().getAttribute("dono");

		List<Produto> listaProdutos = (List<Produto>) request.getSession().getAttribute("listaProdutos");
		
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

                <form class="procuraProduto">

                    <div class="card-group">
                        <label>Procurar produto: </label>
                        <input placeholder="Nome do produto que deseja" type="text" name="nomeProcurado" />
                    </div>
                    
                    <div class="card-group">
                        
                        <input id="btLocaliza" type="submit" value="Localizar" formaction="SvProdutoLocalizar" formmethod="post">
                            
                        <input id="btListar" type="submit" value="Listar todos" formaction="SvProdutoListarTodos" formmethod="post">
                            
                        <input id="btCadastrar" type="submit" value="Cadastrar produto" formaction="SvProdutoTelaCadastro" formmethod="post"/>
                    </div>
    
                    <p id="msgErro"> <%= request.getAttribute("msgErro") == null ? "" : request.getAttribute("msgErro") %> </p>
                    
                    <p id="msgSucess"> <%= request.getAttribute("msgSucess") == null ? "" : request.getAttribute("msgSucess") %> </p>
                    
                </form>
                
                <div class="rolagem">
                
                    <table cellspacing="30">
                                
                        <thead>
                                    
                            <tr>
                                        
                                <th>Código</th>
                                                            
                                <th>Nome</th>
                                            
                                <th>Preço</th>
                                
                                <th>Ativo</th>
                                            
                                <th>Ações</th>
                                        
                            </tr>
                                    
                        </thead>
                        
                        <tbody>     	
                                        
                    <%
                        if(listaProdutos == null || listaProdutos.isEmpty()){
                    %>
                        <tr>
                            <td colspan="5">Nenhum produto encontrado.</td>
                        </tr>
                    <%
                        } else {
                            
                            for(int i = 0; i < listaProdutos.size(); i++){
                    %>				
                                <tr>
                                    <td class="tam"><%= listaProdutos.get(i).getId() %></td>
                                    
                                    <td class="esp tam"><%= listaProdutos.get(i).getNome() %></td>
   
   									<%
	   									DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	   							    	decimalFormat.setRoundingMode(RoundingMode.DOWN);
	   							    	  	
	   									String preco = decimalFormat.format(listaProdutos.get(i).getPreco());
   									%>
                                    <td class="tam">R$ <%= preco %></td>
                                    
                                    <td class="tam"><%= listaProdutos.get(i).isAtivo() ? "SIM" : "NÃO" %></td>
                                    
                                    <td>
                                        <form>
                                            <input type="hidden" name="id" value="<%=listaProdutos.get(i).getId()%>">
                                            
                                            <input class="acoes" type="submit" value="Ações" formmethod="post" formaction="SvProdutoDados"> 
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