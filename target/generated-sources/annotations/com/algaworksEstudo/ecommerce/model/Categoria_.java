package com.algaworksEstudo.ecommerce.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile ListAttribute<Categoria, Produto> produtos;
	public static volatile ListAttribute<Categoria, Categoria> categorias;
	public static volatile SingularAttribute<Categoria, String> nome;
	public static volatile SingularAttribute<Categoria, Long> id;
	public static volatile SingularAttribute<Categoria, Categoria> categoriaPaiId;

	public static final String PRODUTOS = "produtos";
	public static final String CATEGORIAS = "categorias";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String CATEGORIA_PAI_ID = "categoriaPaiId";

}

