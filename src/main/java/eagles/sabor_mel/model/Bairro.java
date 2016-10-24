
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Table
public class Bairro implements Serializable{
    @Id
    @Column
    @GeneratedValue
    private Long idBairro;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @OneToMany(
       mappedBy = "bairro", 
       targetEntity = Endereco.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private final List<Endereco> enderecos = new ArrayList<Endereco>();
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade", nullable = false)
    private Cidade cidade;
    
    public Bairro(){}
    
    public Bairro(String nome){
        this.nome = nome;
    }

    public Bairro(Bairro bairro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Long getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Long idBairro) {
        this.idBairro = idBairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Endereco> getEEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco) {
        endereco.setBairro(this);
        this.enderecos.add(endereco);
    }
}
