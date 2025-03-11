package com.ecommerce.test.IniciandoComJPA.JPQL;

import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworksEstudo.ecommerce.model.Categoria;
import com.algaworksEstudo.ecommerce.model.Pedido;
import com.ecommerce.test.EntityManagerTest;

public class TestandoQueryJPQL extends EntityManagerTest {

	@Test
	public void testeBuscaPedidoPorPedido() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT p");
		sql.append("FROM Pedido p");
		sql.append("JOIN p.itens i");
		sql.append("WHERE 1=1");
		sql.append("AND i.id.produtoId = 1");
		
		TypedQuery<Pedido> query = em.createQuery(sql.toString(), Pedido.class);
		
		List<Pedido> pedidos = query.getResultList();
		
		Assert.assertTrue(pedidos.size() == 2);
	}
	
	
	@Test
	public void testeBuscaPedidoPorPedidoEntreDatas() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT p");
		sql.append("FROM Pedido p");
		sql.append("WHERE 1=1");
		sql.append("AND p.dataCriacao > :data");
	
		
		
		TypedQuery<Pedido> query = em.createQuery(sql.toString(), Pedido.class);
		
		query.setParameter("data", LocalDateTime.now().minusDays(2));
		
		List<Pedido> pedidos = query.getResultList();
		
		Assert.assertTrue(pedidos.size() == 2);
	}
	
	@Test
	public void testeBuscaPedidoPorParteString() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT c");
		sql.append("FROM Cliente c");
		sql.append("WHERE 1=1");
		sql.append("AND UPPER(c.nome) LIKE concat('%', :nome, '%')");
			
		TypedQuery<Pedido> query = em.createQuery(sql.toString(), Pedido.class);
			
		query.setParameter("nome", "b".toLowerCase());
		
		List<Pedido> pedidos = query.getResultList();
		
		Assert.assertTrue(pedidos.size() == 2);
	}
	
	@Test
	public void testeBuscaPaginada() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT c");
		sql.append("FROM Categoria c");
		sql.append("WHERE 1=1");
		sql.append("ORDER BY c.nome");
		
		TypedQuery<Categoria> query = em.createQuery(sql.toString(), Categoria.class);
		
		//FIRSTRESULT = MAX_RESULT * (PAGINA-1)
		query.setFirstResult(0);
		query.setMaxResults(4);
		
		List<Categoria> categorias = query.getResultList();
		
		Assert.assertFalse(categorias.isEmpty());
		
		categorias.forEach(c -> System.out.println(c.getNome()));
	}
	
	
	
}
