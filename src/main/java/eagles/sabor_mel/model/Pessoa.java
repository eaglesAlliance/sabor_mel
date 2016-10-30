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
    
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="endereco", nullable=false)
    private Endereco endereco;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="documento", nullable=false)
    private Documento documento;

    @Column(nullable = false, length = 100)
    private String email;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dataNascimento;
    
    @OneToMany(
        mappedBy = "pessoa", 
        targetEntity = Telefone.class, 
        fetch = FetchType.LAZY, 
        cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<Telefone>();
    
    public Pessoa(){}
    
    public Pessoa(String nome, String email, Calendar dataNascimento, Sexo sexo){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public void addTelefone(Telefone telefone) {
        telefone.setPessoa(this);
        this.telefones.add(telefone);
    }
    
    public void setTelefone(List telefones) {
            
        this.telefones = telefones;
        for(int i = 0; i < this.telefones.size(); i++){
            this.telefones.get(i).setPessoa(this);
        }
    }
    
}