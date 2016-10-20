
package eagles.sabor_mel.test;

import eagles.sabor_mel.model.Estado;
import eagles.sabor_mel.dao.EstadoDAO;

public class Main {
    public static void main(String[] args){
        /*Cria um DAO de Estado*/
        EstadoDAO dao = new EstadoDAO();
        
        /*Cria um objeto do tipo Estado*/
        Estado estado = new Estado ("São Paulo", "SP");
        
        /*Persiste o Estado*/
        dao.persist(estado);
        
        
        System.exit(0);
    }
}
