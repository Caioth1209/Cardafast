package control.pedido;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;
import model.entidade.Pedido;


@WebServlet("/SvPedidoFinal")
public class SvPedidoFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
		
		if (c == null) {
			
			request.getSession(false);
			request.setAttribute("msgErro", "Se cadastre para entrar nessa tela.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else {
			
			Date agora = new Date();
			
			if ((agora.getTime() - request.getSession().getLastAccessedTime()) > 15*60*1000) {
				
				request.getSession(false);
				
				request.getSession().setAttribute("cliente",null);
								
				request.setAttribute("msgErro", "Tempo de inatividade esgotado: 15 minutos!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			} else {
					
				Pedido p = (Pedido) request.getSession().getAttribute("pedido");
				
				if (p.getRestaurante() == null) {
					
					request.setAttribute("msgErro", "Erro ao prosseguir com o pedido.");
					request.getRequestDispatcher("pedidoRestauranteEscolha.jsp").forward(request, response);
					
				} else {
					
					if (p.getListaProdutos() == null || p.getListaProdutos().isEmpty()) {
						
						request.setAttribute("msgErro", "Erro ao prosseguir com o pedido.");
						request.getRequestDispatcher("pedidoProdutosEscolha.jsp").forward(request, response);
						
					} else {
						
						double valorTotal = 0;
						
						for (int i = 0; i < p.getListaProdutos().size(); i++) {	
							valorTotal += p.getListaProdutos().get(i).getPreco();
						}
						
						p.setValorTotal(valorTotal);
						
						p.setFormaPagamento(request.getParameter("formaPagamento"));
						
						p.setData(new Date());
						
						p.setHora(new Date());
						
						p.setStatus("Pendente");
						
						if (p.cadastrar()) {
							
							c.setPedidoEmAberto(true);
							
							c.editarDados();
							
							c = c.pegarDados();

							request.getSession().setAttribute("cliente",c);
							
							request.setAttribute("msgSucess", p.msg);
							
						} else {
							
							request.setAttribute("msgErro", p.msg);
							
						}
						
						request.getSession().setAttribute("pedido", null);
						
						request.getSession().setAttribute("carrinho", null);
						
						request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);
					}
					
				}
				
			}
			
		}
	}


}
