package eagles.sabor_mel.model;


import javax.persistence.*;
import java.io.*;

@Entity
@Table/*( uniqueConstraints ={ @UniqueConstraint( columnNames = "numero" ) } )*/
public class Documento implements Serializable{
    
    @Id
    @Column
    @GeneratedValue
    private Long idDocumento;
    
    @Column(length = 4)
    private String tipo;
    
    @Column(nullable = false, length = 18)
    private String numero;
    
    

    
    public Documento(){}
    
    public Documento(String numero){
        this.numero = numero;
    }
    
    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipoDocumento) {
        this.tipo = tipoDocumento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
}
