package eagles.sabor_mel.model;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Table
public class Pessoa implements Serializable{
    @Id
    @Column
    @GeneratedValue
    private Long idPessoa;
    
    @Column
    private String nome;
    
    @OneToOne
    @JoinColumn(name="documento")
    private Documento documento;
    
    @OneToOne
    @JoinColumn(name="endereco")
    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        endereco.setPessoa(this);
        this.endereco = endereco;
    }
    
    @Column
    private String email;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Calendar dataNascimento;
    
    @OneToMany(
        mappedBy = "pessoa", 
        targetEntity = Telefone.class, 
        fetch = FetchType.LAZY, 
        cascade = CascadeType.ALL)
    private final List<Telefone> telefones = new ArrayList<Telefone>();
    
    @OneToOne(mappedBy = "pessoa")
    private Funcionario funcionario;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public Pessoa(){}
    
    public Pessoa(String nome, String email, Calendar dataNascimento){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        documento.setPessoa(this);
        this.documento = documento;
    }
    
    public List<Telefone> getTelefones() {
		return telefones;
	}

    public void addTelefone(Telefone telefone) {
            telefone.setPessoa(this);
            this.telefones.add(telefone);
    }
    
}