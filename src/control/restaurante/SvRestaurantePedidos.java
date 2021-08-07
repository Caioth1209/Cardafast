package control.restaurante;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.DonoRestaurante;


@WebServlet("/SvRestaurantePedidos")
public class SvRestaurantePedidos extends HttpServlet {
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
				
				d = d.pegarDados();
				
				request.getSession().setAttribute("dono", d);
			 	
				if (d.getRestaurante().getListaPedidos().isEmpty()) {
					
					request.setAttribute("msgErro", "Nenhum pedido até o momento.");
					
					request.setAttribute("msgSucess", "");
					
					request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);
					
				} else {
					
					d.getRestaurante().listarPedidos();
					
					request.getSession().setAttribute("dono", d);					
	
					request.getRequestDispatcher("restaurantePedidos.jsp").forward(request, response);

				}
			
	
			}
		}
	}

}
