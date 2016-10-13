
package eagles.sabor_mel.test;

import eagles.sabor_mel.model.*;
import eagles.sabor_mel.dao.*;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args){
        EstadoDAO dao = new EstadoDAO();
        CidadeDAO cid = new CidadeDAO();
        BairroDAO bai = new BairroDAO();
        /*Insert Estado and Cidade*/
        
       
        /*Estado estado = new Estado("São Paulo", "SP");
       
       
        Cidade cidade = new Cidade("Caraguatatuba", estado);
        estado.addCidade(cidade);
       
        dao.persist(estado);
        cid.persist(cidade);*/
       
       
       
        List<Cidade> cidades = cid.findAll();
       
        for(int i = 0; i < cidades.size(); i++){
            Bairro b1 = new Bairro("Barranco Alto", cidades.get(i));
            

            cidades.get(i).addBairro(b1);
            cid.persist(cidades.get(i));
            bai.persist(b1);
            System.out.println(cidades.get(i).getCidade());
        }
            
      
       
       
       
       System.exit(0);
    }
}
