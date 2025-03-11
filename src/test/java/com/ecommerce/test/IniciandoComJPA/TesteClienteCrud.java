package com.ecommerce.test.IniciandoComJPA;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.algaworksEstudo.ecommerce.model.Cliente;
import com.ecommerce.test.EntityManagerTest;
//CRUD
public class TesteClienteCrud extends EntityManagerTest{
	
	@Test
	public void create() {
		System.out.println("Criando objeto...");
		Cliente c = new Cliente();
//		c.setId(1L);
		c.setNome("Bruno");
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
		em.clear();
		Cliente clienteVerif = em.find(Cliente.class, c.getId());
		assertNotNull(clienteVerif);
	}
//	
//	@Test
//	public void FindById() {
//		System.out.println("Procurando objeto por id");
//		Cliente cliente = em.find(Cliente.class, 1L);
//	
//		assertNotNull(cliente);
//		assertEquals("TesteLazarento", cliente.getNome());
//	}
//	
//	@Test
//	public void update() {
//		System.out.println("Update do objeto...");
//		Cliente c = new Cliente();
//		c.setId(1L);
//		c.setNome("TesteLazarento");
//		
//		em.getTransaction().begin();
//		em.merge(c);
//		em.getTransaction().commit();
//		
//		em.clear();
//		Cliente clienteVerif = em.find(Cliente.class, c.getId());
//		assertEquals("TesteLazarento", clienteVerif.getNome());
//	}
//	
	@Test
	public void delete() {
		System.out.println("Deletando objeto...");
		Cliente c = em.find(Cliente.class, 1L);
//		em.detach(c); // teste detach (tira o contexto de persistência do objeto)
		
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		
		em.clear();
		Cliente clienteVerif = em.find(Cliente.class, c.getId());
		assertNull(clienteVerif);
	}
	
}
