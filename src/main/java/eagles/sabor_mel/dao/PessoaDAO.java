
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.Pessoa;
import java.util.*;

public class PessoaDAO extends DAO<Pessoa>{
    public Pessoa getById(final Long id) {
        return entityManager.find(Pessoa.class, id);
    }
 
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Pessoa pessoa = this.getById(id);
            super.remove(pessoa);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Pessoa> findAll() {
    	return entityManager
    		.createQuery("FROM Pessoa").getResultList();
    }
}
