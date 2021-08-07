package control.pedido;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.*;


@WebServlet("/SvPedidoTela")
public class SvPedidoTela extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
		
		Pedido p = new Pedido();
		
		if (c == null) {
			
			request.getSession(false);
			request.setAttribute("msg", "Erro ao tentar entrar na tela de pedido.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else {
			
			Date agora = new Date();
			
			if ((agora.getTime() - request.getSession().getLastAccessedTime()) > 15*60*1000) {
				
				request.getSession(false);
				
				request.getSession().setAttribute("cliente",null);
				
				request.setAttribute("msg", "Tempo de inatividade esgotado: 15 minutos!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			} else {
				
				if (c.isPedidoEmAberto()) {
					
					request.setAttribute("msgErro", "Você tem um pedido em aberto! Espere recebê-lo para depois fazer outro pedido.");
					
					request.setAttribute("msgSucess", "");

					request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);

					
				} else {
					
					Restaurante r = new Restaurante();
					
					p.setCliente(c);
					
					request.getSession().setAttribute("carrinho", null);
					
					if (r.listarTodos()) {
							
						request.getSession().setAttribute("listaRest", r.getLista());
						
						request.getSession().setAttribute("pedido", p);
							
						request.setAttribute("msgSucess", r.msg);
							
						request.getRequestDispatcher("pedidoRestauranteEscolha.jsp").forward(request, response);
							
					} else {
							
						request.getSession().setAttribute("listaRest", null);
						
						request.getSession().setAttribute("pedido", p);
							
						request.setAttribute("msgErro", r.msg);
							
						request.getRequestDispatcher("pedidoRestauranteEscolha.jsp").forward(request, response);
					}
					
				}
				
			}
		}
	}

}
