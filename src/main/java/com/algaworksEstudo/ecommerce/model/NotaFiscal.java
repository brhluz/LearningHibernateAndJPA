package com.algaworksEstudo.ecommerce.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {
	
	@Id
	private Long id;
	
	@Column(name = "data_emissao")
	private Date dataEmissao;
	
	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "pedido_id")
//	@JoinTable(name = "pedido_nota_fiscal",
//				joinColumns = @JoinColumn(name = "pedido_id"),
//				inverseJoinColumns = @JoinColumn(name = "nota_fiscal_id"))
	private Pedido pedido;
	
	@Lob
	private byte[] xml;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedidoId() {
		return pedido;
	}

	public void setPedidoId(Pedido pedido) {
		this.pedido = pedido;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public byte[] getXml() {
		return xml;
	}

	public void setXml(byte[] xml) {
		this.xml = xml;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, dataEmissao, xml);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		NotaFiscal other = (NotaFiscal) obj;
		return id.equals(other.getId()) && dataEmissao.equals(other.getDataEmissao()) && xml.equals(other.getXml());
	}
	
	
	
}
