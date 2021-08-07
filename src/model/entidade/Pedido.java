package model.entidade;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistencia.PPedido;

@Entity(name = "pedidos")
@DynamicUpdate(value = true) 
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private double valorTotal;
	
	@Column(length = 25)
	private String formaPagamento;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Temporal(TemporalType.TIME)
	private Date hora;
	
	@Column(length = 20)
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_restaurante", nullable = false, 
    foreignKey = @ForeignKey(name = "fk_Pedido_Restaurante"))
    private Restaurante restaurante;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = true, 
	foreignKey = @ForeignKey(name = "fk_Pedido_Cliente"))
	private Cliente cliente;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "pedido_produto",
	joinColumns = @JoinColumn(name = "id_pedido"),
	inverseJoinColumns = @JoinColumn(name = "id_produto"))
	private List<Produto> listaProdutos = new ArrayList<Produto>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_avaliacao", nullable = true, 
	foreignKey = @ForeignKey(name = "fk_Pedido_Avaliacao"))
	private AvaliacaoPedido avaliacaoPedido;
	
	@Transient
	public String msg;
	
	@Transient
	private ArrayList<Pedido> lista;

	public Pedido() {}

	
	public Pedido(long id) {
		this.id = id;
	}

	public boolean cadastrar() {
		PPedido pp = new PPedido();
		return pp.cadastrar(this);
	}
	
	public boolean editarDados() {
		PPedido pp = new PPedido();
		return pp.editarDados(this);
	}
	
	public boolean listarProdutos() {
		PPedido pp = new PPedido();
		return pp.listarProdutos(this);
	}
	
	public Pedido pegarDados() {
		PPedido pp = new PPedido();
		return pp.pegarDados(this);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return listaProdutos;
	}

	public void setProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public double getValorTotal() {
		
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Pedido> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Pedido> lista) {
		this.lista = lista;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}



	public AvaliacaoPedido getAvaliacaoPedido() {
		return avaliacaoPedido;
	}



	public void setAvaliacaoPedido(AvaliacaoPedido avaliacaoPedido) {
		this.avaliacaoPedido = avaliacaoPedido;
	}	
	
	
	
}
