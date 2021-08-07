package model.persistencia;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import model.entidade.AvaliacaoPedido;
import model.entidade.Cliente;
import model.entidade.DonoRestaurante;
import model.entidade.Pedido;
import model.entidade.Usuario;

public class PDonoRestaurante {
	
	Criteria crit;

	public PDonoRestaurante() {
		
	}
	
	public boolean cadastrar(DonoRestaurante d) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				System.out.println(d.getRestaurante().getNome());
				
				d.msg = "Não foi possível cadastrar: <br>";
				
				if(d.getRestaurante().localizarNomeExistente()) {
					
					d.msg += " - Nome de restaurante já existente! <br>";
					
					d.getRestaurante().setNome("");
					
					retorno = false;
				}
				
				if(d.getRestaurante().localizarCnpjExistente()) {
					
					d.msg += " - CNPJ já existente! <br>";
					
					d.getRestaurante().setCnpj("");
					
					retorno = false;
				}
				
				if(d.getRestaurante().localizarEnderecoExistente()) {
					
					d.msg += " - Endereço de restaurante já existente! <br>";
					
					d.getRestaurante().setCnpj("");
					
					retorno = false;
				}
				
				if (localizarCpfExistente(d)) {
					
					d.msg += " - CPF já existente! <br>";
					
					d.setCpf("");
					
					retorno = false;
					
				}
				
				if (localizarLoginExistente(d)) {
					
					d.msg += " - Login já existente! <br>";
					
					d.setLogin("");
					
					retorno = false;
					
				} 
				
				if (localizarSenhaExistente(d)) {
					
					d.msg += " - Senha já existente! <br>";
					
					d.setSenha("");
					
					retorno = false;
					
				} 
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
				
					conn.sessao.save(d);
					
					conn.sessao.getTransaction().commit();
					
					d.msg = "Dono de restaurante e restaurante cadastrados com sucesso!";
					
				}							
				
			} catch (HibernateException e) {
				
				d.msg = "Erro ao cadastrar dono de restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean editarDados(DonoRestaurante d) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				d.msg = "Não foi possível editar suas informações: <br>";
				
				if (localizarCpfExistente(d)) {
					
					d.msg += " - CPF já existente! <br>";
					
					d.setCpf("");
					
					retorno = false;
					
				}
				
				if (localizarLoginExistente(d)) {
					
					d.msg += " - Login já existente! <br>";
					
					d.setLogin("");
					
					retorno = false;
					
				}
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.update(d);
					
					conn.sessao.getTransaction().commit();
					
					d.msg = "Suas informações foram atualizadas com sucesso!";
					
				}
				
			} catch (HibernateException e) {
				
				d.msg = "Erro ao editar dados de dono de restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean localizarCpfExistente(DonoRestaurante d) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Usuario.class);
	
				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", d.getId())), Restrictions.eq("cpf", d.getCpf())));


				if (crit.uniqueResult() != null) {
										
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				d.msg = "Erro ao localizar dono de restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean localizarLoginExistente(DonoRestaurante d) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(DonoRestaurante.class);
	
				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", d.getId())), Restrictions.eq("login", d.getLogin())));
				
				if (crit.uniqueResult() != null) {
										
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				d.msg = "Erro ao localizar dono de restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public boolean localizarSenhaExistente(DonoRestaurante d) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(DonoRestaurante.class);
	
				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", d.getId())), Restrictions.eq("senha", d.getSenha())));
				
				if (crit.uniqueResult() != null) {
										
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				d.msg = "Erro ao localizar dono de restaurante: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			d.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
	
	public DonoRestaurante pegarDados(DonoRestaurante d) {
		
		Conexao conn = new Conexao();
		
		if (!conn.conectar()) {
			
			d.msg = conn.msg;
			
			return null;
			
		} else {
			
			try {
				
				conn.sessao.beginTransaction();
				
				d = conn.sessao.find(DonoRestaurante.class, d.getId());
				
				conn.sessao.getTransaction().commit();

				
			} catch (HibernateException e) {
				
				d.msg = "Erro ao localizar dono de restaurante: " + e.toString();
				
				return null;
				
			}
		}
		
		if (!conn.fechar()) {
			
			d.msg = conn.msg;
			
			return null;
		}
		
		return d;
	}
	
	

}
