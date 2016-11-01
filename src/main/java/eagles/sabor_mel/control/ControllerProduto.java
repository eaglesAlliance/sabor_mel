/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.control;

import eagles.sabor_mel.dao.ProdutoDAO;
import eagles.sabor_mel.model.Produto;
import java.util.List;

/**
 *
 * @author dhiego.balthazar
 */
public class ControllerProduto {
    
    public boolean persistirProduto(String descricao, double valorUnitario, int quantidade){
        
        ProdutoDAO daoProduto = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        produto.setValorUnitario(valorUnitario);
        
        return daoProduto.persist(produto);
        
    }
    
    public String deleteProduto(Long id){
        ProdutoDAO daoProduto = new ProdutoDAO();
        Produto produto = daoProduto.getById(id);
        daoProduto.remove(produto);
        return produto.getDescricao();
    }
    
    public List<Produto> searchProduto(String descricao){
        ProdutoDAO daoProduto = new ProdutoDAO();
        List produtos = daoProduto.getByDescripion(descricao);
        return produtos;
    }
    
    public Produto searchProduto(Long id){
        ProdutoDAO daoProduto = new ProdutoDAO();
        Produto produto = daoProduto.getById(id);
        return produto;
    }
}
