package control.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Restaurante;


@WebServlet("/SvPedidoRestauranteListarTodos")
public class SvPedidoRestauranteListarTodos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Restaurante r = new Restaurante();
		
		if (r.listarTodos()) {
				
			request.getSession().setAttribute("listaRest", r.getLista());
				
		} else {
				
			request.getSession().setAttribute("listaRest", null);

		}
		
		request.getRequestDispatcher("pedidoRestauranteEscolha.jsp").forward(request, response);
	}

	
}
