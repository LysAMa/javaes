

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;


/**
 * Created by root on 4/13/16.
 */
public class Alcrypt {


    private String mot;
    private static final String password = "la";
    private static String salt;
    private static int keySize =256;
    private static int pswdIterations =65536;
    private byte[] ivBytes;

    public Alcrypt(String omot){
        mot = omot;
    }

    public String getMot() {
        return mot;
    }

    public String encode(){
        String motCode=getMot();
        byte[] toCode=motCode.getBytes();
       // = Base64.encodeBase64(toCode);
        byte[] coded=  Base64.getEncoder().encode(toCode);
        String s=new String(coded);

        try {
            return encrypt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public String decode(){
        String trucCode =getMot();
       // byte[] decode = Base64.decodeBase64(trucCode);
        byte[] decode = Base64.getDecoder().decode(trucCode.getBytes());
        return new String(decode);

    }
    public static String decode(String mot){
        byte[] l=Base64.getDecoder().decode(mot);
        return new String(l);
    }

    //crypter
    public String encrypt(String plaintext) throws Exception{
        salt = generatesalt();
        byte[] saltbytes = salt.getBytes("UTF-8");

        //Derive key
        SecretKeyFactory factory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec=new PBEKeySpec(password.toCharArray(), saltbytes, pswdIterations,keySize);
        SecretKey secretKey= factory.generateSecret(spec);
        SecretKeySpec secret=new SecretKeySpec(secretKey.getEncoded(), "AES");

        //Ecrypt
        Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secret);
        AlgorithmParameters params=cipher.getParameters();
        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptTextBy=cipher.doFinal(plaintext.getBytes("UTF-8"));
        return new String(encryptTextBy);



    }

    public String generatesalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String s = new String(bytes);
        return s;
    }

}
