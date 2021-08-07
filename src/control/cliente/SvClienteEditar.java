package control.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Cliente;

@WebServlet("/SvClienteEditar")
public class SvClienteEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		String nome = request.getParameter("nome").trim();
		
		String cpf = request.getParameter("cpf").trim();
		
		String endereco = request.getParameter("endereco").trim();
		
		String login = request.getParameter("login").trim();
		
		String senha = request.getParameter("senha").trim();
		
		String tipo = request.getParameter("tipo");
		
		Cliente c = new Cliente(id, nome, login, senha, cpf, tipo, endereco);
				
		if (c.editarDados()) {
			
			request.setAttribute("msgSucess", c.msg);
			
			c = c.pegarDados();

			request.getSession().setAttribute("cliente", c);
			
			request.getRequestDispatcher("clienteDados.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("msgErro", c.msg);
			
			request.getRequestDispatcher("clienteDados.jsp").forward(request, response);

		}
		
	}

}
