
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.*;
import java.util.*;

public class CidadeDAO extends DAO<Cidade>{
    public Cidade getById(final Long id) {
        return entityManager.find(Cidade.class, id);
    }
 
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Cidade cidade = this.getById(id);
            super.remove(cidade);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Cidade> findAll() {
    	return entityManager
    		.createQuery("FROM Cidade").getResultList();
    }
}
