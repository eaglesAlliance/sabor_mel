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

        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto prod = new Produto();
        prod.setDescricao("Camisa Regata Cores");
        prod.setQuantidade(10);
        prod.setValorUnitario(29.99);

        Calendar cal = Calendar.getInstance();
        cal.set(2016, 11, 12);

        VendaDAO daoVenda = new VendaDAO();
        Venda vend = new Venda();
        vend.setData(cal);
        vend.setDesconto(0d);
        vend.setFormaPagamento("Crediario");
        vend.setTipoVenda("Prazo");

        ItemVenda iv = new ItemVenda();
        iv.setProduto(prod);
        iv.setQuantidade(2);
        vend.addItem(iv);

        CrediarioDAO credDAO = new CrediarioDAO();
        Crediario c = new Crediario();
        c.setQuantidadeParcela(4);
        c.setVenda(vend);

        int mes = 0;
        int dia = 10;
        int ano = 2017;
        
        for (int i = 0; i < 4; i++) {           
            Parcela par = new Parcela();
            Calendar calendar = Calendar.getInstance();
            calendar.set(ano, mes, dia);
            par.setDataVencimento(calendar);
            par.setParcela(i + 1);
            par.setValorParcela(21.23);
            par.setStatus("NAO_PAGO");
            c.addParcela(par);
            mes += 1;
        }

        produtoDAO.persist(prod);
        daoVenda.persist(vend);
        credDAO.persist(c);

    }
}
