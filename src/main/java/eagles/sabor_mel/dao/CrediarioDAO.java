/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.Crediario;
import java.util.List;

/**
 *
 * @author dhiego.balthazar
 */
public class CrediarioDAO extends DAO<Crediario>{

    @Override
    public List<Crediario> findAll() {
        return entityManager.createQuery("FROM Crediario").getResultList();
    }

    @Override
    public Crediario getById(Long id) {
        return entityManager.find(Crediario.class, id);
    }

    @Override
    public boolean removeById(Long id) {
        boolean result = true;
        try{
            Crediario crediario = getById(id);
            super.remove(crediario);
        }catch(Exception e){
            e.printStackTrace();
            result = false;
        }
        
        return result;
    }
    
}
