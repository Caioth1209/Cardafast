package control.produto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.DonoRestaurante;
import model.entidade.Pedido;
import model.entidade.Produto;


@WebServlet("/SvProdutoListarTodos")
public class SvProdutoListarTodos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DonoRestaurante d = (DonoRestaurante) request.getSession().getAttribute("dono");
		
		Produto pr = new Produto(d.getRestaurante());
		
		if (pr.listarTodos()) {
			
			request.getSession().setAttribute("listaProdutos", pr.getLista());
	
		} else {
			request.getSession().setAttribute("listaProdutos", null);
		}
		
		request.getRequestDispatcher("produtoMenu.jsp").forward(request, response);
	}

}
