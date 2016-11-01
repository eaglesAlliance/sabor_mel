package eagles.sabor_mel.model;


import eagles.sabor_mel.model.enums.TipoDocumento;
import javax.persistence.*;
import java.io.*;

@Entity
@Table/*( uniqueConstraints ={ @UniqueConstraint( columnNames = "numero" ) } )*/
public class Documento implements Serializable{
    
    @Id
    @Column
    @GeneratedValue
    private Long idDocumento;
    
    @Enumerated(EnumType.ORDINAL)
    private TipoDocumento tipo;
    
    @Column(nullable = false, length = 18)
    private String numero;
    
    

    
    public Documento(){}
    
    public Documento(String numero, TipoDocumento tipo){
        this.numero = numero;
        this.tipo = tipo;
    }
    
    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
}
