package model.entidade;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistencia.PProduto;
import model.persistencia.PRestaurante;

@Entity(name = "produtos")
@DynamicUpdate(value = true) 
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 60)
	private String nome;
	
	@Column(length = 250)
	private String descricao;
	
	@Column(precision = 3, scale = 2)
	private double preco; 
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name="id_restaurante", nullable = false, 
    foreignKey = @ForeignKey(name = "fk_Produto_Restaurante"))
    private Restaurante restaurante;

	@ManyToMany(mappedBy = "listaProdutos")
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();
	
	
	@Column
	private boolean ativo = true;
	
	@Transient
	public String msg;
	
	@Transient
	private ArrayList<Produto> lista = new ArrayList<Produto>();
	
	public Produto() {}
	
	public Produto(long id) {
		this.id = id;
	}
	
	public Produto(String nome, Restaurante restaurante) {
		this.nome = nome;
		this.restaurante = restaurante;
	}
	
	public Produto(Restaurante restaurante) {
		this.restaurante = restaurante;
	}


	public Produto(long id, String nome, String descricao, double preco, File foto, Restaurante restaurante,
			List<Pedido> listaPedidos) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.restaurante = restaurante;
		this.listaPedidos = listaPedidos;
	}
	
	public Produto(long id, String nome, String descricao, double preco, Restaurante restaurante) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.restaurante = restaurante;
	}
	
	public Produto(long id, String nome, String descricao, double preco, Restaurante restaurante, boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.restaurante = restaurante;
		this.ativo = ativo;
	}

	public Produto(long id, String nome, String descricao, double preco, File foto, Restaurante restaurante) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.restaurante = restaurante;
	}

	public Produto(String nome, String descricao, double preco, File foto, Restaurante restaurante) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.restaurante = restaurante;
	}
	
	public Produto(String nome, String descricao, double preco, Restaurante restaurante) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.restaurante = restaurante;
	}
	
	public Produto(String nome, String descricao, double preco, Restaurante restaurante, boolean ativo) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.restaurante = restaurante;
		this.ativo = ativo;
	}
	
	public Produto(long id, String nome, String descricao, double preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	public boolean cadastrar() {
		PProduto pp = new PProduto();
		return pp.cadastrar(this);
	}
	
	public boolean editar() {
		PProduto pp = new PProduto();
		return pp.editar(this);
	}
	
	public boolean listarTodos() {
		PProduto pp = new PProduto();
		return pp.listarTodos(this);
	}
	
	public boolean listarAtivos() {
		PProduto pp = new PProduto();
		return pp.listarAtivos(this);
	}
	
	public boolean deletar() {
		PProduto pp = new PProduto();
		return pp.deletar(this);
	}
	
	public Produto pegarDados() {
		PProduto pp = new PProduto();
		return pp.pegarDados(this);
	}
	
	public boolean listarLikeNome() {
		PProduto pp = new PProduto();
		return pp.listarLikeNome(this);
	}
	
	public boolean listarLikeNomeAtivos() {
		PProduto pp = new PProduto();
		return pp.listarLikeNomeAtivos(this);
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


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}


	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}


	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}


	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}


	public ArrayList<Produto> getLista() {
		return lista;
	}


	public void setLista(ArrayList<Produto> lista) {
		this.lista = lista;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
}
