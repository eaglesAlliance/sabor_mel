package eagles.sabor_mel.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tiago Lima Villalobos
 */
@Entity
@Table
public class Fornecimento implements Serializable{
    @Id
    @GeneratedValue
    @Column
    private Long idFornecimento;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dataFornecimento;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pessoa", nullable = false)
    private Pessoa pessoa;
    
    @OneToMany(
       mappedBy = "fornecimento", 
       targetEntity = ItemFornecimento.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private List<ItemFornecimento> itens;
    
    Fornecimento(){}
    
    Fornecimento(Calendar dataFornecimento){
        this.dataFornecimento = dataFornecimento;
    }
    
    /*Getters*/
    public Long getIdFornecimento(){
        return idFornecimento;
    }
    
    public Calendar getDataFornecimento(){
        return this.dataFornecimento;
    }
    
    public Pessoa getPessoa(){
        return pessoa;
    }
    
    public List<ItemFornecimento> getItens(){
        return itens;
    }
    
    /*Setters*/
    public void setIdFornecimento(Long idFornecimento){
        this.idFornecimento = idFornecimento;
    }
    
    public void setDataFornecimento(Calendar dataFornecimento){
        this.dataFornecimento = dataFornecimento;
    }
    
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }
    
    public void setItens(List<ItemFornecimento> itens){
        this.itens = itens;
    }
    
    /*Add*/
    public void addItem(ItemFornecimento item){
        this.itens.add(item);
    }
}
