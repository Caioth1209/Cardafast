package control.restaurante;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;
import model.entidade.DonoRestaurante;

@WebServlet("/SvRestauranteLocalizaPedido")
public class SvRestauranteLocalizaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DonoRestaurante d = (DonoRestaurante) request.getSession().getAttribute("dono");
		
		String filtro = request.getParameter("filtro");
		
		d.getRestaurante().setFiltroPedido(filtro);
		
		d.getRestaurante().listarPedidos();
		
		request.getSession().setAttribute("dono", d);
			
		request.getRequestDispatcher("restaurantePedidos.jsp").forward(request, response);
	}

}
