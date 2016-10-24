
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Estado implements Serializable{
    
    @Id
    @Column
    @GeneratedValue
    private Long idEstado;
    
    @Column(nullable = false, length = 45, unique = true)
    private String nome;
    
    @Column(nullable = false, length = 2, unique = true)
    private String uf;
    
    @OneToMany(
       mappedBy = "estado", 
       targetEntity = Cidade.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private final List<Cidade> cidades = new ArrayList<Cidade>();
    
    public Estado(){}
    
    public Estado(String nome, String uf){
        this.nome = nome;
        this.uf = uf;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public List<Cidade> getCidades() {
        return cidades;
    }

    public void addCidade(Cidade cidade) {
        cidade.setEstado(this);
        this.cidades.add(cidade);
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
}
