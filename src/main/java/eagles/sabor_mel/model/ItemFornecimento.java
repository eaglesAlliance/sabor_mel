/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author dhiego.balthazar
 */

@Entity
@Table(name = "itemfornecimento")
public class ItemFornecimento implements Serializable{
    
    @Id
    @SequenceGenerator(name = "idItemFornecimento", sequenceName = "idItemFornecimento_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idItemFornecimento")
    @Column(name = "idItemFornecimento", nullable = false)
    private Long idItemFornecimento;
    
    @Column(name = "quantidade")
    private Integer quantidade;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idFornecimento", nullable = false)
    private Fornecimento fornecimento;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduto", nullable = false)
    private Produto produto;

    public Long getIdItemFornecimento() {
        return idItemFornecimento;
    }

    public void setIdItemFornecimento(Long idItemFornecimento) {
        this.idItemFornecimento = idItemFornecimento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecimento getFornecimento() {
        return fornecimento;
    }

    public void setFornecimento(Fornecimento fornecimento) {
        this.fornecimento = fornecimento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
        
}
