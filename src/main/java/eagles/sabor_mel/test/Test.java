package eagles.sabor_mel.test;

import eagles.sabor_mel.dao.ProdutoDAO;
import eagles.sabor_mel.model.Produto;

public class Test {

    public static void main(String[] args) {
        ProdutoDAO daoProduto = new ProdutoDAO();
        
        Produto produto = new Produto();
        produto.setDescricao("Camisa Regata Cores");
        produto.setQuantidade(10);
        produto.setValorUnitario(2.5);
        
        daoProduto.persist(produto);
    }
}
