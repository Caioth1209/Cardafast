package control.pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.*;



@WebServlet("/SvPedidoAdicionaProduto")
public class SvPedidoAdicionaProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("produto"));
		
		Produto p = new Produto(id);
		
		p = p.pegarDados();
		
		if (p != null) {
			
			List<Produto> carrinho = (List<Produto>) request.getSession().getAttribute("carrinho");
			
			if (carrinho == null) {
				carrinho = new ArrayList<Produto>();
			}
		
			carrinho.add(p);
			
			request.getSession().setAttribute("carrinho", carrinho);
			
			p.listarTodos();
			
			request.getSession().setAttribute("listaProdutos", p.getLista());
			
			request.setAttribute("msgSucess", "Produto adicionado ao carrinho com sucesso!");
						
		} else {
			
			request.setAttribute("msgErro", "Erro ao adicionar produto no carrinho!");
			
		}
		
		request.getRequestDispatcher("pedidoProdutosEscolha.jsp").forward(request, response);
	
	}

}
