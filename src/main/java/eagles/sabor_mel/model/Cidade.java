
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Cidade implements Serializable{
    @Id
    @Column
    @GeneratedValue
    private Long idCidade;
    
    @Column
    private String cidade;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "estado", nullable = false)
    private Estado estado;
    
    @OneToMany(
       mappedBy = "cidade", 
       targetEntity = Bairro.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private final List<Bairro> bairros = new ArrayList<Bairro>();

    public Cidade(){}
    
    public Cidade(String cidade){
        this.cidade = cidade;
    }
    
    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public List<Bairro> getBairros() {
        return bairros;
    }

    public void addBairro(Bairro bairro) {
        bairro.setCidade(this);
        this.bairros.add(bairro);
    }
}
