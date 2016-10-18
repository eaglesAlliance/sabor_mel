
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.*;
import java.util.*;

public class EnderecoDAO extends DAO<Endereco>{
    public Endereco getById(final Long id) {
        return entityManager.find(Endereco.class, id);
    }
 
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Endereco endereco = this.getById(id);
            super.remove(endereco);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Endereco> findAll() {
    	return entityManager
    		.createQuery("FROM Endereco").getResultList();
    }
        
    @SuppressWarnings("JPQLValidation")
        public Long getMax(){
            return (Long)entityManager.createQuery("SELECT MAX(idEndereco) FROM Endereco").getSingleResult();
        }
}
