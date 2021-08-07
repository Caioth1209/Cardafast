package control.cliente;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;


@WebServlet("/SvClienteAvaliacoes")
public class SvClienteAvaliacoes extends HttpServlet {
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
				
				if (c.getListaAvaliacoesPedidos().isEmpty()) {
					
					request.setAttribute("msgErro", "Você não fez avaliações até o momento.");
					
					request.setAttribute("msgSucess", "");
					
					request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);
					
				} else {
					
					c.listarAvaliacoes();
					
					request.getSession().setAttribute("cliente", c);		
					
					request.getRequestDispatcher("clienteAvaliacoes.jsp").forward(request, response);
				}
	
			}
		}
	}

}
