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
    private String tipo;
    
    @Column
    private String numero;
    
    @OneToOne(mappedBy = "documento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Pessoa pessoa;

    
    public Documento(){}
    
    public Documento(String numero, String tipo){
        this.tipo = tipo;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }   
}
