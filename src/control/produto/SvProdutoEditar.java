package control.produto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.DonoRestaurante;
import model.entidade.Produto;



@WebServlet("/SvProdutoEditar")
public class SvProdutoEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DonoRestaurante dr = (DonoRestaurante) request.getSession().getAttribute("dono");		
		
		long id = Long.parseLong(request.getParameter("id"));
		
		String nome = request.getParameter("nome");
		
		String desc = request.getParameter("descricao");
		
		double preco = 0;
		
		try {
			
			preco = Double.parseDouble(request.getParameter("preco").replace(",", "."));
			
		} catch (NumberFormatException e) {
			
			request.getRequestDispatcher("produtoDados.jsp").forward(request, response);
		}
		
		boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));

		Produto p = new Produto(id, nome, desc, preco, dr.getRestaurante(), ativo);
		
		if (p.editar()) {
			
			dr = dr.pegarDados();
				
			request.setAttribute("msgSucess", p.msg);
			
			request.setAttribute("msgErro", "");
			
			request.getSession().setAttribute("produtoDados", p);
						
			request.getRequestDispatcher("produtoDados.jsp").forward(request, response);
						
		} else {
						
			request.setAttribute("msgErro", p.msg);
			
			request.setAttribute("msgSucess", "");
						
			request.getRequestDispatcher("produtoDados.jsp").forward(request, response);
	
		}
	}

}
