
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.*;
import java.util.*;
import javax.persistence.Query;

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
    
    public List<Funcionario> getByName(String nome){
        Query query = entityManager.createQuery("FROM Funcionario f WHERE f.nome LIKE :nomeFuncionario");
        query.setParameter("nomeFuncionario", "%" + nome + "%");
        return query.getResultList();
    }
 
    @SuppressWarnings("unchecked")
	public List<Funcionario> findAll() {
    	return entityManager
    		.createQuery("FROM Funcionario").getResultList();
    }
}
