/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.dao;

import eagles.sabor_mel.model.Venda;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author dhiego.balthazar
 */
public class VendaDAO extends DAO<Venda> {

    @Override
    public List<Venda> findAll() {
        return entityManager.createQuery("FROM Venda").getResultList();
    }

    @Override
    public Venda getById(Long id) {
        return entityManager.find(Venda.class, id);
    }

    @Override
    public boolean removeById(Long id) {
        boolean resultado = true;
        try {
            Venda venda = getById(id);
            super.remove(venda);
        } catch (Exception e) {
            e.printStackTrace();
            resultado = false;
        }
        return resultado;
    }

    public List<Venda> getByInterval(Date start, Date end) {
        Query query = entityManager.createQuery("FROM Venda v WHERE v.dataVenda BETWEEN :startDate AND :endDate");
        query.setParameter("startDate", start);
        query.setParameter("endDate", end);
        return query.getResultList();
    }
}
