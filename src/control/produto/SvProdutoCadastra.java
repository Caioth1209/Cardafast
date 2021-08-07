package control.produto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.*;


@WebServlet("/SvProdutoCadastra")
public class SvProdutoCadastra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DonoRestaurante dr = (DonoRestaurante) request.getSession().getAttribute("dono");
		
		String nome = request.getParameter("nome");
		
		String desc = request.getParameter("descricao");
		
		double preco = 0;
		
		try {
			
			preco = Double.parseDouble(request.getParameter("preco").replace(",", "."));
			
		} catch (NumberFormatException e) {
			
			request.getRequestDispatcher("produtoCadastra.jsp").forward(request, response);
		}
		
		Produto p = new Produto(nome, desc, preco, dr.getRestaurante());
		
		if (p.cadastrar()) {
			
			request.setAttribute("msgSucess", p.msg);
			
			dr = dr.pegarDados();
						
			request.getRequestDispatcher("produtoCadastra.jsp").forward(request, response);
						
		} else {
						
			request.setAttribute("msgErro", p.msg);
						
			request.setAttribute("produto", p);
						
			request.getRequestDispatcher("produtoCadastra.jsp").forward(request, response);
	
		}
	}

}
