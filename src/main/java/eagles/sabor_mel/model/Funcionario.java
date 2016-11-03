
package eagles.sabor_mel.model;

import eagles.sabor_mel.model.enums.Sexo;
import javax.persistence.*;
import java.util.Calendar;

@Entity(name="Funcionario")
@PrimaryKeyJoinColumn(name="pessoa")
@Table
public class Funcionario extends Pessoa{
    

    
    @Column(nullable = false, length = 20)
    private String usuario;
    
    @Column(nullable = false, length = 100)
    private String senha;
    
    @Enumerated(EnumType.ORDINAL)
    private Acesso acesso;

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }
    

    public Funcionario(){}
    
    public Funcionario(String usuario, String senha, Acesso acesso, String nome, String email, Calendar dataNascimento, Sexo sexo){
        super(nome, email, dataNascimento, sexo);
        this.usuario = usuario;
        this.senha = senha;
        this.acesso = acesso;
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
      
}
