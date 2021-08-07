package control.avaliacaoPedido;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.AvaliacaoPedido;
import model.entidade.Cliente;
import model.entidade.Pedido;


@WebServlet("/SvAvaliaPedido")
public class SvAvaliaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pedido pedido = (Pedido) request.getSession().getAttribute("pedidoAvaliado");
	
		int nota = Integer.parseInt(request.getParameter("nota"));
		
		String descricao = request.getParameter("descricao");
		
		AvaliacaoPedido a = new AvaliacaoPedido(nota, descricao, pedido.getRestaurante(), pedido.getCliente());
		
		a.setData(new Date());
		
		pedido.setAvaliacaoPedido(a);
		
		if (pedido.editarDados()) {
			
			Cliente c = (Cliente) request.getSession().getAttribute("cliente");
			
			c = c.pegarDados();
			
			c.listarPedidos();
			
			request.getSession().setAttribute("cliente", c);
			
			request.setAttribute("msgSucess", "Pedido avaliado com sucesso!");
			
			request.getRequestDispatcher("clientePedidos.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("msgErro", pedido.msg);
			
			request.getRequestDispatcher("clientePedidos.jsp").forward(request, response);
			
		}
	
	}

}
