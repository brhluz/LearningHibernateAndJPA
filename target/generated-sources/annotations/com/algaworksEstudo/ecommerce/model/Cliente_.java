package com.algaworksEstudo.ecommerce.model;

import com.algaworksEstudo.ecommerce.model.enums.SexoCliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile MapAttribute<Cliente, String, String> contatos;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile ListAttribute<Cliente, Pedido> pedidos;
	public static volatile SingularAttribute<Cliente, SexoCliente> sexo;

	public static final String CONTATOS = "contatos";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String PEDIDOS = "pedidos";
	public static final String SEXO = "sexo";

}

