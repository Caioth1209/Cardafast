package model.entidade;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistencia.PCliente;

@Entity(name = "clientes")
@PrimaryKeyJoinColumn(name="idUsuario")
@DynamicUpdate(value = true) 
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Cliente extends Usuario {
	
	@Column(length = 80)
	private String endereco;
	
	@Column
	private boolean pedidoEmAberto = false;
	
	@OneToMany(mappedBy = "cliente")
	private List<AvaliacaoPedido> listaAvaliacoesPedidos = new ArrayList<AvaliacaoPedido>();

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();
	
	@Transient
	private String filtroPedido = "Todos";
	
	@Transient
	private String filtroAvaliacao = "Todos";
	
	public Cliente() {}
	
	public Cliente(long id) {
		super(id);
	}

	public Cliente(long id, String nome, String login, String senha, String cpf, String tipo) {
		super(id, nome, login, senha, cpf, tipo);
	}
	
	public Cliente(long id, String nome, String login, String senha, String cpf, String tipo, String endereco) {
		super(id, nome, login, senha, cpf, tipo);
		this.endereco = endereco;
	}

	public Cliente(String nome, String login, String senha) {
		super(nome, login, senha);
	}


	public Cliente(String nome, String login, String senha, String cpf, String endereco) {
		super(nome, login, senha, cpf);
		this.endereco = endereco;
	}


	public Cliente(String login, String senha) {
		super(login, senha);

	}
	
	
	public boolean cadastrar() {
		PCliente pc = new PCliente();
		return pc.cadastrar(this);
	}
	
	public boolean editarDados() {
		PCliente pc = new PCliente();
		return pc.editarDados(this);
	}
	
	public boolean listarPedidos() {
		PCliente pc = new PCliente();
		return pc.listarPedidos(this);
	}
	
	public boolean listarAvaliacoes() {
		PCliente pc = new PCliente();
		return pc.listarAvaliacoes(this);
	}
	
	public Cliente pegarDados() {
		PCliente pc = new PCliente();
		return pc.pegarDados(this);
	}

	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public boolean isPedidoEmAberto() {
		return pedidoEmAberto;
	}


	public void setPedidoEmAberto(boolean pedidoEmAberto) {
		this.pedidoEmAberto = pedidoEmAberto;
	}


	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}


	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}


	public String getFiltroPedido() {
		return filtroPedido;
	}

	public void setFiltroPedido(String filtroPedido) {
		this.filtroPedido = filtroPedido;
	}

	public List<AvaliacaoPedido> getListaAvaliacoesPedidos() {
		return listaAvaliacoesPedidos;
	}

	public void setListaAvaliacoesPedidos(List<AvaliacaoPedido> listaAvaliacoesPedidos) {
		this.listaAvaliacoesPedidos = listaAvaliacoesPedidos;
	}

	public String getFiltroAvaliacao() {
		return filtroAvaliacao;
	}

	public void setFiltroAvaliacao(String filtroAvaliacao) {
		this.filtroAvaliacao = filtroAvaliacao;
	}

	
	
}
