
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.*;
import java.util.*;

public class FuncionarioDAO extends DAO<Funcionario>{
    public Funcionario getById(final Long id) {
        return entityManager.find(Funcionario.class, id);
    }
 
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Funcionario funcionario = this.getById(id);
            super.remove(funcionario);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Funcionario> findAll() {
    	return entityManager
    		.createQuery("FROM Funcionario").getResultList();
    }
}
