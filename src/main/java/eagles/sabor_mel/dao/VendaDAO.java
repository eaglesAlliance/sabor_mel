package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.Venda;
import java.util.List;

/**
 *
 * @author Tiago Lima Villalobos
 */
public class VendaDAO extends DAO<Venda>{
    public Venda getById(final Long id) {
        return entityManager.find(Venda.class, id);
    }
    
    public Venda getByPessoa(Long id){
        return (Venda) entityManager.createQuery("SELECT * FROM Venda WHERE pessoa = '"+id+"';").getSingleResult();
    }
    
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Venda venda = this.getById(id);
            super.remove(venda);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Venda> findAll() {
    	return entityManager
    		.createQuery("FROM Venda").getResultList();
    }
}
