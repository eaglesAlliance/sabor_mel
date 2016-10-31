package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.ItemVenda;
import java.util.List;
/**
 *
 * @author Tiago Lima Villalobos
 */
public class ItemVendaDAO extends DAO<ItemVenda>{
    public ItemVenda getById(final Long id) {
        return entityManager.find(ItemVenda.class, id);
    }
    
    public ItemVenda getByPessoa(Long id){
        return (ItemVenda) entityManager.createQuery("SELECT * FROM ItemVenda WHERE pessoa = '"+id+"';").getSingleResult();
    }
    
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            ItemVenda itemVenda = this.getById(id);
            super.remove(itemVenda);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<ItemVenda> findAll() {
    	return entityManager
    		.createQuery("FROM ItemVenda").getResultList();
    }
}
