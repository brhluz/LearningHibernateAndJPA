package com.algaworksEstudo.ecommerce.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NotaFiscal.class)
public abstract class NotaFiscal_ {

	public static volatile SingularAttribute<NotaFiscal, byte[]> xml;
	public static volatile SingularAttribute<NotaFiscal, Pedido> pedido;
	public static volatile SingularAttribute<NotaFiscal, Long> id;
	public static volatile SingularAttribute<NotaFiscal, Date> dataEmissao;

	public static final String XML = "xml";
	public static final String PEDIDO = "pedido";
	public static final String ID = "id";
	public static final String DATA_EMISSAO = "dataEmissao";

}

