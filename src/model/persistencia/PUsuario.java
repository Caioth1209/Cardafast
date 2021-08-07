package model.persistencia;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import model.entidade.Usuario;

public class PUsuario {

	Criteria crit;
	
	public PUsuario() {
		
	}
	
	public boolean login(Usuario u) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			u.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Usuario.class);
				
				crit.add(Restrictions.eq("login", u.getLogin()));
				crit.add(Restrictions.eq("senha", u.getSenha()));
				
				Usuario aux = (Usuario) crit.uniqueResult();
				
				if (aux != null) {
					
					u.setId(aux.getId());
					u.setTipo(aux.getTipo());
					u.setNome(aux.getNome());
					u.setCpf(aux.getCpf());
					u.setLogin(aux.getLogin());
					u.setSenha(aux.getSenha());

				} else {
					
					u.msg = "Login ou Senha inválidos!";
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				u.msg = "Erro ao localizar usuário: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			u.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}
}
