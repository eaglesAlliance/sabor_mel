
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
    private String usuario;
    
    @Column
    private String senha;
    
    @Column
    private String tipo;
    
    @OneToOne
    @JoinColumn(name="pessoa", nullable=false)
    private Pessoa pessoa;
    
    public Funcionario(){}
    
    public Funcionario(String usuario, String senha, String tipo){
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setusuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipoFuncionario) {
        this.tipo = tipoFuncionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        pessoa.setFuncionario(this);
        this.pessoa = pessoa;
    }
    
    
}
