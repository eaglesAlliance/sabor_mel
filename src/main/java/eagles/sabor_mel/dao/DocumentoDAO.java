package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.Documento;
import java.util.*;
/**
 *
 * @author etivideo
 */
public class DocumentoDAO extends DAO<Documento>{
    public Documento getById(final Long id) {
        return entityManager.find(Documento.class, id);
    }
    
    public Documento getByPessoa(Long id){
        return (Documento) entityManager.createQuery("SELECT * FROM Documento WHERE pessoa = '"+id+"';").getSingleResult();
    }
    
    public boolean removeById(final Long id) {
    	
    	boolean result = true;
    	
        try {
            Documento documento = this.getById(id);
            super.remove(documento);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
 
    @SuppressWarnings("unchecked")
	public List<Documento> findAll() {
    	return entityManager
    		.createQuery("FROM Documento").getResultList();
    }
}
