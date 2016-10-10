
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;

@Entity
@Table
public class Funcionario implements Serializable{
    
    @Id
    @GeneratedValue
    @Column
    private Long idFuncionario;
    
    @Column
    private String nomeUsuario;
    
    @Column
    private String senha;
    
    @Column
    private String tipoFuncionario;
    
    @OneToOne
    @JoinColumn(name="idPessoa")
    private Pessoa pessoa;
    
    public Funcionario(){}
    
    public Funcionario(String nomeUsuario, String senha, String tipoFuncionario, Pessoa pessoa){
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipoFuncionario = tipoFuncionario;
        this.pessoa = pessoa;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
}
