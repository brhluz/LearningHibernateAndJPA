package com.algaworksEstudo.ecommerce.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "categoria_pai_id")
	private Categoria categoriaPaiId;
	
	@OneToMany(mappedBy = "categoriaPaiId")
	private List<Categoria> categorias;
	
	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos;

	public Categoria getCategoriaPaiId() {
		return categoriaPaiId;
	}

	public void setCategoriaPaiId(Categoria categoriaPaiId) {
		this.categoriaPaiId = categoriaPaiId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, categoriaPaiId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Categoria other = (Categoria) obj;
		return id.equals(other.getId()) && nome.equalsIgnoreCase(other.getNome());
	}
	
	
	
}
