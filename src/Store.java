import java.util.Scanner;

public class Store {

    private User[] users;
    private Product[]products;
    private Cart[] carts;

    public final int INITIAL_SIZE_ARRAY=0, PASSWORD_MIN_SIZE=6;

    public Store() {
        this.users =new User[INITIAL_SIZE_ARRAY];
        Product product1=new Product("bamba",10,true);
        Product product2=new Product("bisli",5,false);
        Product product3=new Product("pizza",20,true);
        this.products = new Product[3];
        this.products[0]=product1;
        this.products[1]=product2;
        this.products[2]=product3;
        this.carts=new Cart[INITIAL_SIZE_ARRAY];
    }

    public void createUser(){
        User user=workerOrCostumer();
        String firstName= fullName("First name: ");
        String lastName=fullName("Last name: ");
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
    private String fullName(String print){
        Scanner scanner=new Scanner(System.in);
        String name;
        do {
            System.out.print(print);
            name=scanner.nextLine();
        }while (!isString(name));
        return name;
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
        System.out.println("UserName: ");
        String userName = scanner.nextLine();
        String password = password();
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(userName) && this.users[i].getPassword().equals(password)) {
                if (user instanceof Worker&&this.users[i] instanceof Worker){
                    user = this.users[i];
                }else if (user instanceof Costumer&& this.users[i] instanceof Costumer){
                    user = this.users[i];
                }
            }
        }
        if (user.getUserName()==null) {
            System.out.println("This user is not exist.");
            user=null;
        }else {
            if (user instanceof Worker) {

            } else if (user instanceof Costumer){
                loginToCostumer(user);

            }
        }
        return user;
    }
    private void loginToCostumer(User user){
        System.out.println(user);
        chooseProduct(user);
    }
    private Product[] printAvailableProduct(){
            Product[] availableProducts=this.products;
            for (int i=0;i<availableProducts.length;i++){
                if (!availableProducts[i].isInStock()) {
                    availableProducts= removeProductFromArray(availableProducts,availableProducts[i]);
                }
            }
            if (availableProducts.length==0){
                System.out.println("There are no products in stock.");
            }else {
                for (int i=0;i<availableProducts.length;i++){
                    System.out.println(i+1+"- "+availableProducts[i]);
                }
            }
            return availableProducts;
    }

    private void chooseProduct(User user){
        Scanner scanner=new Scanner(System.in);
        boolean run =true;
        int choose;
        Cart cart =new Cart(user);
        while (run) {
            Product[] availableProducts = printAvailableProduct();
            do {
                System.out.println("Select a product to purchase,\n-1 to finish");
                choose = scanner.nextInt();
            } while (choose != -1 && (choose < 0 || choose > availableProducts.length));
            if (choose==-1){
                run=false;
                System.out.println("The total price is"+cart.getTotalPrice()+"$");
            }else {
                choose--;
                int numberOfProducts;
                do {
                    System.out.println("Select number of products.");
                    numberOfProducts=scanner.nextInt();
                }while (numberOfProducts<=0);
                availableProducts[choose].addAmount(numberOfProducts);
                cart.addProductToArray(availableProducts[choose]);
                cart.addTotalPrice(cart.getTotalPrice()+(numberOfProducts*availableProducts[choose].getPrice()));
                System.out.println(cart);


            }

        }

    }


    private Product[] removeProductFromArray(Product[] products, Product product){
        Product[] newArray=new Product[products.length-1];
        int indexNewArray=0;
        for (int i=0;i<products.length;i++){
            if (products[i]!=product){
                newArray[indexNewArray]=products[i];
                indexNewArray++;
            }
        }
        return newArray;
    }


}
