package com.algaworksEstudo.ecommerce.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.algaworksEstudo.ecommerce.model.enums.StatusPagamento;

@Entity
@Table(name = "pagamento_boleto")
@DiscriminatorValue("boleto")
public class PagamentoBoleto extends Pagamento{
	
	@Column(name = "codigo_barras")
	private String codigoBarras;

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigoBarras);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		PagamentoBoleto other = (PagamentoBoleto) obj;
		return codigoBarras.equalsIgnoreCase(other.getCodigoBarras());
	}
	
	
	
}
