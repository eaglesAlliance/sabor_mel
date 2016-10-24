package eagles.sabor_mel.control;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author etivideo
 */
public class HashSha {
    private String senha;
    
    
    public HashSha(String senha){
        this.senha = senha;
    }
    
    public String hashSenha() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = md.digest(this.senha.getBytes("UTF-8"));
        
        StringBuilder hexString = new StringBuilder();
        
        for(byte b : messageDigest){
            hexString.append(String.format("%02X", 0xFF & b));
        }
        
        return hexString.toString();
    }
}
