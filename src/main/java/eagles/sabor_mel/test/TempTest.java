/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.test;

import eagles.sabor_mel.dao.EstadoDAO;
import eagles.sabor_mel.dao.PessoaDAO;
import eagles.sabor_mel.model.Estado;
import eagles.sabor_mel.model.Pessoa;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author etivideo
 */
public class TempTest {
    public static void main(String[] args){
       
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> pessoas = dao.findAll();
        
        for(int i = 0; i < pessoas.size(); i++){
            System.out.println("NOME "+pessoas.get(i).getNome());
            System.out.println("Usuário "+pessoas.get(i).getFuncionario().getUsuario());
            
            String acesso = pessoas.get(i).getFuncionario().getTipo();
            switch(acesso){
                case("V"):
                    System.out.println("Acesso: Vendedor");
                    break;
                case("A"):
                    System.out.println("Acesso: Administrador");
                    break;
            }
            
        }
        
        System.exit(0);
    }
}
