package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;

@Entity
@Table
public class Telefone implements Serializable{
    
    @Id
    @Column
    @GeneratedValue
    private Long idTelefone;
    
    @Column
    private String ddd;
    
    @Column
    private String numero;
    
    @Column
    private String tipo;
 
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa", nullable = false)
    private Pessoa pessoa;

    public Telefone(){}
    
    public Telefone(String ddd, String numero){
        this.ddd = ddd;
        this.numero = numero;
    }
    
    
    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
