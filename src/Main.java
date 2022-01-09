import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        chooseOption();

    }
    public static void printMenu() {
        System.out.println("1- Register: \n2- Sign in: \n3- Finish the program:");
    }

    public static void chooseOption(){
        Store store=new Store();
        Scanner scanner=new Scanner(System.in);
        boolean runLoop =true;
        int choose;
        while (runLoop) {
            printMenu();
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    store.createUser();
                    break;
                case 2:
                    User user= store.login();
                    if (user.getUserName()==null) {
                        System.out.println("This user is not exist.");
                    }else {
                        if (user instanceof Worker) {
                            store.loginToWorker(user);
                        } else if (user instanceof Costumer){
                            store.loginToCostumer(user);
                        }
                    }
                    break;
                case 3:
                    runLoop = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
