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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author dhiego.balthazar
 */

@Entity
@Table(name = "crediario")
public class Crediario implements Serializable{
    
    @Id
    @SequenceGenerator(name = "idCrediario", sequenceName = "idCrediario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCrediario")
    @Column(name = "idCrediario", nullable = false)
    private Long idCrediario;
    
    @Column(name = "quantidadeParcela", nullable = false)
    private Integer quantidadeParcela;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idVenda", nullable = false)
    private Venda venda;

    public Long getIdCrediario() {
        return idCrediario;
    }

    public void setIdCrediario(Long idCrediario) {
        this.idCrediario = idCrediario;
    }

    public Integer getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(Integer quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    
}
