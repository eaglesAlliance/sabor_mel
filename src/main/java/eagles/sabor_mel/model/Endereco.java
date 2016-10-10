
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
    private String cep;
    
    @Column
    private String definicao;
    
    @Column
    private String nome;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa pessoa;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idCidade", nullable = false)
    private Cidade cidade;

    public Endereco(){}
    
    public Endereco(String definicao, String nome, String cep, Cidade cidade, Pessoa pessoa){
        this.definicao = definicao;
        this.nome = nome;
        this.cep = cep;
        this.cidade = cidade;
        this.pessoa = pessoa;
    }
    
    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    
}
