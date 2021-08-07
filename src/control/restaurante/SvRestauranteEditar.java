package control.restaurante;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.*;

@WebServlet("/SvRestauranteEditar")
public class SvRestauranteEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long id = Long.parseLong(request.getParameter("id"));
		
		String nome = request.getParameter("nomeRest").trim();
		
		String cnpj = request.getParameter("cnpj").trim();
		
		String endereco = request.getParameter("endereco").trim();
		
		double faturamento = Double.parseDouble(request.getParameter("faturamento"));
		
		Restaurante r = new Restaurante(id, nome, cnpj, endereco, faturamento);
				
		if (r.editarDados()) {
			
			request.setAttribute("msgSucess", r.msg);
			
	    	DonoRestaurante dr = (DonoRestaurante) request.getSession().getAttribute("dono");
	    	
			dr.setRestaurante(r);
			
			dr = dr.pegarDados();
			
			request.getSession().setAttribute("dono", dr);

			request.getRequestDispatcher("restauranteDados.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("msgErro", r.msg);
			
			request.getRequestDispatcher("restauranteDados.jsp").forward(request, response);

		}
	}


}
