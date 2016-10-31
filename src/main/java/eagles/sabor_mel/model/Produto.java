package eagles.sabor_mel.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Tiago Lima Villalobos
 */
@Entity
@Table
public class Produto implements Serializable{
    @Id
    @GeneratedValue
    @Column
    private Long idProduto;
    
    @Column
    private String nome;
    
    @Column
    private String descricao;
    
    @Column
    private Integer quantidade;
    
    @Column
    private Double valorUnitario;
    
    @Column
    private String imagem;
    
    @OneToMany(
       mappedBy = "produto", 
       targetEntity = ItemVenda.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private List<ItemVenda> itens;
    
    /*Construtores*/
    Produto() {}
    
    Produto(String nome, String descricao, Integer quantidade, Double valorUnitario, String imagem){
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.imagem = imagem;
    }
    
    /*Getters*/

    public Long getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public String getImagem() {
        return imagem;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }
     
    /*Setters*/
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
}
