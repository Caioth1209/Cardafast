package model.persistencia;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import model.entidade.AvaliacaoPedido;
import model.entidade.Cliente;
import model.entidade.DonoRestaurante;
import model.entidade.Pedido;
import model.entidade.Restaurante;

public class PRestaurante {
	
	Criteria crit;
	Query query;
	
	public PRestaurante() {
		
	}
	
	public Restaurante pegarDados(Restaurante r) {
		
		Conexao conn = new Conexao();
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			
			return null;
			
		} else {
			
			try {
				
				conn.sessao.beginTransaction();
				
				r = conn.sessao.find(Restaurante.class, r.getId());
				
				conn.sessao.getTransaction().commit();

				
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao localizar restaurante: " + e.toString();
				
				return null;
				
			}
		}
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			
			return null;
		}
		
		return r;
	}
	
	public boolean editarDados(Restaurante r) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				r.msg = "Não foi possível editar informações: <br>";
				
				if (localizarNomeExistente(r)) {
					
					r.msg += " - Nome de restaurante já existente! <br>";
					
					r.setNome("");
					
					retorno = false;
					
				}
				
				if (localizarCnpjExistente(r)) {
					
					r.msg += " - Cnpj já existente! <br>";
					
					r.setCnpj("");
					
					retorno = false;
					
				} 
				
				if (localizarEnderecoExistente(r)) {
					
					r.msg += " - Endereço de restaurante já existente! <br>";
					
					r.setEndereco("");
					
					retorno = false;
					
				} 
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.update(r);
					
					conn.sessao.getTransaction().commit();
					
					r.msg = "Informações do restaurante foram atualizadas com sucesso!";
					
				}
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao atualizar informações de restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean localizarNomeExistente(Restaurante r) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Restaurante.class);

				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", r.getId())), Restrictions.eq("nome", r.getNome())));

				
				if (crit.uniqueResult() != null) {
										
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao localizar restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}

	
	public boolean localizarCnpjExistente(Restaurante r) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Restaurante.class);
				
				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", r.getId())), Restrictions.eq("cnpj", r.getCnpj())));

				
				if (crit.uniqueResult() != null) {
										
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao localizar restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	
	public boolean localizarEnderecoExistente(Restaurante r) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Restaurante.class);
				
				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", r.getId())), Restrictions.eq("endereco", r.getEndereco())));
				
				if (crit.uniqueResult() != null) {
										
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao localizar restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean listarLikeNome(Restaurante r) {
		
		ArrayList<Restaurante> lista = new ArrayList<Restaurante>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Restaurante.class);
				
				crit.add(Restrictions.like("nome","%" + r.getNome() + "%"));
				
				lista = (ArrayList<Restaurante>) crit.list();
				
				try {
					
					if (lista.get(0) == null) {
						throw new IndexOutOfBoundsException();
					} else {
						r.setLista(lista);
					}
					
				} catch (IndexOutOfBoundsException e) {
					r.msg = "Não foram encontrados restaurantes.";
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao listar restaurantes: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarTodos(Restaurante r) {
		
		ArrayList<Restaurante> lista = new ArrayList<Restaurante>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Restaurante.class);
				
				lista = (ArrayList<Restaurante>) crit.list();
				
				try {
					
					if (lista.get(0) == null) {
						throw new IndexOutOfBoundsException();
					} else {
						r.setLista(lista);
					}
					
				} catch (IndexOutOfBoundsException e) {
					r.msg = "Não foram encontrados restaurantes.";
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao listar restaurantes: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarPedidos(Restaurante r) {
		
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pedido.class);
				
				crit.createAlias("restaurante", "r");
				crit.add(Restrictions.eq("r.id", r.getId()));
				if (!r.getFiltroPedido().equals("Todos")) {
					crit.add(Restrictions.eq("status", r.getFiltroPedido()));
				}
				crit.addOrder(Order.desc("id"));
			
				
				lista = (ArrayList<Pedido>) crit.list();
				
				try {
					r.setListaPedidos(lista);
				} catch (IndexOutOfBoundsException e) {
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao listar pedidos: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarAvaliacoes(Restaurante r) {
		
		ArrayList<AvaliacaoPedido> lista = new ArrayList<AvaliacaoPedido>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(AvaliacaoPedido.class);
				
				crit.createAlias("restaurante", "r");
				crit.add(Restrictions.eq("r.id", r.getId()));
				if (!r.getFiltroAvaliacao().equals("Todos")) {
					
					switch (r.getFiltroAvaliacao()) {
	
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
					r.setListaAvaliacoesPedidos(lista);
				} catch (IndexOutOfBoundsException e) {
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao listar avaliações: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	
	public boolean listarPedidos1d(Restaurante r) {
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				String sql = "FROM pedidos where id_restaurante = :id_restaurante"
						+ " AND status = 'Entregue' AND data = CURRENT_DATE()";
				
				query = conn.sessao.createQuery(sql); 
				
				query.setParameter("id_restaurante", r.getId()); 
				
				List<Pedido> lista = query.list();
			
				
				double faturamento = 0;
				
				if (lista.isEmpty()) {
					faturamento = 0;
				} else {
				
					for (int i = 0; i < lista.size(); i++) {
						faturamento += lista.get(i).getValorTotal();
					}
				}
				
				r.setFaturamento1d(faturamento);
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao listar pedidos: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarPedidos15d(Restaurante r) {
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {

				String sql = "FROM pedidos where id_restaurante = :id_restaurante"
						+ " AND status = 'Entregue' AND data BETWEEN CURRENT_DATE() - 15 and CURRENT_DATE()";
				
				query = conn.sessao.createQuery(sql); 
				
				query.setParameter("id_restaurante", r.getId()); 
				
				List<Pedido> lista = query.list();
			
				
				double faturamento = 0;
				
				if (lista.isEmpty()) {
					faturamento = 0;
				} else {
				
					for (int i = 0; i < lista.size(); i++) {
						faturamento += lista.get(i).getValorTotal();
					}
				}
				
				r.setFaturamento15d(faturamento);
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao listar pedidos: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarPedidos30d(Restaurante r) {
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {

				String sql = "FROM pedidos where id_restaurante = :id_restaurante"
						+ " AND status = 'Entregue' AND data BETWEEN CURRENT_DATE() - 30 and CURRENT_DATE()";
				
				query = conn.sessao.createQuery(sql); 
				
				query.setParameter("id_restaurante", r.getId()); 
				
				List<Pedido> lista = query.list();
			
				
				double faturamento = 0;
				
				if (lista.isEmpty()) {
					faturamento = 0;
				} else {
				
					for (int i = 0; i < lista.size(); i++) {
						faturamento += lista.get(i).getValorTotal();
					}
				}
				
				r.setFaturamento30d(faturamento);
				
			} catch (HibernateException e) {
				
				r.msg = "Erro ao listar pedidos: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			r.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
}
