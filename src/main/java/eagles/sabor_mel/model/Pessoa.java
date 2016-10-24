package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity(name="Pessoa")
@Inheritance(strategy=InheritanceType.JOINED)
@Table
public class Pessoa implements Serializable{
    
    @Id
    @Column
    @GeneratedValue
    private Long idPessoa;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco")
    private Endereco endereco;

    
    @Column(nullable = false, length = 45)
    private String email;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dataNascimento;
    
    @OneToMany(
        mappedBy = "pessoa", 
        targetEntity = Telefone.class, 
        fetch = FetchType.LAZY, 
        cascade = CascadeType.ALL)
    private final List<Telefone> telefones = new ArrayList<Telefone>();
    
    public Pessoa(){}
    
    public Pessoa(String nome, String email, Calendar dataNascimento){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        endereco.setPessoa(this);
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Telefone> getTelefones() {
		return telefones;
	}

    public void addTelefone(Telefone telefone) {
            telefone.setPessoa(this);
            this.telefones.add(telefone);
    }
    
}
