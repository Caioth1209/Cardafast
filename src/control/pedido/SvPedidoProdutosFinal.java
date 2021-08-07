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
import model.entidade.Produto;


@WebServlet("/SvPedidoProdutosFinal")
public class SvPedidoProdutosFinal extends HttpServlet {
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
					
				List<Produto> carrinho = (List<Produto>) request.getSession().getAttribute("carrinho");
				
				if (carrinho == null || carrinho.isEmpty()) {
					request.setAttribute("msgErro", "Erro ao prosseguir com o pedido.");
					request.getRequestDispatcher("pedidoRestauranteEscolha.jsp").forward(request, response);
				} else {
					
					Pedido p = (Pedido) request.getSession().getAttribute("pedido");
					
					p.setListaProdutos(carrinho);
					
					request.getSession().setAttribute("pedido", p);
					
					request.getRequestDispatcher("pedidoFinal.jsp").forward(request, response);
					
				}
				
			}
			
		}
	}

}
