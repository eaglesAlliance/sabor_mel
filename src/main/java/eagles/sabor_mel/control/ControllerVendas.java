/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.control;

import eagles.sabor_mel.dao.CrediarioDAO;
import eagles.sabor_mel.dao.FuncionarioDAO;
import eagles.sabor_mel.dao.PessoaDAO;
import eagles.sabor_mel.dao.ProdutoDAO;
import eagles.sabor_mel.dao.VendaDAO;
import eagles.sabor_mel.model.Crediario;
import eagles.sabor_mel.model.DateGenerator;
import eagles.sabor_mel.model.Funcionario;
import eagles.sabor_mel.model.ItemVenda;
import eagles.sabor_mel.model.Parcela;
import eagles.sabor_mel.model.Pessoa;
import eagles.sabor_mel.model.Produto;
import eagles.sabor_mel.model.Venda;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author dhiego.balthazar
 */
public class ControllerVendas {

    /*
     * @author Dhiego
     * Map<String,String> produtos: recebe o id e a quantidade de produtos escolhidos na View
     */
    
    public boolean vender(long idPessoa, long idFuncionario, String tipoVenda, long[] produtos, int[] quantidades, double desconto) {
        VendaDAO daoVenda = new VendaDAO();
        Venda venda = createVenda(idPessoa, idFuncionario, tipoVenda, desconto, produtos, quantidades);
        return daoVenda.persist(venda);
    }
    
    public boolean vender(long idPessoa, long idFuncionario, String tipoVenda, long[] produtos, int[] quantidades, double desconto, int quantidadeParcela, int dia, int mes, int ano) {
        boolean result = true;
        
        CrediarioDAO daoCrediario = new CrediarioDAO();
        VendaDAO daoVenda = new VendaDAO();
        Venda venda = createVenda(idPessoa, idFuncionario, tipoVenda, desconto, produtos, quantidades);
        double valorTotal = getValorTotal(venda.getItens(), desconto);
        
        Crediario crediario =  createCrediario(quantidadeParcela, dia, mes, ano, valorTotal, venda);
        
        daoVenda.persist(venda);
        daoCrediario.persist(crediario);
        
        return true;
        
    }

    private Crediario createCrediario(int quantidadeParcela, int dia, int mes, int ano, double valorTotal, Venda venda) {
        mes -= 1;
        Crediario crediario = new Crediario();
        crediario.setQuantidadeParcela(quantidadeParcela);
        crediario.setVenda(venda);

        double valorParcela = valorTotal / quantidadeParcela;
        
        for (int i = 0; i < quantidadeParcela; i++) {
            Calendar cal = Calendar.getInstance();
            Parcela parcela = new Parcela();
            cal.set(ano, mes, dia);
            parcela.setDataVencimento(cal);
            parcela.setParcela(i+1);
            parcela.setStatus("Não Pago");
            parcela.setValorParcela(valorParcela);
            mes += 1;
            crediario.addParcela(parcela);
        }
        
        return crediario;
    }

    private Venda createVenda(long idPessoa, long idFuncionario, String tipoVenda, double desconto, long[] produtos, int[] quantidades) {
        PessoaDAO daoPessoa = new PessoaDAO();
        FuncionarioDAO daoFuncionario = new FuncionarioDAO();
        ProdutoDAO daoProduto = new ProdutoDAO();

        
        Pessoa pessoa = null;
        Funcionario funcionario = null;

        Venda venda = new Venda();
        Calendar cal = Calendar.getInstance();
        cal.set(DateGenerator.getYear(), DateGenerator.getMonth(), DateGenerator.getDay());
        venda.setData(cal);
        venda.setTipoVenda(tipoVenda);
        venda.setDesconto(desconto);
        venda.setCliente(pessoa);
        venda.setVendedor(funcionario);
        for (int i = 0; i < produtos.length; i++) {
            Produto produto = daoProduto.getById(produtos[i]);
            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQuantidade(quantidades[i]);
            changeQuantityProduto(produto, quantidades[i]);
            venda.addItem(item);
            
        }
        return venda;
    }

    private Double getValorTotal(List<ItemVenda> itens, double desconto) {
        double valorTotal = 0d;
        for(ItemVenda iv : itens) {
            valorTotal += iv.getProduto().getValorUnitario();
        }
        if (desconto > 0) {
            valorTotal = valorTotal - (valorTotal * (desconto/100));
        }
        return valorTotal;
    }
    
    private boolean changeQuantityProduto(Produto p, int quantity){
        ProdutoDAO pDAO  = new ProdutoDAO();
        int newQuantity = p.getQuantidade() - quantity;
        p.setQuantidade(newQuantity);
        return pDAO.merge(p);
    }

}
