package control.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;


@WebServlet("/SvClienteLocalizaAvaliacao")
public class SvClienteLocalizaAvaliacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
		
		String filtro = request.getParameter("filtro");
		
		c.setFiltroAvaliacao(filtro);
		
		c.listarAvaliacoes();
			
		request.getSession().setAttribute("cliente", c);
		
		request.getRequestDispatcher("clienteAvaliacoes.jsp").forward(request, response);	
	}

}
