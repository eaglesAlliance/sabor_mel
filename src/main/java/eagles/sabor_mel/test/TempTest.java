/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.test;

import eagles.sabor_mel.dao.*;
import eagles.sabor_mel.model.*;
import java.util.*;


/**
 *
 * @author etivideo
 */
public class TempTest {
    public static void main(String[] args){
       
//        FuncionarioDAO dao = new FuncionarioDAO();
//        Funcionario funcionario = dao.getById(15l);
//        dao.remove(funcionario);

//        FuncionarioDAO dao = new FuncionarioDAO();
        EstadoDAO est = new EstadoDAO();
        Estado estado = new Estado ("São Paulo", "SP");
        est.persist(estado);
        //EstadoDAO dao = new EstadoDAO();
        
//        Calendar c = Calendar.getInstance();
//        c.set(1988, 3, 21);
        
//        Estado estado = new Estado ("São Paulo", "SP");
//        Cidade      cidade      = new Cidade     ("Caraguatatuba");
//        Bairro      bairro      = new Bairro     ("Pontal Santa Marina");
//        Endereco    endereco    = new Endereco   ("Rua do Contorno", "199", "11672-020");
//        Telefone    telefone    = new Telefone   ("(12)", "38879006", "F");
//        Pessoa      pessoa      = new Pessoa     ("Tiago Lima Villalobos", "tiago@gmail.com", c);
//        Documento   documento   = new Documento  ("340.124.578.37", "CPF");
//        Funcionario funcionario = new Funcionario("tiago", "123", "V");
        
//        estado.addCidade(cidade);
//        cidade.addBairro(bairro);
//        bairro.addEndereco(endereco);
//        pessoa.setEndereco(endereco);
//        pessoa.addTelefone(telefone);
//        pessoa.setDocumento(documento);
//        funcionario.setPessoa(pessoa);
//        
//        dao.persist(funcionario);
        
//        List<Funcionario> funcionarios = dao.findAll();
//        for(int i = 0; i < funcionarios.size(); i++){
//            funcionarios.get(i).getPessoa().setNome("Tiago Lima");
//            dao.merge(funcionarios.get(i));
//        }
        

//        FuncionarioDAO dao = new FuncionarioDAO();
//        EstadoDAO est = new EstadoDAO();
//        List<Estado> list = est.findByUf("SP");
//        
//        for(int i = 0; i < list.size(); i++){
//            list.get(i).addCidade(cidade);
//            cidade.addBairro(bairro);
//            bairro.addEndereco(endereco);
//            pessoa.setEndereco(endereco);
//            pessoa.addTelefone(telefone);
//            pessoa.setDocumento(documento);
//            funcionario.setPessoa(pessoa);
//
//            dao.merge(funcionario);
//        }

        System.exit(0);
    }
}
