import java.util.Scanner;
import java.io.File;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        User user1 = new User();
        
        File text = new File("passwords.txt");
        System.out.println("File created: " + text.getName());

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

        System.out.println("Username: "+ user1.getName());
        System.out.println("Password: " + user1.getPasswordToHash());
        System.out.println(user1.getSHA256());


        text.delete();
        System.out.println("Deleted file: "+text.getName());
    }

}