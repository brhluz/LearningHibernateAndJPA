package com.algaworksEstudo.ecommerce.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estoque.class)
public abstract class Estoque_ {

	public static volatile SingularAttribute<Estoque, Produto> produtoId;
	public static volatile SingularAttribute<Estoque, Long> id;
	public static volatile SingularAttribute<Estoque, Integer> quantidade;

	public static final String PRODUTO_ID = "produtoId";
	public static final String ID = "id";
	public static final String QUANTIDADE = "quantidade";

}

