/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.control;

import eagles.sabor_mel.dao.CrediarioDAO;
import eagles.sabor_mel.dao.FuncionarioDAO;
import eagles.sabor_mel.dao.PessoaDAO;
import eagles.sabor_mel.dao.VendaDAO;
import eagles.sabor_mel.model.Crediario;
import eagles.sabor_mel.model.DateGenerator;
import eagles.sabor_mel.model.Funcionario;
import eagles.sabor_mel.model.ItemVenda;
import eagles.sabor_mel.model.Parcela;
import eagles.sabor_mel.model.Pessoa;
import eagles.sabor_mel.model.Venda;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dhiego.balthazar
 */
public class ControllerVendas {

    public boolean vender(Pessoa p, Funcionario f, String tipoVenda, List<Produto> produtos, double desconto) {
        
        VendaDAO daoVenda = new VendaDAO();
        Venda venda = new Venda();
        Calendar cal = Calendar.getInstance();
        cal.set(DateGenerator.getYear(), DateGenerator.getMonth(), DateGenerator.getDay());
        venda.setData(cal);
        venda.setTipoVenda(tipoVenda);
        venda.setDesconto(desconto);
    }

    private Double getValorTotal(int quantParcela, List<ItemVenda> itens, double desconto) {
        double soma = 0d;
        for (ItemVenda iv : itens) {
            soma += iv.getProduto().getValorUnitario();
        }
        if (desconto > 0) {
            soma *= desconto / 100;
        }
        return soma / quantParcela;
    }

}
