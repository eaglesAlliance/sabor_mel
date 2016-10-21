
package eagles.sabor_mel.test;

import eagles.sabor_mel.dao.CidadeDAO;
import eagles.sabor_mel.model.Estado;
import eagles.sabor_mel.dao.EstadoDAO;
import eagles.sabor_mel.model.Cidade;

public class Main {
    public static void main(String[] args){
        /*Cria um DAO de Estado*/
        EstadoDAO dao = new EstadoDAO();
        
        /*Cria um objeto do tipo Estado*/
        Estado estado = new Estado ("São Paulo", "SP");
        
        /*Persiste o Estado*/
        dao.persist(estado);
        
//        CidadeDAO dao = new CidadeDAO();
//        String nome = "Caraguatatuba";
//        
//        System.out.println("ID CIDADE: "+dao.getByNome(nome).getIdCidade());
        System.exit(0);
    }
}
