package control.produto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.DonoRestaurante;
import model.entidade.Produto;


@WebServlet("/SvProdutoDesativar")
public class SvProdutoDesativar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Produto p = (Produto) request.getSession().getAttribute("produtoDados");
		
    	DonoRestaurante d = (DonoRestaurante) request.getSession().getAttribute("dono");   			
		
		p.setAtivo(false);
		
		if (p.editar()) {
				
			request.setAttribute("msgSucess", "Produto desativado com sucesso!");
			
			request.getSession().setAttribute("produtoDados", p);
						
			request.getRequestDispatcher("produtoDados.jsp").forward(request, response);
						
		} else {
						
			request.setAttribute("msgErro", p.msg);
						
			request.getRequestDispatcher("produtoDados.jsp").forward(request, response);
	
		}
	}

}
