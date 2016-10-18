/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dhiego.balthazar
 */

@Entity
@Table(name = "fornecimento")
public class Fornecimento implements Serializable{
    
    @Id
    @SequenceGenerator(name = "idFornecimento", sequenceName = "idFornecimento_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idFornecimento")
    @Column(name = "idFornecimento", nullable = false)
    private Long idFornecimento;
    
    @Column(name = "dataFornecimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dataFornecimento;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idFornecedor", nullable = false)
    private Fornecedor fornecedor;

    public Long getIdFornecimento() {
        return idFornecimento;
    }

    public void setIdFornecimento(Long idFornecimento) {
        this.idFornecimento = idFornecimento;
    }

    public Calendar getDataFornecimento() {
        return dataFornecimento;
    }

    public void setDataFornecimento(Calendar dataFornecimento) {
        this.dataFornecimento = dataFornecimento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
}
