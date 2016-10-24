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
    
    @Column(nullable = false, length = 4)
    private String ddd;
    
    @Column(nullable = false, length = 10)
    private String numero;
    
    @Enumerated(EnumType.ORDINAL)
    private TipoTelefone tipo;
 
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa", nullable = false)
    private Pessoa pessoa;

    public Telefone(){}
    
    public Telefone(String ddd, String numero, TipoTelefone tipo){
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
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

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
