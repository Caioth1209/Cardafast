package model.entidade;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity(name = "avaliacoes_pedidos")
@DynamicUpdate(value = true) 
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class AvaliacaoPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private int estrelas;
	
	@Column(length = 400)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = true, 
	foreignKey = @ForeignKey(name = "fk_AvaliacaoPedido_Cliente"))
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restaurante", nullable = true, 
	foreignKey = @ForeignKey(name = "fk_AvaliacaoPedido_Restaurante"))
	private Restaurante restaurante;
	
	@OneToOne(mappedBy = "avaliacaoPedido")
	private Pedido pedido;
	
	@Transient
	public String msg;
	
	public AvaliacaoPedido() {
		
	}

	public AvaliacaoPedido(long id, int estrelas, String descricao) {
		this.id = id;
		this.estrelas = estrelas;
		this.descricao = descricao;
	}



	public AvaliacaoPedido(int estrelas, String descricao) {
		this.estrelas = estrelas;
		this.descricao = descricao;
	}



	public AvaliacaoPedido(int estrelas, String descricao,Cliente cliente) {
		this.estrelas = estrelas;
		this.descricao = descricao;
		this.cliente = cliente;
	}
	
	

	public AvaliacaoPedido(int estrelas, String descricao, Restaurante restaurante, Cliente cliente) {
		this.estrelas = estrelas;
		this.descricao = descricao;
		this.restaurante = restaurante;
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(int estrelas) {
		this.estrelas = estrelas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}


	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	
	
}
