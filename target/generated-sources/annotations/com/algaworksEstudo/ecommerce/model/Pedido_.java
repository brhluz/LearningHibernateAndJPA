package com.algaworksEstudo.ecommerce.model;

import com.algaworksEstudo.ecommerce.model.enums.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, Cliente> cliente;
	public static volatile ListAttribute<Pedido, ItemPedido> itens;
	public static volatile SingularAttribute<Pedido, BigDecimal> total;
	public static volatile SingularAttribute<Pedido, LocalDateTime> dataAtualizacao;
	public static volatile SingularAttribute<Pedido, NotaFiscal> notaFiscalId;
	public static volatile SingularAttribute<Pedido, EnderecoEntregaPedido> enderecoEntrega;
	public static volatile SingularAttribute<Pedido, LocalDateTime> dataConclusao;
	public static volatile SingularAttribute<Pedido, LocalDateTime> dataPedido;
	public static volatile SingularAttribute<Pedido, Long> id;
	public static volatile SingularAttribute<Pedido, Pagamento> pagamento;
	public static volatile SingularAttribute<Pedido, LocalDateTime> dataCriacao;
	public static volatile SingularAttribute<Pedido, StatusPedido> status;

	public static final String CLIENTE = "cliente";
	public static final String ITENS = "itens";
	public static final String TOTAL = "total";
	public static final String DATA_ATUALIZACAO = "dataAtualizacao";
	public static final String NOTA_FISCAL_ID = "notaFiscalId";
	public static final String ENDERECO_ENTREGA = "enderecoEntrega";
	public static final String DATA_CONCLUSAO = "dataConclusao";
	public static final String DATA_PEDIDO = "dataPedido";
	public static final String ID = "id";
	public static final String PAGAMENTO = "pagamento";
	public static final String DATA_CRIACAO = "dataCriacao";
	public static final String STATUS = "status";

}

