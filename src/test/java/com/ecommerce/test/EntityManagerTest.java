package com.ecommerce.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class EntityManagerTest {

	protected static EntityManagerFactory entityManagerFactory;

	protected EntityManager em;

	@BeforeClass
	public static void setUpBeforeClass() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("Ecommerce-PU");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		entityManagerFactory.close();
	}
	
	@Before
	public void setUp() {
		em = entityManagerFactory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		em.close();
	}

}
