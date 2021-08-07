package control.donoRest;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.DonoRestaurante;
import model.entidade.Usuario;


@WebServlet("/SvDonoRestMenu")
public class SvDonoRestMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DonoRestaurante d = (DonoRestaurante) request.getSession().getAttribute("dono");
	
		if (d == null) {
			
			request.getSession(false);
			request.setAttribute("msgErro", "Se cadastre para entrar nessa tela.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else {
			
			Date agora = new Date();
			
			if ((agora.getTime() - request.getSession().getLastAccessedTime()) > 15*60*1000) {
				request.getSession(false);
				request.getSession().setAttribute("dono", null);
				request.setAttribute("msgErro", "Tempo de inatividade esgotado: 15 minutos!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				
				d.getRestaurante().setFiltroAvaliacao("Todos");
				
				d.getRestaurante().setFiltroPedido("Todos");
				
				d.getRestaurante().listarAvaliacoes();
				
				d.getRestaurante().listarPedidos();
				
				request.getSession().setAttribute("dono", d);
				
				for(int i = 0; i < d.getRestaurante().getListaPedidos().size(); i++) {
					if (d.getRestaurante().getListaPedidos().get(i).getStatus().equals("Pendente")) {
						request.setAttribute("msgSucess", "Você recebeu pedidos!!!");
						break;
					}
				}

				request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);
			
			}
		}
	}


}
