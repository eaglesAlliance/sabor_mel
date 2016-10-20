
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;
import java.util.Calendar;

@Entity(name="Funcionario")
@PrimaryKeyJoinColumn(name="pessoa")
//@Table
public class Funcionario extends Pessoa{
    
//    @Id
//    @GeneratedValue
//    @Column
//    private Long idFuncionario;
    
    @Column
    private String usuario;
    
    @Column
    private String senha;
    
    @Column
    private String tipo;
    
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name="pessoa", nullable=false)
//    private Pessoa pessoa;
    
    public Funcionario(){}
    
    public Funcionario(String usuario, String senha, String tipo, String nome, String email, Calendar dataNascimento){
        super(nome, email, dataNascimento);
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }

//    public Long getIdFuncionario() {
//        return idFuncionario;
//    }
//
//    public void setIdFuncionario(Long idFuncionario) {
//        this.idFuncionario = idFuncionario;
//    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
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

//    public Pessoa getPessoa() {
//        return pessoa;
//    }
//
//    public void setPessoa(Pessoa pessoa) {
//        this.pessoa = pessoa;
//    }
    
    
}
