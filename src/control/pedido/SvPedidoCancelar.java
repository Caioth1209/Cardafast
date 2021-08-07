package control.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;
import model.entidade.DonoRestaurante;
import model.entidade.Pedido;


@WebServlet("/SvPedidoCancelar")
public class SvPedidoCancelar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("idPedido"));
		
		Pedido p = new Pedido(id);
		
		p = p.pegarDados();
		
		p.setStatus("Cancelado");
		
		p.getCliente().setPedidoEmAberto(false);
		
		if (p.editarDados()) {
			
			if (request.getSession().getAttribute("dono") != null) {
				
				DonoRestaurante d = (DonoRestaurante) request.getSession().getAttribute("dono");
				
				d = d.pegarDados();
				
				d.getRestaurante().listarPedidos();

				request.getSession().setAttribute("dono", d);
				
				request.setAttribute("msgSucess", "Pedido cancelado com sucesso!");
				
				request.getRequestDispatcher("restaurantePedidos.jsp").forward(request, response);
				
				
			} else if (request.getSession().getAttribute("cliente") != null) {
				
				Cliente c = (Cliente) request.getSession().getAttribute("cliente");
				
				c = c.pegarDados();
				
				c.listarPedidos();
				
				request.getSession().setAttribute("cliente", c);
				
				request.setAttribute("msgSucess", "Pedido cancelado com sucesso!");

				request.getRequestDispatcher("clientePedidos.jsp").forward(request, response);
				
			}
		
		} else {
			
			request.setAttribute("msgErro", p.msg);
			
			request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);
	
		}
	}

}
