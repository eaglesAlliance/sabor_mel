/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.Produto;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author a1655086
 */
public class ProdutoDAO extends DAO<Produto>{

    @Override
    public List findAll() {
        return entityManager.createQuery("FROM Produto").getResultList();
    }

    @Override
    public Produto getById(Long id) {
        return entityManager.find(Produto.class, id);
    }

    @Override
    public boolean removeById(Long id) {
        boolean result = true;
        try {
            Produto produto = getById(id);
            super.remove(produto);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
    
    public List<Produto> getByDescripion(String description){
        Query query = entityManager.createQuery("FROM Produto p WHERE p.descricao LIKE :description");
        query.setParameter("description", "%" + description + "%");
        return query.getResultList();
    }
    
}
