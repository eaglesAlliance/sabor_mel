
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Table
public class Pais implements Serializable{
    @Id
    @Column
    @GeneratedValue
    private Long idPais;
    
    @Column
    private String pais;
    
    @OneToMany(
       mappedBy = "pais", 
       targetEntity = Estado.class, 
       fetch = FetchType.LAZY, 
       cascade = CascadeType.ALL)
    private final List<Estado> estados = new ArrayList<Estado>();
    
    public Pais(){}
    
    public Pais(String pais){
        this.pais = pais;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public List<Estado> getEstados() {
        return estados;
    }

    public void addEstado(Estado estado) {
        this.estados.add(estado);
    }
}
