 package control.donoRest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.*;


@WebServlet("/SvDonoRestCadastra")
public class SvDonoRestCadastra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeDono = request.getParameter("nomeDono").trim();
			
		String cpf = request.getParameter("cpf").trim();
		
		String login = request.getParameter("login").trim();
		
		String senha = request.getParameter("senha").trim();
		
		String nomeRest = request.getParameter("nomeRest").trim();
		
		String cnpj = request.getParameter("cnpj").trim();
				
		String endereco = request.getParameter("endereco").trim();
		
		Restaurante r = new Restaurante(nomeRest, cnpj, endereco);
		
		DonoRestaurante dr = new DonoRestaurante(nomeDono, login, senha, cpf, r);
		
		dr.setTipo("dono");
		
		if (dr.cadastrar()) {
				
			request.setAttribute("msgSucess", dr.msg);
						
			request.getRequestDispatcher("login.jsp").forward(request, response);
						
		} else {
						
			request.setAttribute("msgErro", dr.msg);
						
			request.setAttribute("donoRest", dr);
						
			request.getRequestDispatcher("donoRestCadastra.jsp").forward(request, response);
	
		}
	}

}
