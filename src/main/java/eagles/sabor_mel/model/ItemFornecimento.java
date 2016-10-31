package eagles.sabor_mel.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Tiago Lima Villalobos
 */
@Entity
@Table
public class ItemFornecimento implements Serializable{
    @Id
    @GeneratedValue
    @Column
    private Long idItemFornecimento;
    
    @Column
    private Integer quantidade;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fornecimento", nullable = false)
    private Fornecimento fornecimento;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="produto", nullable=false)
    private Produto produto;
    
    /*Construtores*/
    ItemFornecimento(){}
    
    ItemFornecimento(Integer quantidade){
        this.quantidade = quantidade;
    }
    
    /*Getters*/
    public Long getIdItemFornecimento(){
        return idItemFornecimento;
    }
    
    public Integer getQuantidade(){
        return quantidade;
    }
    
    public Fornecimento getFornecimento(){
        return fornecimento;
    }
    
    public Produto getProduto(){
        return produto;
    }
    
    /*Setters*/
    public void setIdFornecimento(Long idItemFornecimento){
        this.idItemFornecimento = idItemFornecimento;
    }
    
    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }
    
    public void setFornecimento(Fornecimento fornecimento){
        this.fornecimento = fornecimento;
    }
    
    public void setProduto(Produto produto){
        this.produto = produto;
    }
}

