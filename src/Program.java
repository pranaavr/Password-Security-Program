import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Boolean flag = true;
        User user1 = new User();
        System.out.println("New User: ");
        user1.setName(scan.nextLine());
        System.out.println("New Password: ");
        user1.setPasswordToHash(scan.nextLine());


        System.out.println("Username: "+ user1.getName());
        System.out.println("Password: " + user1.getPasswordToHash());
        System.out.println(user1.getSHA256());
    }

}