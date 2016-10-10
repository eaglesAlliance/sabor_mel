
package eagles.sabor_mel.test;

import eagles.sabor_mel.model.*;
import eagles.sabor_mel.dao.*;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args){
        /*PessoaDAO dao = new PessoaDAO();
        DocumentoDAO doc = new DocumentoDAO();
        FuncionarioDAO func = new FuncionarioDAO();
        
        Documento documento = new Documento("CPF", "34012457837");
        doc.persist(documento);
        
        
        Calendar c = Calendar.getInstance();
        c.set(1988, 2, 12);
        Pessoa pessoa = new Pessoa("Paula", "paula@gmail.com", c, documento);
        
        documento.setPessoa(pessoa);
        
        dao.persist(pessoa);
        
        Funcionario funcionario = new Funcionario("guest", "123", "Admin", pessoa);
        func.persist(funcionario);*/
        
        /*Long id = 4L;
        
        dao.removeById(id);*/
        FuncionarioDAO dao = new FuncionarioDAO();
        List<Funcionario> list = dao.findAll();
        
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getPessoa().getNome());
        }
        
        System.exit(0);
    }
}
