package com.ecommerce.test.IniciandoComJPA;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.algaworksEstudo.ecommerce.model.Categoria;
import com.ecommerce.test.EntityManagerTest;

public class TesteAutoRelacionamento extends EntityManagerTest{

	@Test
	public void test() {

		Categoria categoriaPai = new Categoria();
		
		categoriaPai.setNome("Eletrônicos");
		
		Categoria categoria = new Categoria();
		categoria.setNome("Celulares");
		categoria.setCategoriaPaiId(categoriaPai);
		
		em.getTransaction().begin();
		em.persist(categoriaPai);
		em.persist(categoria);
		em.getTransaction().commit();
	}

}
