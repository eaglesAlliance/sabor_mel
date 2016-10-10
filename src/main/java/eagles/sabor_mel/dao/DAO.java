package eagles.sabor_mel.dao;

import javax.persistence.*;
import java.util.*;

public abstract class DAO<E> {

	protected EntityManager entityManager;
	 
    public DAO() {
        entityManager = getEntityManager();
    }
 
    private EntityManager getEntityManager() {
        
    	EntityManagerFactory factory = 
        		Persistence.createEntityManagerFactory("sabor_mel");
    	
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
 
        return entityManager;
    }
 
    public abstract List<E> findAll();
 
    public boolean persist(E object) {
    	
    	boolean result = true;
    	
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        	ex.printStackTrace();
            entityManager.getTransaction().rollback();
            result = false;
        }
        
        return result;
    }
 
    public boolean merge(E object) {
    	
    	boolean result = true;
    	
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            result = false;
        }
        
        return result;
    }
 
    public boolean remove(E object) {
    	
    	boolean result = true;
    	
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            result = false;
        }
        
        return result;
    }

	public abstract E getById(final Long id);
    
    public abstract boolean removeById(final Long id);
    
}
