


package controller;

import DAO.FestivalDAO;
import DAO.KorisnikDAO;
import beans.Festival;
import beans.Korisnik;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class Controller implements Serializable {
    String msgChangingPass = null;
    private static final String encryptionKey = "ABCDEFGHIJKLMNOP";
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";

    public String getMsgChangingPass() {
        return msgChangingPass;
    }

    public void setMsgChangingPass(String msgChangingPass) {
        this.msgChangingPass = msgChangingPass;
    }
    public List<Festival> sviFestivali() throws SQLException{
       return FestivalDAO.dohvatiFestivaleZaRegKorisnike();
       
    }
    
    public String passwordUpdate(String username, String password, String newPasswrd) throws SQLException{
        String encryptedTextOld = "";
        String encryptedTextNew = "";
        if(username.trim().length() == 0 || password.trim().length() == 0 || newPasswrd.trim().length() == 0){
            msgChangingPass = "Morate uneti sve podatke!";
            return"changingPassword";
        }
        
        try {
                Cipher cipher = Cipher.getInstance(cipherTransformation);
                byte[] key = encryptionKey.getBytes(characterEncoding);
                SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
                IvParameterSpec ivparameterspec = new IvParameterSpec(key);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
                byte[] cipherText = cipher.doFinal(password.getBytes("UTF8"));
                Base64.Encoder encoder = Base64.getEncoder();
                encryptedTextOld = encoder.encodeToString(cipherText);

            } catch (Exception E) {
                System.err.println("Encrypt Exception : " + E.getMessage());
            }
        try {
                Cipher cipher = Cipher.getInstance(cipherTransformation);
                byte[] key = encryptionKey.getBytes(characterEncoding);
                SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
                IvParameterSpec ivparameterspec = new IvParameterSpec(key);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
                byte[] cipherText = cipher.doFinal(newPasswrd.getBytes("UTF8"));
                Base64.Encoder encoder = Base64.getEncoder();
                encryptedTextNew = encoder.encodeToString(cipherText);

            } catch (Exception E) {
                System.err.println("Encrypt Exception : " + E.getMessage());
            }
        
        String result = KorisnikDAO.passwordUpdate(username, encryptedTextOld, encryptedTextNew);
        if(result == "istaSifra"){
            msgChangingPass = "Vasa nova sifra ne moze biti ista kao stara!";
            return"changingPassword";
        }
        if(result == "neostojeciKorisnik"){
            msgChangingPass = "Vasi podatci nisu validni, molimo vas pokusajte ponovo!";
            return"changingPassword";
        }
        return "index";
    }

    
}
