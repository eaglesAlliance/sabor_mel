
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
}
