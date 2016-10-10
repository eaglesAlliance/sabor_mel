package eagles.sabor_mel.model;


import javax.persistence.*;
import java.io.*;

@Entity
@Table
public class Documento implements Serializable{
    
    @Id
    @Column
    @GeneratedValue
    private Long idDocumento;
    
    @Column
    private String tipoDocumento;
    
    @Column
    private String numero;
    
    @OneToOne(mappedBy = "documento")
    private Pessoa pessoa;

    
    public Documento(){}
    
    public Documento(String tipoDocumento, String numero){
        this.tipoDocumento = tipoDocumento;
        this.numero = numero;
    }
    
    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }   
}
