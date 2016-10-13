
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.*;
import java.util.*;

public class BairroDAO extends DAO<Bairro>{
    public Bairro getById(final Long id) {
        return entityManager.find(Bairro.class, id);
    }
 
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Bairro bairro = this.getById(id);
            super.remove(bairro);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Bairro> findAll() {
    	return entityManager
    		.createQuery("FROM Bairro").getResultList();
    }
}
