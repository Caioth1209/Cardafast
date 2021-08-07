package model.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistencia.*;

@Entity(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate(value = true) 
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 40)
	private String nome;
	
	@Column(length = 11, unique = true)
	private String cpf;
	
	@Column(length = 20, unique = true)
	private String login;
	
	@Column(length = 12, unique = true)
	private String senha;
	
	@Column(length = 30)
	private String tipo;
	
	@Transient
	public String msg;

	
	public Usuario() {}
	
	public Usuario(long id) {
		this.id = id;
	}
	
	
	public Usuario(long id, String nome, String login, String senha, String cpf, String tipo) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.cpf = cpf;
		this.tipo = tipo;
	}

	public Usuario(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(String nome, String login, String senha, String cpf) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.cpf = cpf;
	}
	
	
	public boolean login() {
		PUsuario pu = new PUsuario();
		return pu.login(this);
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
