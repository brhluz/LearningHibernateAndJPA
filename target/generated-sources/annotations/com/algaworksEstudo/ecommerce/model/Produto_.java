package com.algaworksEstudo.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, BigDecimal> preco;
	public static volatile SingularAttribute<Produto, Estoque> estoque;
	public static volatile SingularAttribute<Produto, LocalDateTime> dataAtualizacao;
	public static volatile SingularAttribute<Produto, byte[]> fotoProduto;
	public static volatile ListAttribute<Produto, Categoria> categorias;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, LocalDateTime> dataCriacao;
	public static volatile ListAttribute<Produto, Atributo> atributos;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile ListAttribute<Produto, String> tags;

	public static final String PRECO = "preco";
	public static final String ESTOQUE = "estoque";
	public static final String DATA_ATUALIZACAO = "dataAtualizacao";
	public static final String FOTO_PRODUTO = "fotoProduto";
	public static final String CATEGORIAS = "categorias";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String DATA_CRIACAO = "dataCriacao";
	public static final String ATRIBUTOS = "atributos";
	public static final String DESCRICAO = "descricao";
	public static final String TAGS = "tags";

}

