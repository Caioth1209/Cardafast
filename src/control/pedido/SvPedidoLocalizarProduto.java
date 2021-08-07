package control.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Pedido;
import model.entidade.Produto;


@WebServlet("/SvPedidoLocalizarProduto")
public class SvPedidoLocalizarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pedido pe = (Pedido) request.getSession().getAttribute("pedido");
		
		String nome = request.getParameter("nomeProcurado");
		
		Produto p = new Produto(nome, pe.getRestaurante());
			
		if (p.listarLikeNome()) {
				
			request.getSession().setAttribute("listaProdutos", p.getLista());
				
			request.getRequestDispatcher("pedidoProdutosEscolha.jsp").forward(request, response);
				
		} else {
				
			request.getSession().setAttribute("listaProdutos", null);
				
			request.getRequestDispatcher("pedidoProdutosEscolha.jsp").forward(request, response);
		}
	}

}
