package control.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Pedido;
import model.entidade.Produto;


@WebServlet("/SvPedidoProdutosListarTodos")
public class SvPedidoProdutosListarTodos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pedido p = (Pedido) request.getSession().getAttribute("pedido");
		
		Produto pr = new Produto(p.getRestaurante());
	
		if (pr.listarTodos()) {
			
			request.getSession().setAttribute("listaProdutos", pr.getLista());
	
		} else {
			
			request.getSession().setAttribute("listaProdutos", null);

		}
		
		request.getRequestDispatcher("pedidoProdutosEscolha.jsp").forward(request, response);
		
	}

}
