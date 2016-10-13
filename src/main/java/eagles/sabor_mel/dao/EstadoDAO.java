
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.*;
import java.util.*;

public class EstadoDAO extends DAO<Estado>{
    public Estado getById(final Long id) {
        return entityManager.find(Estado.class, id);
    }
 
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Estado estado = this.getById(id);
            super.remove(estado);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Estado> findAll() {
    	return entityManager
    		.createQuery("FROM Estado").getResultList();
    }
        
        
    public List<Estado> findByUf(String uf){
        return entityManager.createQuery("FROM Estado WHERE uf = "+uf).getResultList();
    }
}
