import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

public class User {
    private String name;
    private String passwordToHash;
    static final String[] commonPasswords = {"123456","123456789","12345","qwerty","password","12345678","iloveyou","111111","1q2w3e","123123","testpass123"};

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
    

    public String getSHA256() {
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

    public Boolean verifyPassword(String passin) {
        //verifies if password follows criteria
        int lCount = 0;    //count of letters
        int nCount = 0;    //count of numbers
        boolean flag = true;

        for (int i=0; i<passin.length(); i++) {
            char c = passin.charAt(i);
            if ( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ) {
                lCount++;
            }
            else if (Character.isDigit(c)) {
                nCount++;
            }
        }

        for (String password:commonPasswords) {         //verify input password is not the same as the a common password
            if (Objects.equals(passin, password)) {
                flag = false;
            }
        }

        if (passin.length()<8 || nCount<1 || lCount<1) {    //verify length is greater than 8 and has atleast 1 number and letter
            flag = false;
        }
        return flag;
    }

}

//https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/ 