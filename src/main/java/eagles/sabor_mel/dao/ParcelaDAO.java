package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.Parcela;
import java.util.List;
/**
 *
 * @author Tiago Lima Villalobos
 */
public class ParcelaDAO extends DAO<Parcela>{
    public Parcela getById(final Long id) {
        return entityManager.find(Parcela.class, id);
    }
    
    public Parcela getByPessoa(Long id){
        return (Parcela) entityManager.createQuery("SELECT * FROM Parcela WHERE pessoa = '"+id+"';").getSingleResult();
    }
    
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Parcela parcela = this.getById(id);
            super.remove(parcela);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Parcela> findAll() {
    	return entityManager
    		.createQuery("FROM Parcela").getResultList();
    }
}
