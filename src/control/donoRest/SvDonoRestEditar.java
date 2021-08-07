package control.donoRest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.DonoRestaurante;
import model.entidade.Restaurante;

@WebServlet("/SvDonoRestEditar")
public class SvDonoRestEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long idR = Long.parseLong(request.getParameter("idRest"));
				
		Restaurante r = new Restaurante(idR);
			
		r = r.pegarDados();
						
		long id = Long.parseLong(request.getParameter("id"));
		
		String nome = request.getParameter("nomeDono").trim();
		
		String cpf = request.getParameter("cpf").trim();
		
		String login = request.getParameter("login").trim();
		
		String senha = request.getParameter("senha").trim();
		
		String tipo = request.getParameter("tipo");
		
		DonoRestaurante d = new DonoRestaurante(id, nome, login, senha, cpf, tipo, r);
				
		if (d.editarDados()) {
			
			request.setAttribute("msgSucess", d.msg);
			
			d = d.pegarDados();
			
			request.getSession().setAttribute("dono", d);
			
			request.getRequestDispatcher("donoRestDados.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("msgErro", d.msg);
			
			request.getRequestDispatcher("donoRestDados.jsp").forward(request, response);

		}
	}

}
