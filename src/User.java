import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class User {
    private String name;
    private String passwordToHash;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordToHash() {
        return this.passwordToHash;
    }

    public void setPasswordToHash(String passwordToHash) {
        this.passwordToHash = passwordToHash;
    }

    String get_SHA_256() {
        //get salt
        SecureRandom sr = new SecureRandom();
        byte[] saltb = new byte[16];
        sr.nextBytes(saltb);
        String salt = saltb.toString();
        
        //encrypt
        String encryptedPass = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i=0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPass = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPass;
    }
}
