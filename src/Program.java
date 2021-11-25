import java.util.Scanner;
import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        User user1 = new User();
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("passwords.txt"));
        bw.write("money");

        System.out.println("New User: ");
        user1.setName(scan.nextLine());
        System.out.println("New Password: ");
        String pass = scan.nextLine();

        if (user1.verifyPassword(pass)) {
            user1.setPasswordToHash(pass);
        }
        else {
            System.out.println("fail");
            return;
        }
        
        bw.write(user1.getName()+":");
        bw.write(user1.getSHA256()+"\n");

        bw.close();
        new File("passwords.txt").delete();
    }

}