package control.login;

import model.entidade.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SvLogin")
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().setAttribute("usuarioLogado", null);
		
		String login = request.getParameter("login").trim();
		
		String senha = request.getParameter("senha").trim();
		
		Usuario u = new Usuario(login, senha);
		
		if (u.login()) {
	
			request.getSession().setAttribute("usuarioLogado", u);
			
			request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);
	

		} else {
			
			request.getSession(false);
			
			request.setAttribute("msgErro", u.msg);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
