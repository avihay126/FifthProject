import java.util.Scanner;
public class Main {
    public static final int REGISTER=1,LOG_IN=2,FINISH_THE_PROGRAM=3;


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
                case REGISTER:
                    store.createUser();
                    break;
                case LOG_IN:
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
                case FINISH_THE_PROGRAM:
                    runLoop = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
