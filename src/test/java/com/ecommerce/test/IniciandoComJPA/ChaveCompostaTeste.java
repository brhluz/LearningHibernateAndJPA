package com.ecommerce.test.IniciandoComJPA;

import java.time.LocalDateTime;

import org.junit.Test;

import com.algaworksEstudo.ecommerce.model.Cliente;
import com.algaworksEstudo.ecommerce.model.ItemPedido;
import com.algaworksEstudo.ecommerce.model.Pedido;
import com.algaworksEstudo.ecommerce.model.Produto;
import com.algaworksEstudo.ecommerce.model.enums.StatusPedido;
import com.ecommerce.test.EntityManagerTest;

public class ChaveCompostaTeste extends EntityManagerTest {

	@Test
	public void salvarItem() {
		
		em.getTransaction().begin();
		
		Cliente cliente = em.find(Cliente.class, 1l);
		Produto produto = em.find(Produto.class, 1l);
		
		Pedido pedido = new Pedido();
		
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(produto.getPreco());
		
		em.persist(pedido);
		
		ItemPedido itemPedido = new ItemPedido();
//		itemPedido.setProdutoId(produto.getId());
//		itemPedido.setPedidoId(pedido.getId());
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(produto.getEstoque().getQuantidade());
		
		em.persist(itemPedido);
		
		em.getTransaction().commit();
		
		em.clear();
		
	}
	
}
