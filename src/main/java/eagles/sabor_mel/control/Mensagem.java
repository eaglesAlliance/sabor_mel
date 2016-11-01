/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.control;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Lima Villalobos
 */
public class Mensagem implements Runnable{

    @Override
    public void run() {
        String mensagem = "";
        eagles.sabor_mel.view.Principal.mensagem.setBackground(Color.WHITE);
        
        switch (eagles.sabor_mel.view.Principal.acao) {
            case "edit":
                mensagem = "Alteração Realizada com Sucesso!";
                eagles.sabor_mel.view.Principal.mensagem.setForeground(Color.YELLOW);
                break;
            case "delete":
                mensagem = "Exclusão Realizada Com Sucesso!";
                eagles.sabor_mel.view.Principal.mensagem.setForeground(Color.RED);
                break;
            case "insert": 
                mensagem = "Inserção Realizada Com Sucesso!";
                eagles.sabor_mel.view.Principal.mensagem.setForeground(Color.GREEN);
                break;
            default:
                break;
        }
        
        eagles.sabor_mel.view.Principal.mensagem.setText(mensagem);
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        eagles.sabor_mel.view.Principal.mensagem.setText(null);
        Thread.interrupted();
    }
    
}
