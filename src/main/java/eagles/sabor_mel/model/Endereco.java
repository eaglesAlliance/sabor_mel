
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
    
    @Column
    private String logradouro;
    
    @Column
    private String numero;
    
    @Column
    private String cep;
    
    @OneToOne(mappedBy = "endereco")
    private Pessoa pessoa;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
}
