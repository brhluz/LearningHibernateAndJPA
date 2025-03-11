package com.algaworksEstudo.ecommerce.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = null;

	public static EntityManager getEntity() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("Ecommerce-PU");
		}
	
		return emf.createEntityManager();
	}

}
