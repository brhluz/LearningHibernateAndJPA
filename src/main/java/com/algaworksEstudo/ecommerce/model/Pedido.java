package com.algaworksEstudo.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.algaworksEstudo.ecommerce.model.enums.StatusPedido;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;
	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;
	
	@Column(name = "data_criacao", updatable = false)
	private LocalDateTime dataCriacao;
	@Column(name = "data_atualizacao", insertable = false)
	private LocalDateTime dataAtualizacao;
	
	@OneToOne(mappedBy = "pedido")
	private NotaFiscal notaFiscalId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
	private List<ItemPedido> itens;

	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;
	
	@Embedded
	private EnderecoEntregaPedido enderecoEntrega;
	
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_pedido")
	private StatusPedido status;

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Pagamento getPagamentoCartao() {
		return pagamento;
	}

	public void setPagamentoCartao(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public EnderecoEntregaPedido getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoEntregaPedido enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public NotaFiscal getNotaFiscalId() {
		return notaFiscalId;
	}

	public void setNotaFiscalId(NotaFiscal notaFiscalId) {
		this.notaFiscalId = notaFiscalId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, total, dataPedido, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Pedido other = (Pedido) obj;
		return id.equals(other.getId()) && dataPedido.equals(other.getDataPedido()) && dataConclusao.equals(other.getDataConclusao()) && total.equals(other.getTotal()) 
				&& status.equals(other.getStatus());
	}
	
	
	
}
