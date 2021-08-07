package model.persistencia;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import model.entidade.Cliente;
import model.entidade.DonoRestaurante;
import model.entidade.Pedido;
import model.entidade.Produto;

public class PPedido {

	Criteria crit;

	public PPedido() {
		
	}
	
	public boolean cadastrar(Pedido p) {
		
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
					
					p.msg = "Pedido enviado com sucesso! Confira o status dele em 'Meus Pedidos'";
	
				}
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao cadastrar pedido: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean editarDados(Pedido p) {
		
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
	
				}
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao editar pedido: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean listarProdutos(Pedido p) {
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pedido.class);
				
		        crit.createAlias("pedidos", "pe");
		        crit.add(Restrictions.eq("pe.id", p.getId()));
		        crit.createAlias("produtos", "pr");
		        crit.setFetchMode("pr", FetchMode.JOIN);
		        
				lista = (ArrayList<Produto>) crit.list();
				
				try {
					p.setListaProdutos(lista);
				} catch (IndexOutOfBoundsException e) {
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
	
	public Pedido pegarDados(Pedido p) {
		
		Conexao conn = new Conexao();
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			return null;
			
		} else {
			
			try {
				
				conn.sessao.beginTransaction();
				
				p = conn.sessao.find(Pedido.class, p.getId());
				
				conn.sessao.getTransaction().commit();
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao localizar pedido: " + e.toString();
				
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
