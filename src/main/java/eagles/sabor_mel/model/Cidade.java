
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
       targetEntity = Endereco.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private final List<Endereco> enderecos = new ArrayList<Endereco>();

    public Cidade(){}
    
    public Cidade(String cidade, Estado estado){
        this.cidade = cidade;
        this.estado = estado;
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
    
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
}
