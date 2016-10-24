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
    
    @Column(nullable = false, length = 100)
    private String nome;
    
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
    
    public Cidade(String nome){
        this.nome = nome;
    }
    
    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
