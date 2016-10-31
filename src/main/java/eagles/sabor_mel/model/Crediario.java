package eagles.sabor_mel.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Tiago Lima Villalobos
 */
@Entity
@Table
public class Crediario implements Serializable{
    @Id
    @GeneratedValue
    @Column
    private Long idCrediario;
    
    @Column
    private Integer quantidadeParcelas;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="venda", nullable=false)
    private Venda venda;
    
    @OneToMany(
       mappedBy = "crediario", 
       targetEntity = Parcela.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private List<Parcela> parcelas;
    
    /*Construtores*/
    Crediario(){}
    
    /*Getters*/
    public Long getIdCrediario() {
        return idCrediario;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public Venda getVenda() {
        return venda;
    }
    
    /*Setters*/
    public void setIdCrediario(Long idCrediario) {
        this.idCrediario = idCrediario;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
