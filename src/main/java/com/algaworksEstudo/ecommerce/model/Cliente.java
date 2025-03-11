package com.algaworksEstudo.ecommerce.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.algaworksEstudo.ecommerce.model.enums.SexoCliente;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Transient
	private String primeiroNome;
	
	@Enumerated(EnumType.STRING)
	private SexoCliente sexo;
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	@ElementCollection
	@CollectionTable(name = "cliente_contato"
				, joinColumns = @JoinColumn(name = "cliente_id"))
	@MapKeyColumn(name = "tipo")
	@Column(name = "descricao")
	private Map<String, String> contatos;

	@PostLoad
	public void configurarPrimeiroNome() {
		if(nome != null && !nome.isEmpty()) {
			int index = nome.indexOf(" ");
			if(index > -1) {
				primeiroNome = nome.substring(0, index);
			}
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SexoCliente getSexo() {
		return sexo;
	}

	public void setSexo(SexoCliente sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Cliente other = (Cliente) obj;
		return id.equals(other.getId()) && nome.equalsIgnoreCase(other.getNome());
	}
	
	
	
}
