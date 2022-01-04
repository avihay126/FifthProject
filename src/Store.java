import javax.print.MultiDocPrintService;
import java.util.Scanner;

public class Store {

    private User[] users;
    private Product[]products;

    public final int INITIAL_SIZE_ARRAY=0, PASSWORD_MIN_SIZE=6;

    public Store() {
        this.users =new User[INITIAL_SIZE_ARRAY];
        this.products = new Product[INITIAL_SIZE_ARRAY];
    }

    public void createUser(){
        User user=workerOrCostumer();
        String firstName=firstName();
        String lastName=lastName();
        String userName=userName();
        String password=password();
        if (user instanceof Worker){
           String rank= workerRank();
           double discount=workerDiscount(rank);
           Worker worker =new Worker(firstName,lastName,userName,password,discount,rank);
           addUserToArray(worker);
        }else {
           boolean isVip=isVip();
           Costumer costumer=new Costumer(firstName,lastName,userName,password,isVip);
           addUserToArray(costumer);

        }
    }
    private User workerOrCostumer(){
        Scanner scanner=new Scanner(System.in);
        User user;
        int choose;
        do {
            System.out.println("1- Worker \n2- Costumer");
            choose=scanner.nextInt();
        }while (choose<1||choose>2);
        if (choose==1){
            user=new Worker();
        }else {
            user=new Costumer();
        }
        return user;
    }
    private String firstName(){
        Scanner scanner=new Scanner(System.in);
        String firstName;
        do {
            System.out.print("First name: ");
            firstName=scanner.nextLine();
        }while (!isString(firstName));
        return firstName;
    }
    private String lastName(){
        Scanner scanner=new Scanner(System.in);
        String lastName;
        do {
            System.out.print("Last name: ");
            lastName=scanner.nextLine();
        }while (!isString(lastName));
        return lastName;
    }
    private boolean isString(String str){
        boolean flag=true;
        for (int i=0;i<str.length();i++){
            if (Character.isDigit(str.charAt(i))){
                flag=false;
                break;
            }
        }
        return flag;
    }
    private String userName(){
        Scanner scanner=new Scanner(System.in);
        String userName;
        do {
            System.out.println("UserName: ");
            userName=scanner.nextLine();
        }while (userNameExist(userName));
        return userName;
    }
    private boolean userNameExist(String userName){
        boolean isExist=false;
        for (int i=0;i<this.users.length;i++){
            if (this.users[i].getUserName().equals(userName)){
                System.out.println("This username is already exist!");
                isExist=true;
                break;
            }
        }
        return isExist;
    }
    private String password(){
        Scanner scanner=new Scanner(System.in);
        String password;
        do {
            System.out.println("Enter password:(min 6 chars) ");
            password=scanner.nextLine();
        }while (password.length()<PASSWORD_MIN_SIZE);
        return password;
    }
    private String workerRank(){
        Scanner scanner =new Scanner(System.in);
        String rank=null;
        int chosenRank;
        do {
            System.out.println("State your rank: \n" +
                    "1- Regular worker: \n" +
                    "2- Manager: \n" +
                    "3- Board member: ");
            chosenRank=scanner.nextInt();
            switch (chosenRank){
                case 1:
                    rank="Regular worker";
                    break;
                case 2:
                    rank="Manager";
                    break;
                case 3:
                    rank="Board member";
                    break;
                default:
                    System.out.println("Invalid rank!");
            }
        }while (chosenRank<1||chosenRank>3);
        return rank;
    }
    private boolean isVip(){
        Scanner scanner=new Scanner(System.in);
        boolean isVip=false;
        char chooseVip;
        do {
            System.out.println("Vip?\nY- Yes \nN- No ");
            chooseVip=scanner.next().charAt(0);
        }while (chooseVip!='Y'&&chooseVip!='N');
        if (chooseVip=='Y'){
            isVip=true;
        }
        return isVip;
    }
    private void addUserToArray(User user){
        User[] newArray=new User[this.users.length+1];
        for (int i=0;i<this.users.length;i++){
            newArray[i]=this.users[i];
        }
        newArray[this.users.length]=user;
        this.users=newArray;
    }
    private double workerDiscount(String rank){
        double discount=0;
        switch (rank){
            case "Regular worker":
                discount=0.1;
                break;
            case "Manager":
                discount=0.2;
                break;
            case "Board member":
                discount=0.3;
                break;
        }
        return discount;
    }

    public User login(){
        Scanner scanner=new Scanner(System.in);
        User user=workerOrCostumer();
        System.out.print("UserName: ");
        String userName = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(userName) && this.users[i].getPassword().equals(password)) {
                user = users[i];
            }
        }
        if (user.getUserName()==null) {
            user=null;
        }else {
            if (user instanceof Worker) {

            } else {

            }
        }
        return user;
    }
    private User[] removeUserFromArray(User[] users,User user){
        User[] newArray=new User[users.length-1];
        for (int i=0;i<newArray.length;i++){

        }
    }
}
