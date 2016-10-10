
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Estado implements Serializable{
    
    @Id
    @Column
    @GeneratedValue
    private Long idEstado;
    
    @Column
    private String estado;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPais", nullable = false)
    private Pais pais;
    
    @OneToMany(
       mappedBy = "idCidade", 
       targetEntity = Cidade.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private final List<Cidade> cidades = new ArrayList<Cidade>();
    
    public Estado(){}
    
    public Estado(String estado, Pais pais){
        this.estado = estado;
        this.pais = pais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    public List<Cidade> getCidades() {
        return cidades;
    }

    public void addCidade(Cidade cidade) {
        this.cidades.add(cidade);
    }
}
