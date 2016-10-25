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

    public boolean cadastrar(Date data, String tipoVenda, String formaPagamento,
            Double desconto, Long idCliente, Long idFuncionario, List<ItemVenda> itens,
            int quantidadeParcela, int dia, int mes, int ano) {

        VendaDAO daoVenda = new VendaDAO();
        PessoaDAO daoPessoa = new PessoaDAO();
        Pessoa cliente = daoPessoa.getById(idCliente);
        FuncionarioDAO daoFuncionario = new FuncionarioDAO();
        Funcionario vendedor = daoFuncionario.getById(idFuncionario);

        Venda venda = new Venda();
        venda.setData(data);
        venda.setCliente(cliente);
        venda.setVendedor(vendedor);
        venda.setFormaPagamento(formaPagamento);
        venda.setTipoVenda(tipoVenda);
        if (tipoVenda.equals("CREDIARIO")) {
            System.out.printf("Foi possivel criar crediario? %b", criarCrediario(ano, mes, dia, venda, quantidadeParcela, itens, 0));
        }

        return daoVenda.persist(venda);
    }

    private boolean criarCrediario(int ano, int mes, int dia, Venda venda, int quantidadeParcela, List<ItemVenda> itens, double desconto) {
        Calendar cal = Calendar.getInstance();
        Parcela parcela = new Parcela();
        Crediario crediario = new Crediario();
        CrediarioDAO daoCrediario = new CrediarioDAO();
        cal.set(ano, mes, dia);
        crediario.setVenda(venda);
        crediario.setQuantidadeParcela(quantidadeParcela);
        double valorParcela = calcularParcela(quantidadeParcela, itens, desconto);
        for (int i = 0; i < quantidadeParcela; i++) {
            if (mes > 12) {
                mes = 1;
                ano += 1;
            }
            cal.set(ano, mes, dia);
            mes += 1;
            parcela.setDataVencimento(cal);
            parcela.setParcela(i + 1);
            parcela.setStatus("Não");
            parcela.setValorParcela(valorParcela);
            crediario.addParcela(parcela);
        }

        return daoCrediario.persist(crediario);
    }

    private Double calcularParcela(int quantParcela, List<ItemVenda> itens, double desconto) {
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
