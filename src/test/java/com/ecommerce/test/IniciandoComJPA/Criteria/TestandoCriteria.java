package com.ecommerce.test.IniciandoComJPA.Criteria;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworksEstudo.ecommerce.model.Categoria;
import com.algaworksEstudo.ecommerce.model.Cliente;
import com.algaworksEstudo.ecommerce.model.ItemPedido;
import com.algaworksEstudo.ecommerce.model.Pedido;
import com.algaworksEstudo.ecommerce.model.Produto;
import com.ecommerce.test.EntityManagerTest;

public class TestandoCriteria extends EntityManagerTest{
	
	@Test
	public void retornarTodosProdutos() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
		Root<Produto> root = cq.from(Produto.class);
		
		cq.select(root);
		
		TypedQuery<Produto> tp = em.createQuery(cq);
		List<Produto> produtos = tp.getResultList();
		Assert.assertFalse(produtos.isEmpty());
	}
	
	@Test
	public void clienteProdutoSumarizado() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<ItemPedido> root = cq.from(ItemPedido.class);
		Join<ItemPedido, Pedido> joinPedido = root.join("pedido");
		Join<Pedido, Cliente> joinCliente = joinPedido.join("cliente");
		
		cq.multiselect(
				joinCliente.get("nome"),
				cb.sum(root.get("precoProduto"))
		);
		
		cq.groupBy(joinCliente.get("id"));
		
		em.createQuery(cq).getResultList().forEach(arr -> System.out.println("Nome: " + arr[0] + "Soma Preco: " + arr[1]));;
	}
	
	@Test
	public void buscarPedidosComProdutoIgualA1() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> cq = cb.createQuery(Pedido.class);
		Root<Pedido> root = cq.from(Pedido.class);
		Join<Pedido, ItemPedido> joinItemPedido = root.join("itens");
		
		cq.select(root);
		
		cq.where(cb.equal(joinItemPedido.get("produto").get("id"), 1));
		
		em.createQuery(cq).getResultList();
	}
	
	@Test
    public void agruparResultadoComFuncoes() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<Pedido> root = cq.from(Pedido.class);
		
		Expression<Integer> anoCriacaoPedido = cb.function("year", Integer.class, root.get("dataCriacao"));
		Expression<Integer> mesCriacaoPedido = cb.function("month", Integer.class, root.get("dataCriacao"));
		Expression<String> nomeMesCriacaoPedido = cb.function("monthname", String.class, root.get("dataCriacao"));
		
		Expression<String> anoMesConcat = cb.concat(cb.concat(anoCriacaoPedido.as(String.class), "/"), nomeMesCriacaoPedido);
		
		Predicate teste;
		
		cq.multiselect(
					anoMesConcat,
					cb.sum(root.get("total"))
				);
		
		cq.groupBy(anoCriacaoPedido, mesCriacaoPedido);
		
		em.createQuery(cq).getResultList().forEach(arr -> System.out.println("Ano/Mês: " + arr[0] + ", Sum: " + arr[1]));
		
	}
	
	@Test
	public void pesquisarComSubqueryExercicio() {
		// todos clientes que ja fizeram mais de 2 pedidos
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
		Root<Cliente> root = cq.from(Cliente.class);
		
		cq.select(root);
		
		Subquery<Integer> sq = cq.subquery(Integer.class);
		Root<Pedido> subRoot = sq.from(Pedido.class);
		
		sq.select(cb.count(subRoot).as(Integer.class));
//		sq.where(cb.equal(root.get("id"), subRoot.get("cliente").get("id")));
		sq.where(cb.equal(root, subRoot.get("cliente")));
		
		cq.where(cb.greaterThan(sq, 2));
		
		em.createQuery(cq).getResultList().forEach(a -> System.out.println(a.getId() + "|" + a.getNome() + "|" + a.getSexo()));
		
	}
	
	@Test
	public void pesquisarComSubqueryIN() {
		// todos os pedidos que tem alguma categoria de produto igual a 2
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> cq = cb.createQuery(Pedido.class);
		Root<Pedido> root = cq.from(Pedido.class);
		
		cq.select(root);
		
		Subquery<Pedido> sq = cq.subquery(Pedido.class);
		Root<ItemPedido> subRoot = sq.from(ItemPedido.class);
		
		sq.select(subRoot.get("pedido"));
		sq.where(cb.equal(subRoot.get("produto").get("categorias"), 2));
		
		cq.where(root.in(sq));
		
		// OU POR CAUSA DA CHAVE COMPOSTA
		
		CriteriaBuilder cb2 = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> cq2 = cb.createQuery(Pedido.class);
		Root<Pedido> root2 = cq.from(Pedido.class);
		
		cq.select(root2);
		
		Subquery<Pedido> sq2 = cq.subquery(Pedido.class);
		Root<ItemPedido> subRoot2 = sq.from(ItemPedido.class);
		
		sq.select(subRoot2.get("id").get("pedidoId"));
		sq.where(cb.equal(subRoot2.get("produto").get("categorias"), 2));
		
		cq.where(root2.get("id").in(sq2));
		
		// OU
		
		CriteriaBuilder cb3 = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> cq3 = cb.createQuery(Pedido.class);
		Root<Pedido> root3 = cq.from(Pedido.class);
		
		cq.select(root3);
		
		Subquery<Long> sq3 = cq.subquery(Long.class);
		Root<ItemPedido> subRoot3 = sq.from(ItemPedido.class);
		
		sq3.select(cb.count(subRoot3));
		sq3.where(
			cb.and(
				cb.equal(subRoot3.get("produto").get("categorias"), 2),
				cb.equal(subRoot3.get("pedido"), root3)
				)
			);
		
		cq.where(cb.greaterThan(sq3, 0L));
		
		// OU
		
		CriteriaBuilder cb4 = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> cq4 = cb.createQuery(Pedido.class);
		Root<Pedido> root4 = cq.from(Pedido.class);
		
		cq4.select(root4);
		
		Subquery<Long> sq4 = cq.subquery(Long.class);
		Root<ItemPedido> subRoot4 = sq.from(ItemPedido.class);
		
		sq4.select(cb.literal(1L));
		sq4.where(
			cb.and(
				cb.equal(subRoot4.get("produto").get("categorias"), 2),
				cb.equal(subRoot4.get("pedido"), root4)
				)
			);
		
		cq4.where(cb.exists(sq4));
	}
	
	@Test
	public void exercicioExists() {
		// todos os produtos que tenham sido vendidos por um preço diferente do atual dele
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
		Root<Produto> root = cq.from(Produto.class);
		
		cq.select(root);
		
		Subquery<Integer> sq = cq.subquery(Integer.class);
		Root<ItemPedido> subRoot = sq.from(ItemPedido.class);
		sq.select(cb.literal(1));
		sq.where(cb.and(cb.equal(subRoot.get("produto").get("id"), root.get("id")), cb.notEqual(subRoot.get("precoProduto"), root.get("preco"))));
		
		cq.where(cb.exists(sq));
	}
	
	@Test
	public void exercicioAll() {
//		Descrição: "Todos os produtos que sempre foram vendidos pelo mesmo preço."
//		String jpql = "select distinct p from ItemPedido ip join ip.produto p where " +
//	              "ip.precoProduto = ALL " +
//	              "(select precoProduto from ItemPedido where produto = p and id <> ip.id)";
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemPedido> cq = cb.createQuery(ItemPedido.class);
		Root<ItemPedido> root = cq.from(ItemPedido.class);
		
		cq.select(root).distinct(true);
		
		Subquery<BigDecimal> sq = cq.subquery(BigDecimal.class);
		Root<ItemPedido> subRoot = sq.from(ItemPedido.class);
		sq.select(subRoot.get("precoProduto"));
		sq.where(cb.and(cb.equal(subRoot.get("produto"), root.get("produto")), cb.notEqual(subRoot, root)));
		
		cq.where(cb.equal(root.get("precoProduto"), cb.all(sq)));
		
		// OU
		
//		Descrição: "Todos os produtos que houveram uma venda pelo mesmo preço."
//		String jpql = "select distinct p from ItemPedido ip join ip.produto p where " +
//	              "ip.precoProduto = ANY " +
//	              "(select precoProduto from ItemPedido where produto = p and id <> ip.id)";
		
		CriteriaBuilder cb2 = em.getCriteriaBuilder();
		CriteriaQuery<ItemPedido> cq2 = cb2.createQuery(ItemPedido.class);
		Root<ItemPedido> root2 = cq2.from(ItemPedido.class);
		
		cq2.select(root2).distinct(true);
		
		Subquery<BigDecimal> sq2 = cq2.subquery(BigDecimal.class);
		Root<ItemPedido> subRoot2 = sq2.from(ItemPedido.class);
		sq2.select(subRoot2.get("precoProduto"));
		sq2.where(cb2.and(cb2.equal(subRoot2.get("produto"), root2.get("produto")), cb2.notEqual(subRoot2, root2)));
		
		cq2.where(cb2.equal(root2.get("precoProduto"), cb2.any(sq2)));
		
	}

	@Test
	public void CriteriaUpdateeeee() {
		
//		"UPDATE PRODUTO P SET P.PRECO = P.PRECO + (P.PRECO * 0.1)"
//		+ "WHERE EXISTS(SELECT 1 FROM P.CATEGORIAS C2 WHERE C2 = 2)"
		
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Produto> cp = cb.createCriteriaUpdate(Produto.class);
		Root<Produto> root = cp.from(Produto.class);
		
		Path<BigDecimal> precoPath = root.get("preco");

	    cp.set(precoPath, cb.prod(root.get("preco"), cb.literal(new BigDecimal("1.1"))));
		
		Subquery<Integer> sq = cp.subquery(Integer.class);
		Root<Produto> subRoot = sq.correlate(root); // isso aqui é para não usar a mesma tabela produto na consulta
		Join<Produto, Categoria> joinCategoria = subRoot.join("categorias");
		sq.select(cb.literal(1));
		sq.where(cb.equal(joinCategoria.get("id"), 2));
		
		cp.where(cb.exists(sq));
		
		em.createQuery(cp).executeUpdate();
		
		em.getTransaction().commit();

	}
	@Test
	public void CriteriaDeleteeeee() {
		
//		"DELETE PRODUTO P WHERE P.ID BETWEEN 8 AND 12"
		
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Produto> cd = cb.createCriteriaDelete(Produto.class);
		Root<Produto> root = cd.from(Produto.class);
		
		cd.where(cb.between(root.get("id"), 8, 12));
		
		em.createQuery(cd).executeUpdate();
		
		em.getTransaction().commit();
	}
}
