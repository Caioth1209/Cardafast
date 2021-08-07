package model.persistencia;

import java.util.ArrayList;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import model.entidade.Produto;

public class PProduto {

	Criteria crit;

	public PProduto() {
		
	}
	
	public boolean cadastrar(Produto p) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;

		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.save(p);
					
					conn.sessao.getTransaction().commit();
					
					p.msg = "Produto cadastrado com sucesso!";
	
				}
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao cadastrar produto: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean editar(Produto p) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;

		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.update(p);
					
					conn.sessao.getTransaction().commit();
					
					p.msg = "Produto atualizado com sucesso!";
	
				}
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao atualizar produto: " + e.toString();
				
				retorno = false;
				
			} catch(Exception e) {
				
				p.msg = "Erro ao atualizar produto: " + e.toString();
				
				retorno = false;
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean deletar(Produto p) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;

		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.delete(p);
					
					conn.sessao.getTransaction().commit();
					
					p.msg = "Produto deletado com sucesso!";
	
				}
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao deletar produto: " + e.toString();
				
				retorno = false;
				
			} catch(Exception e) {
				
				p.msg = "Erro ao deletar produto: " + e.toString();
				
				retorno = false;
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean listarTodos(Produto p) {
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Produto.class);
				
				crit.createAlias("restaurante", "r");
				crit.add(Restrictions.eq("r.id", p.getRestaurante().getId()));
				
				lista = (ArrayList<Produto>) crit.list();
				
				try {
					
					if (lista.isEmpty()) {
						throw new IndexOutOfBoundsException();
					} else {
						p.setLista(lista);
					}
					
				} catch (IndexOutOfBoundsException e) {
					p.msg = "Não existem produtos cadastrados nesse restaurante.";
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao listar produtos: " + e.toString();
				retorno = false;
				
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarAtivos(Produto p) {
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Produto.class);
				
				crit.createAlias("restaurante", "r");
				crit.add(Restrictions.eq("r.id", p.getRestaurante().getId()));
				crit.add(Restrictions.eq("ativo", true));
				
				lista = (ArrayList<Produto>) crit.list();
				
				try {
					
					if (lista.isEmpty()) {
						throw new IndexOutOfBoundsException();
					} else {
						p.setLista(lista);
					}
					
				} catch (IndexOutOfBoundsException e) {
					p.msg = "Não existem produtos ativos nesse restaurante.";
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao listar produtos: " + e.toString();
				retorno = false;
				
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarLikeNomeAtivos(Produto p) {
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Produto.class);
				
				crit.add(Restrictions.like("nome","%" + p.getNome() + "%"));
				crit.createAlias("restaurante", "r");
				crit.add(Restrictions.eq("r.id", p.getRestaurante().getId()));
				crit.add(Restrictions.eq("ativo", true));

				
				lista = (ArrayList<Produto>) crit.list();
				
				try {
					
					if (lista.get(0) == null) {
						throw new IndexOutOfBoundsException();
					} else {
						p.setLista(lista);
					}
					
				} catch (IndexOutOfBoundsException e) {
					p.msg = "Não foram encontrados produtos.";
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao listar restaurantes: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public boolean listarLikeNome(Produto p) {
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Produto.class);
				
				crit.add(Restrictions.like("nome","%" + p.getNome() + "%"));
				crit.createAlias("restaurante", "r");
				crit.add(Restrictions.eq("r.id", p.getRestaurante().getId()));

				
				lista = (ArrayList<Produto>) crit.list();
				
				try {
					
					if (lista.get(0) == null) {
						throw new IndexOutOfBoundsException();
					} else {
						p.setLista(lista);
					}
					
				} catch (IndexOutOfBoundsException e) {
					p.msg = "Não foram encontrados produtos.";
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao listar restaurantes: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
	}
	
	public Produto pegarDados(Produto p) {
		
		Conexao conn = new Conexao();
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			return null;
			
		} else {
			
			try {
				
				conn.sessao.beginTransaction();
				
				p = conn.sessao.find(Produto.class, p.getId());
				
				conn.sessao.getTransaction().commit();
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao localizar produto: " + e.toString();
				
				return null;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			return null;
		}
		
		return p;
	}
}
