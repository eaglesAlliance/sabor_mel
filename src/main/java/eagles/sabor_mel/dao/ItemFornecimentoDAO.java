package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.ItemFornecimento;
import java.util.List;
/**
 *
 * @author Tiago Lima Villalobos
 */
public class ItemFornecimentoDAO extends DAO<ItemFornecimento>{
    public ItemFornecimento getById(final Long id) {
        return entityManager.find(ItemFornecimento.class, id);
    }
    
    public ItemFornecimento getByPessoa(Long id){
        return (ItemFornecimento) entityManager.createQuery("SELECT * FROM ItemFornecimento WHERE pessoa = '"+id+"';").getSingleResult();
    }
    
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            ItemFornecimento itemFornecimento = this.getById(id);
            super.remove(itemFornecimento);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<ItemFornecimento> findAll() {
    	return entityManager
    		.createQuery("FROM ItemFornecimento").getResultList();
    }
}
