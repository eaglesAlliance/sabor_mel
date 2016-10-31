package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.Fornecimento;
import java.util.List;
/**
 *
 * @author Tiago Lima Villalobos
 */
public class FornecimentoDAO extends DAO<Fornecimento>{
    public Fornecimento getById(final Long id) {
        return entityManager.find(Fornecimento.class, id);
    }
    
    public Fornecimento getByPessoa(Long id){
        return (Fornecimento) entityManager.createQuery("SELECT * FROM Fornecimento WHERE pessoa = '"+id+"';").getSingleResult();
    }
    
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Fornecimento fornecimento = this.getById(id);
            super.remove(fornecimento);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Fornecimento> findAll() {
    	return entityManager
    		.createQuery("FROM Fornecimento").getResultList();
    }
}
