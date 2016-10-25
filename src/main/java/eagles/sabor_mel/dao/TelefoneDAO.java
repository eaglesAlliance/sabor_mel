
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.*;
import java.util.*;

public class TelefoneDAO extends DAO<Telefone>{
    public Telefone getById(final Long id) {
        return entityManager.find(Telefone.class, id);
    }
 
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Telefone telefone = this.getById(id);
            super.remove(telefone);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Telefone> findAll() {
    	return entityManager
    		.createQuery("FROM Telefone").getResultList();
    }
        
    
	public Telefone getByDddTelefone(String ddd, String numero) {
    	return (Telefone) entityManager
    		.createQuery("FROM Telefone WHERE ddd = '"+ddd+"' AND numero = '"+numero+"'").getSingleResult();
    }
}
