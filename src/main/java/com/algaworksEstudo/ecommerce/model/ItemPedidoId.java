package com.algaworksEstudo.ecommerce.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;

import javax.persistence.Embeddable;


@Embeddable
public class ItemPedidoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long produtoId;
	
	private Long pedidoId;

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public ItemPedidoId(Long produtoId, Long pedidoId) {
		super();
		this.produtoId = produtoId;
		this.pedidoId = pedidoId;
	}
	
	public ItemPedidoId() {}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoId, produtoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;
		if (obj == null || getClass() != obj.getClass())	return false;
		ItemPedidoId other = (ItemPedidoId) obj;
		return produtoId.equals(other.getProdutoId()) && pedidoId.equals(other.pedidoId);
	}
	
	
	
}
