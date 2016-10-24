
package eagles.sabor_mel.test;

import eagles.sabor_mel.model.Estado;
import eagles.sabor_mel.dao.EstadoDAO;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        /*Cria um DAO de Estado*/
        EstadoDAO dao = new EstadoDAO();
        
        /*Cria um objeto do tipo Estado*/
        List<Estado> estados = new ArrayList<>();
        
        dao.persist(estado);
        */
        
        Calendar c = Calendar.getInstance();
        c.set(1988, 3, 21);
        
        EstadoDAO      estDAO = new EstadoDAO();
        CidadeDAO      cidDAO = new CidadeDAO();
        BairroDAO      baiDAO = new BairroDAO();
        EnderecoDAO    endDAO = new EnderecoDAO();
        PessoaDAO      pesDAO = new PessoaDAO();
        DocumentoDAO   docDAO = new DocumentoDAO();
        FuncionarioDAO funDAO = new FuncionarioDAO();
        TelefoneDAO    telDAO = new TelefoneDAO();
        
        Estado estado           = new Estado("SP");
        Cidade cidade           = new Cidade("Caraguatatuba");
        Bairro bairro           = new Bairro("Pontal de Santa Marina");
        Endereco endereco       = new Endereco("Rua do Contorno", "199", "11672-020");
        Pessoa pessoa           = new Pessoa("Tiago Lima", "tiago@gmail.com", c);
        Documento documento     = new Documento("340.124.578-37", "CPF");
        Funcionario funcionario = new Funcionario("login", "admin123", "A");
        Telefone telefone       = new Telefone("(12)", "3887-9006", "F");
        
        
        cidade.setEstado(estado);
        bairro.setCidade(cidade);
        endereco.setBairro(bairro);
        pessoa.setEndereco(endereco);
        pessoa.setDocumento(documento);
        pessoa.addTelefone(telefone);
        funcionario.setPessoa(pessoa);
        
        funDAO.persist(funcionario);
       
        
        
        
       /* Bairro bairro = new Bairro("Pontal", cidade);
        cidade.addBairro(bairro);
        
        Endereco endereco = new Endereco("RUA", "10", "00", bairro);
        bairro.addEndereco(endereco);
        
        Documento documento = new Documento("100", "CPF");
        
        Pessoa pessoa = new Pessoa("Tiago", "tiago@gmail.com", c, documento, endereco);
        documento.setPessoa(pessoa);
        endereco.setPessoa(pessoa);
        
        Funcionario funcionario = new Funcionario("login", "senha", "V", pessoa);
        
        
        Telefone telefone = new Telefone("12", "38879006", "fixo");
        telefone.setPessoa(pessoa);
        pessoa.addTelefone(telefone);*/
        
       
        /*baiDAO.persist(bairro);
        endDAO.persist(endereco);
        docDAO.persist(documento);
        telDAO.persist(telefone);
        pesDAO.persist(pessoa);
        funDAO.persist(funcionario);*/
            
            

        
        /*Persiste o Estado*/
        for(int i = 0; i < estados.size(); i++){
            dao.persist(estados.get(i));
        }
        
//        CidadeDAO dao = new CidadeDAO();
//        String nome = "Caraguatatuba";
//        
//        System.out.println("ID CIDADE: "+dao.getByNome(nome).getIdCidade());
        System.exit(0);
    }
}
