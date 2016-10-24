
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;

@Entity
@Table
public class Endereco implements Serializable{
    
    @Id
    @Column
    @GeneratedValue
    private Long idEndereco;
    
    @Column(nullable = false, length = 100)
    private String logradouro;
    
    @Column(nullable = false, length = 7)
    private String numero;
    
    @Column(nullable = false, length = 9)
    private String cep;
    
    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bairro", nullable = false)
    private Bairro bairro;
    
    
    public Endereco(){}
    
    public Endereco(String logradouro, String numero, String cep){
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

   

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    
    
}
