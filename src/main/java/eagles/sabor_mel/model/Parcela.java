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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Tiago Lima Villalobos
 */
@Entity
@Table
public class Parcela implements Serializable{
    @Id
    @GeneratedValue
    @Column
    private Long idParcela;
    
    @Column
    private Double valorParcela;
    
    @Column
    private boolean statusParcela;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "crediario", nullable = false)
    private Crediario crediario;
}
