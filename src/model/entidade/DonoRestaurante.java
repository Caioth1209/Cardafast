package model.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistencia.PCliente;
import model.persistencia.PDonoRestaurante;

@Entity(name = "donos_restaurante")
@PrimaryKeyJoinColumn(name="idUsuario")
@DynamicUpdate(value = true) 
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class DonoRestaurante extends Usuario {

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restaurante", nullable = true, 
	foreignKey = @ForeignKey(name = "fk_Dono_Restaurante"))
	private Restaurante restaurante;
	
	public DonoRestaurante() {
		
	}

	public DonoRestaurante(long id) {
		super(id);
	}

	public DonoRestaurante(long id, String nome, String login, String senha, String cpf, String tipo, Restaurante r) {
		super(id, nome, login, senha, cpf, tipo);
		this.restaurante = r;
	}
	
	public DonoRestaurante(long id, String nome, String login, String senha, String cpf, String tipo) {
		super(id, nome, login, senha, cpf, tipo);
	}

	public DonoRestaurante(String nome, String login, String senha) {
		super(nome, login, senha);
	}

	public DonoRestaurante(String nome, String login, String senha, String cpf) {
		super(nome, login, senha, cpf);
	}
	
	public DonoRestaurante(String nome, String login, String senha, String cpf, Restaurante r) {
		super(nome, login, senha, cpf);
		this.restaurante = r;
	}


	public boolean cadastrar() {
		PDonoRestaurante pd = new PDonoRestaurante();
		return pd.cadastrar(this);
	}
	
	public boolean editarDados() {
		PDonoRestaurante pd = new PDonoRestaurante();
		return pd.editarDados(this);
	}

	
	public DonoRestaurante pegarDados() {
		PDonoRestaurante pd = new PDonoRestaurante();
		return pd.pegarDados(this);
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	
	
}
