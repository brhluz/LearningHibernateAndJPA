package com.ecommerce.test.IniciandoComJPA;

import java.math.BigDecimal;

import org.junit.Test;

import com.algaworksEstudo.ecommerce.model.Produto;
import com.ecommerce.test.EntityManagerTest;

public class OperacoesComTransacaoTest extends EntityManagerTest {

	@Test
	public void abrirEFecharTransacao() {
		
		Produto p = new Produto();
		
//		p.setId(2l);
		p.setNome("Exemplo1");
		p.setPreco(BigDecimal.valueOf(50.0));
		p.setDescricao("TesteDescricao");
		
		em.getTransaction().begin();
//		em.persist(p);
//		Produto p2 = 
		em.merge(p);
//		em.remove(p);
//		p2.setNome("Exemplo30");
		em.getTransaction().commit();
		
		
	}

}
