package control.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;


@WebServlet("/SvClienteCadastra")
public class SvClienteCadastra extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String nome = request.getParameter("nome").trim();
		
		String cpf = request.getParameter("cpf").trim();
		
		String endereco = request.getParameter("endereco").trim();
		
		String login = request.getParameter("login").trim();
		
		String senha = request.getParameter("senha").trim();
		
		Cliente c = new Cliente(nome, login, senha, cpf, endereco);
		
		c.setTipo("cliente");
				
		if (c.cadastrar()) {
			
			request.setAttribute("msgSucess", c.msg);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("msgErro", c.msg);
			
			request.setAttribute("cliente", c);
			
			request.getRequestDispatcher("clienteCadastra.jsp").forward(request, response);

		}
	}

}
