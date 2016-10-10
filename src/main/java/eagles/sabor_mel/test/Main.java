
package eagles.sabor_mel.test;

import eagles.sabor_mel.model.*;
import eagles.sabor_mel.dao.*;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args){
        PaisDAO pDAO = new PaisDAO();
        Pais pais = new Pais("Brasil");
        pDAO.persist(pais);
        
        System.exit(0);
    }
}
