
package eagles.sabor_mel.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity(name="Funcionario")
@PrimaryKeyJoinColumn(name="pessoa")
//@Table
public class Funcionario extends Pessoa{
    

    
    @Column(nullable = false, length = 20)
    private String usuario;
    
    @Column(nullable = false, length = 100)
    private String senha;
    
    @Column(nullable = false, length = 1)
    private String tipo;
    

    public Funcionario(){}
    
    public Funcionario(String usuario, String senha, String tipo, String nome, String email, Calendar dataNascimento){
        super(nome, email, dataNascimento);
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }

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
      
}
