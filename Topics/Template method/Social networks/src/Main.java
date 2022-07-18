import java.util.Scanner;

abstract class SocialNetwork {

    public void connect() {
        // write your code here ...
        login();
        postMessage();
        logout();
    }

    protected abstract void logout();

    protected abstract void postMessage();

    protected abstract void login();

    // write your code here ...


}

class Instagram extends SocialNetwork{
    @Override
    protected void logout() {
        System.out.println("Log out of Instagram");
    }

    @Override
    protected void postMessage() {
        System.out.println("Post: Hello, Instagram!");
    }

    @Override
    protected void login() {
        System.out.println("Log into Instagram");
    }
    // write your code here ...
}


class Facebook extends SocialNetwork {
    @Override
    protected void logout() {
        System.out.println("Log out of Facebook");
    }

    @Override
    protected void postMessage() {
        System.out.println("Post: Hello, Facebook!");
    }

    @Override
    protected void login() {
        System.out.println("Log into Facebook");
    }
    // write your code here ...
}

// Do not change the code below
class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String type = scanner.nextLine();
        scanner.close();
        SocialNetwork network = null;
        if ("facebook".equalsIgnoreCase(type)) {
            network = new Facebook();
        } else if ("instagram".equalsIgnoreCase(type)) {
            network = new Instagram();
        } else {
            System.out.println("Error!");
            System.exit(0);
        }
        network.connect();
    }
}