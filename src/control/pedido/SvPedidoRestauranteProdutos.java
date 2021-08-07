package control.pedido;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;
import model.entidade.Pedido;
import model.entidade.Produto;
import model.entidade.Restaurante;

@WebServlet("/SvPedidoRestauranteProdutos")
public class SvPedidoRestauranteProdutos extends HttpServlet {
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
		
				request.getSession().setAttribute("cliente", null);
				request.setAttribute("msg", "Tempo de inatividade esgotado: 15 minutos!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			} else {
				
				long id = Long.parseLong(request.getParameter("restaurante"));
				
				Restaurante r = new Restaurante(id);
				
				r = r.pegarDados();
				
				Pedido p = (Pedido) request.getSession().getAttribute("pedido");
				
				p.setRestaurante(r);
				
				request.getSession().setAttribute("pedido", p);
				
				Produto pr = new Produto(p.getRestaurante());
				
				if (pr.listarTodos()) {
					
					request.getSession().setAttribute("listaProdutos", pr.getLista());
					
					request.getRequestDispatcher("pedidoProdutosEscolha.jsp").forward(request, response);
					
				} else {
					
					request.getSession().setAttribute("listaProdutos", null);
					
					request.getRequestDispatcher("pedidoProdutosEscolha.jsp").forward(request, response);
				}	
				
			}
		
		}
		
		
	}

}
