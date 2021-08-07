package control.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;


@WebServlet("/SvClienteLocalizaPedido")
public class SvClienteLocalizaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
		
		String filtro = request.getParameter("filtro");
		
		c.setFiltroPedido(filtro);
		
		c.listarPedidos();
			
		request.getSession().setAttribute("cliente", c);
		
		request.getRequestDispatcher("clientePedidos.jsp").forward(request, response);
	}
}
