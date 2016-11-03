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
        prod.setDescricao("Calça Jeans");
        prod.setQuantidade(10);
        prod.setValorUnitario(69.99);

        

        produtoDAO.persist(prod);

    }
}
