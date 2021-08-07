package control.cliente;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;
import model.entidade.Usuario;


@WebServlet("/SvClienteMenu")
public class SvClienteMenu extends HttpServlet {
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
				request.setAttribute("msgErro", "Tempo de inatividade esgotado: 15 minutos!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				
				c.setFiltroAvaliacao("Todos");
				
				c.setFiltroPedido("Todos");
				
				c.listarAvaliacoes();
				
				c.listarPedidos();
				
				request.getSession().setAttribute("cliente", c);
				
				request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);

			}
		}
		
	}


}
