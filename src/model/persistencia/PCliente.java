package model.persistencia;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.entidade.AvaliacaoPedido;
import model.entidade.Cliente;
import model.entidade.Pedido;
import model.entidade.Restaurante;
import model.entidade.Usuario;

public class PCliente {
	
	Criteria crit;

	public PCliente() {
		
	}
	
	public boolean cadastrar(Cliente c) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;

		if (!conn.conectar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				c.msg = "Não foi possível cadastrar: <br>";
				
				if (localizarCpfExistente(c)) {
					
					c.msg += " - CPF já existente! <br>";
					
					c.setCpf("");
								
					retorno = false;
					
				}
				
				if (localizarLoginExistente(c)) {
					
					c.msg += " - Login já existente! <br>";
					
					c.setLogin("");
					
					retorno = false;
					
				}
				
				if (localizarSenhaExistente(c)) {
					
					c.msg += " - Senha já existente! <br>";
					
					c.setSenha("");
					
					retorno = false;
					
				}
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.save(c);
					
					conn.sessao.getTransaction().commit();
					
					c.msg = "Cliente cadastrado com sucesso!";
	
				}
				
			} catch (HibernateException e) {
				
				c.msg = "Erro ao cadastrar cliente: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean editarDados(Cliente c) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;

		if (!conn.conectar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				c.msg = "Não foi possível editar suas informações: <br>";
				
				if (localizarCpfExistente(c)) {
					
					c.msg += " - CPF já existente! <br>";
				
					retorno = false;
					
				}
				
				if (localizarLoginExistente(c)) {
					
					c.msg += " - Login já existente! <br>";
				
					retorno = false;
					
				} 
				
				if (localizarSenhaExistente(c)) {
					
					c.msg += " - Senha já existente! <br>";
					
					retorno = false;
					
				} 
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.update(c);
					
					conn.sessao.getTransaction().commit();
					
					c.msg = "Suas informações foram atualizadas com sucesso!";
	
				}
				
			} catch (HibernateException e) {
				
				c.msg = "Erro ao editar cliente: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean localizarCpfExistente(Cliente c) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Usuario.class);
							
				// Procura se existe algum usuario com o cpf, excluindo a pessoa que está logada.
				//Resolução do problema do codigo de baixo.
				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", c.getId())), Restrictions.eq("cpf", c.getCpf())));
				
				
				//Procura se existe algum usuario com o cpf, incluindo a pessoa que está logada.
				//Problema: Se for atualizar os dados e deixar o mesmo cpf, vai contar que já existe.
				//crit.add(Restrictions.eq("cpf", c.getCpf()));
				
				if (crit.uniqueResult() != null) {
					
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				c.msg = "Erro ao localizar cliente: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean localizarLoginExistente(Cliente c) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Usuario.class);
				
				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", c.getId())), Restrictions.eq("login", c.getLogin())));
				
				if (crit.uniqueResult() != null) {
										
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				c.msg = "Erro ao localizar cliente: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean localizarSenhaExistente(Cliente c) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Usuario.class);
				
				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", c.getId())), Restrictions.eq("senha", c.getSenha())));
				
				if (crit.uniqueResult() != null) {
										
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				c.msg = "Erro ao localizar cliente: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			c.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean listarPedidos(Cliente c) {
		
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			c.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pedido.class);
				
				crit.createAlias("cliente", "c");
				crit.add(Restrictions.eq("c.id", c.getId()));
				if (!c.getFiltroPedido().equals("Todos")) {
					crit.add(Restrictions.eq("status", c.getFiltroPedido()));
				}
				crit.addOrder(Order.desc("id"));
				
				lista = (ArrayList<Pedido>) crit.list();
				
				try {
					c.setListaPedidos(lista);
				} catch (IndexOutOfBoundsException e) {
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				c.msg = "Erro ao listar pedidos: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			c.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarAvaliacoes(Cliente c) {
		
		ArrayList<AvaliacaoPedido> lista = new ArrayList<AvaliacaoPedido>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			c.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(AvaliacaoPedido.class);
				
				crit.createAlias("cliente", "c");
				crit.add(Restrictions.eq("c.id", c.getId()));
				if (!c.getFiltroAvaliacao().equals("Todos")) {
					
					switch (c.getFiltroAvaliacao()) {
	
						case "1" :{
							crit.add(Restrictions.eq("estrelas", 1));
							
							break;
						}
						
						case "2" :{
							crit.add(Restrictions.eq("estrelas", 2));
							
							break;
						}
						
						case "3" :{
							crit.add(Restrictions.eq("estrelas", 3));
							
							break;
						}
						
						case "4" :{
							crit.add(Restrictions.eq("estrelas", 4));
							
							break;
						}
						
						case "5" :{
							crit.add(Restrictions.eq("estrelas", 5));
							
							break;
						}
					}
				}
				
				lista = (ArrayList<AvaliacaoPedido>) crit.list();
				
				try {
					c.setListaAvaliacoesPedidos(lista);
				} catch (IndexOutOfBoundsException e) {
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				c.msg = "Erro ao listar avaliações: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			c.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public Cliente pegarDados(Cliente c) {
		
		Conexao conn = new Conexao();
		
		if (!conn.conectar()) {
			
			c.msg = conn.msg;
			
			return null;
			
		} else {
			
			try {
				
				conn.sessao.beginTransaction();
				
				c = conn.sessao.find(Cliente.class, c.getId());
				
				conn.sessao.getTransaction().commit();

				
				
			} catch (HibernateException e) {
				
				c.msg = "Erro ao localizar cliente: " + e.toString();
				
				return null;
				
			}
		}
		
		if (!conn.fechar()) {
			
			c.msg = conn.msg;
			
			return null;
		}
		
		return c;
	}
	
}
