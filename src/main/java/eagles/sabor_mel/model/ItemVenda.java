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
import javax.persistence.Table;

/**
 *
 * @author Tiago Lima Villalobos
 */
@Entity
@Table
public class ItemVenda implements Serializable{
    @Id
    @GeneratedValue
    @Column
    private Long idItemVenda;
    
    @Column
    private Integer quantidade;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "produto", nullable = false)
    private Produto produto;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "venda", nullable = false)
    private Venda venda;
    
    /*Construtores*/
    public ItemVenda() {}

    /*Getters*/
    public Long getIdItemVenda() {
        return idItemVenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Venda getVenda() {
        return venda;
    }
    
    /*Setters*/
    public void setIdItemVenda(Long idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
}
