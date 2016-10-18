
package eagles.sabor_mel.test;

import javax.persistence.*;
import eagles.sabor_mel.model.*;
import eagles.sabor_mel.dao.*;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args){
        EstadoDAO dao = new EstadoDAO();
        
        Estado estado = new Estado("São Paulo", "SP");
        
        dao.persist(estado);
        
        
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
        
        
        
        String uf = "SP";
        
        List<Estado> list = estDAO.findByUf(uf);
        Cidade cidade           = new Cidade("Caraguatatuba");
        Bairro bairro           = new Bairro("Pontal de Santa Marina");
        Endereco endereco       = new Endereco("Rua do Contorno", "199", "11672-020");
        Pessoa pessoa           = new Pessoa("Tiago Lima", "tiago@gmail.com", c);
        Documento documento     = new Documento("340.124.578-37", "CPF");
        Funcionario funcionario = new Funcionario("login", "admin123", "A");
        Telefone telefone       = new Telefone("(12)", "3887-9006", "F");
        
        
        
        for(int i = 0; i < list.size(); i++){
//            Estado estado = list.get(i);
//            estDAO.merge(estado);
            
            list.get(i).addCidade(cidade);
            cidade.setEstado(list.get(i));
            
           
            //cidade.setEstado(estado);
            
            bairro.setCidade(cidade);
            endereco.setBairro(bairro);
            //endDAO.merge(endereco);
            endDAO.merge(endereco);
            //System.out.println(endDAO.getMax());
            pessoa.setEndereco(endDAO.getById(endDAO.getMax()));
            docDAO.persist(documento);
            pessoa.setDocumento(documento);
            pessoa.addTelefone(telefone);
            pesDAO.persist(pessoa);

            funcionario.setPessoa(pessoa);

            funDAO.persist(funcionario);
            //System.out.println(list.get(i).getCidades());
        }
        
        
       
        
        
        
        
        
        
        System.exit(0);
    }
}
