package com.algaworksEstudo.ecommerce.model;

import javax.persistence.Embeddable;

@Embeddable
public class Atributo {

	private String nome;
	
	private Integer valor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
}
