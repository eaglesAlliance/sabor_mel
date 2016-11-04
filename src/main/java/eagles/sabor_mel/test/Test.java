package eagles.sabor_mel.test;

import eagles.sabor_mel.control.ControllerVendas;
import eagles.sabor_mel.dao.CrediarioDAO;
import eagles.sabor_mel.dao.VendaDAO;
import eagles.sabor_mel.model.Crediario;
import eagles.sabor_mel.model.Parcela;
import java.util.Calendar;

public class Test {

    public static void main(String[] args) {

        /*
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto prod = new Produto();
        prod.setDescricao("Calça Jeans");
        prod.setQuantidade(10);
        prod.setValorUnitario(69.99);
        produtoDAO.persist(prod);
        */
        
        long[] produtos = {1};
        int[] quantidades = {4};
        
        
       //ControllerVendas cv = new ControllerVendas();
       // cv.vender(0, 0, "Crediario", produtos, quantidades, 0);
        
        //cv.vender(0, 0, "CrediarioMesmo", produtos, quantidades, 0, 5, 3, 11, 2016);
        
        //VendaDAO vDAO = new VendaDAO();
        CrediarioDAO cdao = new CrediarioDAO();
        
        Crediario cred = cdao.getById(2l);
        
        for(Parcela par : cred.getParcelas()){
            System.out.println(par.getDataVencimento().get(Calendar.MONTH)+1);
        }
        
    }
}
