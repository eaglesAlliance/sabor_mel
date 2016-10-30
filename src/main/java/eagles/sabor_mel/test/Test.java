package eagles.sabor_mel.test;

import eagles.sabor_mel.dao.CrediarioDAO;
import eagles.sabor_mel.dao.ProdutoDAO;
import eagles.sabor_mel.dao.VendaDAO;
import eagles.sabor_mel.model.Crediario;
import eagles.sabor_mel.model.ItemVenda;
import eagles.sabor_mel.model.Parcela;
import eagles.sabor_mel.model.Produto;
import eagles.sabor_mel.model.Venda;
import java.util.Calendar;

public class Test {

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(2016, 11, 12);

        VendaDAO daoVenda = new VendaDAO();
        Venda vend = new Venda();
        vend.setData(cal);
        vend.setDesconto(0d);
        vend.setFormaPagamento("Crediario");
        vend.setTipoVenda("Prazo");

        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.getById(2l);
        ItemVenda iv = new ItemVenda();
        iv.setProduto(produto);
        iv.setQuantidade(2);
        vend.addItem(iv);

        CrediarioDAO credDAO = new CrediarioDAO();
        Crediario c = new Crediario();
        c.setQuantidadeParcela(4);
        c.setVenda(vend);

        
        int mes = 1;
        int dia = 10;
        int ano = 2017;
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, dia);
        for (int i = 0; i < 4; i++) {
            calendar.set(ano, mes+1, dia);
            Parcela par = new Parcela();
            par.setDataVencimento(calendar);
            par
        }
    }
}
