import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your login username : ");
        String username = scanner.nextLine();
        System.out.println("Please enter your login password : ");
        String password = scanner.nextLine();
        System.out.println("Please enter target profile name : ");
        String targetProfileName = scanner.nextLine();

        App app = new App();
        app.loginWith(username,password);
        app.navigateToTargetProfile(targetProfileName);
        app.clickFirstPost();
        app.allPost(app.getpostCount());
    }
}