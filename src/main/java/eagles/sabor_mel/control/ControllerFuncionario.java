/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.control;

import eagles.sabor_mel.dao.DAO;
import eagles.sabor_mel.dao.FuncionarioDAO;
import eagles.sabor_mel.dao.PessoaDAO;
import eagles.sabor_mel.model.Bairro;
import eagles.sabor_mel.model.Cidade;
import eagles.sabor_mel.model.Documento;
import eagles.sabor_mel.model.Endereco;
import eagles.sabor_mel.model.Estado;
import eagles.sabor_mel.model.Funcionario;
import eagles.sabor_mel.model.Pessoa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author dhiego.balthazar
 */
public class ControllerFuncionario {
    
    /*
     * @author dhiego
     * This function calls VendaDAO and CrediarioDAO to persist a sell.
     *
     */

    public boolean cadastrar(String usuario, String senha, String tipoFuncionario,
            String nome, String email, String dataNascimento,
            String numeroDocumento, String tipoDocumento,
            String logradouro, String numeroEndereco, String cep,
            String nomeBairro,
            String nomeCidade,
            String nomeEstado, String uf) {
        
        //Funcionario funcionario = new Funcionario(usuario, senha, tipoFuncionario);
        //Pessoa pessoa = new Pessoa(nome, email, transformData(dataNascimento));
       // Documento documento = new Documento(numeroDocumento, tipoDocumento);
        Endereco endereco = new Endereco();
        Bairro bairro = new Bairro(nomeBairro);
        Cidade cidade = new Cidade(nomeCidade);
       // Estado estado = new Estado(uf);
        
       // cidade.setEstado(estado);
        bairro.setCidade(cidade);
        endereco.setBairro(bairro);
       // pessoa.setEndereco(endereco);
       // pessoa.setDocumento(documento);
        
        PessoaDAO dao = new PessoaDAO();
        return false; //dao.persist(pessoa);
    }

    private Calendar transformData(String data) {
        Calendar cal = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            cal = Calendar.getInstance();
            cal.setTime(sdf.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
    
    public List<Funcionario> findByName(String nome){        
        FuncionarioDAO dao = new FuncionarioDAO();
        return null; //dao.getByName(nome);
    }

}
