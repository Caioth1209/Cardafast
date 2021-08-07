package model.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistencia.PRestaurante;

@Entity(name = "restaurantes")
@DynamicUpdate(value = true) 
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 30, unique = true)
	private String nome;
	
	@Column(length = 14, unique = true)
	private String cnpj;
	
	@Column(length = 100)
	private String endereco;
	
	@Column(precision = 10, scale = 2)
	private double faturamento;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> listaProdutos = new ArrayList<Produto>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<AvaliacaoPedido> listaAvaliacoes = new ArrayList<AvaliacaoPedido>();

	@Transient
	public String msg;
	
	@Transient
	private String filtroPedido = "Todos";
	
	@Transient
	private String filtroAvaliacao = "Todos";
	
	@Transient
	private double faturamento30d;
	
	@Transient
	private double faturamento15d;
	
	@Transient
	private double faturamento1d;
	
	@Transient
	private List<Restaurante> lista = new ArrayList<Restaurante>();
	
	public Restaurante() {}
	
	public Restaurante(long id) {
		this.id = id;
	}
	
	public Restaurante(String nome) {
		this.nome = nome;
	}
	
	public Restaurante(String nome, String cnpj, String endereco) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}

	public Restaurante(long id, String nome, String cnpj, String endereco, double faturamento) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.faturamento = faturamento;
	}
	
	public Restaurante(long id, String nome, String cnpj, String endereco) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}
	
	public boolean editarDados() {
		PRestaurante pr = new PRestaurante();
		return pr.editarDados(this);
	}
	
	public boolean localizarNomeExistente() {
		PRestaurante pr = new PRestaurante();
		return pr.localizarNomeExistente(this);
	}
	
	public boolean localizarCnpjExistente() {
		PRestaurante pr = new PRestaurante();
		return pr.localizarCnpjExistente(this);
	}
	
	public boolean localizarEnderecoExistente() {
		PRestaurante pr = new PRestaurante();
		return pr.localizarEnderecoExistente(this);
	}
	
	public boolean listarTodos() {
		PRestaurante pr = new PRestaurante();
		return pr.listarTodos(this);
	}
	
	public boolean listarPedidos() {
		PRestaurante pr = new PRestaurante();
		return pr.listarPedidos(this);
	}
	
	public boolean listarPedidos1d() {
		PRestaurante pr = new PRestaurante();
		return pr.listarPedidos1d(this);
	}
	
	public boolean listarPedidos15d() {
		PRestaurante pr = new PRestaurante();
		return pr.listarPedidos15d(this);
	}
	
	public boolean listarPedidos30d() {
		PRestaurante pr = new PRestaurante();
		return pr.listarPedidos30d(this);
	}
	
	public boolean listarAvaliacoes() {
		PRestaurante pr = new PRestaurante();
		return pr.listarAvaliacoes(this);
	}
	
	public boolean listarLikeNome() {
		PRestaurante pr = new PRestaurante();
		return pr.listarLikeNome(this);
	}
	
	public Restaurante pegarDados() {
		PRestaurante pr = new PRestaurante();
		return pr.pegarDados(this);
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getFaturamento() {
		
		return faturamento;
	}

	public void setFaturamento(double faturamento) {
		this.faturamento = faturamento;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<Restaurante> getLista() {
		return lista;
	}

	public void setLista(List<Restaurante> lista) {
		this.lista = lista;
	}

	public List<AvaliacaoPedido> getListaAvaliacoesPedidos() {
		return listaAvaliacoes;
	}

	public void setListaAvaliacoesPedidos(List<AvaliacaoPedido> listaAvaliacoes) {
		this.listaAvaliacoes = listaAvaliacoes;
	}


	public String getFiltroPedido() {
		return filtroPedido;
	}

	public void setFiltroPedido(String filtroPedido) {
		this.filtroPedido = filtroPedido;
	}


	public String getFiltroAvaliacao() {
		return filtroAvaliacao;
	}

	public void setFiltroAvaliacao(String filtroAvaliacao) {
		this.filtroAvaliacao = filtroAvaliacao;
	}


	public double getFaturamento30d() {
		return faturamento30d;
	}

	public void setFaturamento30d(double faturamento30d) {
		this.faturamento30d = faturamento30d;
	}

	public double getFaturamento15d() {
		return faturamento15d;
	}

	public void setFaturamento15d(double faturamento15d) {
		this.faturamento15d = faturamento15d;
	}

	public double getFaturamento1d() {
		return faturamento1d;
	}

	public void setFaturamento1d(double faturamento1d) {
		this.faturamento1d = faturamento1d;
	}
	
	
}
