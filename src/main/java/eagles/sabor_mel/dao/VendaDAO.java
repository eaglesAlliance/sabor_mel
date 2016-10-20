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

    public Month convert(int m) {
        Month mes;
        switch (m) {
            case 1:
                mes = Month.JANUARY;
                break;
            case 2:
                mes = Month.FEBRUARY;
                break;
            case 3:
                mes = Month.MARCH;
                break;
            case 4:
                mes = Month.APRIL;
                break;
            case 5:
                mes = Month.MAY;
                break;
            case 6:
                mes = Month.JUNE;
                break;
            case 7:
                mes = Month.JULY;
                break;
            case 8:
                mes = Month.AUGUST;
                break;
            case 9:
                mes = Month.SEPTEMBER;
                break;
            case 10:
                mes = Month.OCTOBER;
                break;
            case 11:
                mes = Month.NOVEMBER;
                break;
            case 12:
                mes = Month.DECEMBER;
                break;
            default:
                mes = null;
                break;
        }
        return mes;
    }

    public List<Venda> getByInterval(int ano, int mes, int dia) {
        Date start = java.sql.Date.valueOf(LocalDate.of(ano, convert(mes), dia));
    }
}
