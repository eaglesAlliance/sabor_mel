
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.*;
import java.util.*;

public class PaisDAO extends DAO<Pais>{
    public Pais getById(final Long id) {
        return entityManager.find(Pais.class, id);
    }
 
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Pais pais = this.getById(id);
            super.remove(pais);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Pais> findAll() {
    	return entityManager
    		.createQuery("FROM Pais").getResultList();
    }
}
