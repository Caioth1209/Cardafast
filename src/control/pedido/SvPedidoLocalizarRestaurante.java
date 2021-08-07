package control.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.entidade.Restaurante;


@WebServlet("/SvPedidoLocalizarRestaurante")
public class SvPedidoLocalizarRestaurante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nomeProcurado");
			
		Restaurante r = new Restaurante(nome);
			
		if (r.listarLikeNome()) {
				
			request.getSession().setAttribute("listaRest", r.getLista());
				
			request.getRequestDispatcher("pedidoRestauranteEscolha.jsp").forward(request, response);
				
		} else {
				
			request.getSession().setAttribute("listaRest", null);
				
			request.getRequestDispatcher("pedidoRestauranteEscolha.jsp").forward(request, response);
		}
	}

}
