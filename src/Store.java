import java.util.Arrays;
import java.util.Scanner;

public class Store {

    private User[] users;
    private Product[] products;
    private Cart[] carts;

    public final int INITIAL_SIZE_ARRAY = 0, PASSWORD_MIN_SIZE = 6;

    public Store() {
        this.users = new User[INITIAL_SIZE_ARRAY];
        this.products = new Product[INITIAL_SIZE_ARRAY];
        this.carts = new Cart[INITIAL_SIZE_ARRAY];
    }

    public void createUser() {
        User user = workerOrCostumer();
        String firstName = name("First name: ");
        String lastName = name("Last name: ");
        String userName = userName();
        String password = password();
        boolean isVip = isVip();
        if (user instanceof Worker) {
            String rank = workerRank();
            double discount = workerDiscount(rank);
            Worker worker = new Worker(firstName, lastName, userName, password, isVip, discount, rank);
            this.users = (User[]) addObjectToArray(worker, this.users);
        } else {
            Costumer costumer = new Costumer(firstName, lastName, userName, password, isVip);
            this.users = (User[]) addObjectToArray(costumer, this.users);

        }
    }

    private User workerOrCostumer() {
        Scanner scanner = new Scanner(System.in);
        User user;
        int choose;
        do {
            System.out.println("1- Worker \n2- Costumer");
            choose = scanner.nextInt();
        } while (choose < 1 || choose > 2);
        if (choose == 1) {
            user = new Worker();
        } else {
            user = new Costumer();
        }
        return user;
    }

    private String name(String print) {
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            System.out.print(print);
            name = scanner.nextLine();
        } while (!isString(name));
        return name;
    }

    private boolean isString(String str) {
        boolean flag = true;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private String userName() {
        Scanner scanner = new Scanner(System.in);
        String userName;
        do {
            System.out.println("UserName: ");
            userName = scanner.nextLine();
        } while (userNameExist(userName));
        return userName;
    }

    private boolean userNameExist(String userName) {
        boolean isExist = false;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(userName)) {
                System.out.println("This username is already exist!");
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    private String password() {
        Scanner scanner = new Scanner(System.in);
        String password;
        do {
            System.out.println("Enter password:(min 6 chars) ");
            password = scanner.nextLine();
        } while (password.length() < PASSWORD_MIN_SIZE);
        return password;
    }

    private String workerRank() {
        Scanner scanner = new Scanner(System.in);
        String rank = null;
        int chosenRank;
        do {
            System.out.println("State your rank: \n" +
                    "1- Regular worker: \n" +
                    "2- Manager: \n" +
                    "3- Board member: ");
            chosenRank = scanner.nextInt();
            switch (chosenRank) {
                case 1:
                    rank = "Regular worker";
                    break;
                case 2:
                    rank = "Manager";
                    break;
                case 3:
                    rank = "Board member";
                    break;
                default:
                    System.out.println("Invalid rank!");
            }
        } while (chosenRank < 1 || chosenRank > 3);
        return rank;
    }

    private boolean isVip() {
        Scanner scanner = new Scanner(System.in);
        boolean isVip = false;
        char chooseVip;
        do {
            System.out.println("Vip?\nY- Yes \nN- No ");
            chooseVip = scanner.next().charAt(0);
        } while (chooseVip != 'Y' && chooseVip != 'N');
        if (chooseVip == 'Y') {
            isVip = true;
        }
        return isVip;
    }

    private double workerDiscount(String rank) {
        double discount = 0;
        switch (rank) {
            case "Regular worker":
                discount = 0.1;
                break;
            case "Manager":
                discount = 0.2;
                break;
            case "Board member":
                discount = 0.3;
                break;
        }
        return discount;
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        User user = workerOrCostumer();
        System.out.println("UserName: ");
        String userName = scanner.nextLine();
        String password = password();
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(userName) && this.users[i].getPassword().equals(password)) {
                if (user instanceof Worker && this.users[i] instanceof Worker) {
                    user = this.users[i];
                } else if ((!(user instanceof Worker)) && (!(this.users[i] instanceof Worker))) {
                    user = this.users[i];
                }
            }
        }
        return user;
    }

    public void loginToWorker(User user) {
        System.out.println("Hello "+user);
        workerMenuChoose(user);
    }

    private void printWorkerMenu() {
        System.out.println("1- Costumer list: \n" +
                "2- VIP costumer list: \n" +
                "3- Costumer who have made at least one purchase: \n" +
                "4- Costumer with the highest amount of purchase: \n" +
                "5- Adding a new product: \n" +
                "6- Change inventory status fo product: \n" +
                "7- Making a purchase: \n" +
                "8- Log out: ");
    }

    private void workerMenuChoose(User user) {
        Scanner scanner = new Scanner(System.in);
        Costumer costumer = new Costumer();
        boolean runLoop = true;
        int choose;
        while (runLoop) {
            printWorkerMenu();
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    printAllCostumers(this.users);
                    break;
                case 2:
                    printAllCostumers(onlyVip());
                    break;
                case 3:
                    printAllCostumers(atLeastOnePurchase());
                    break;
                case 4:
                    highestSumPurchase();
                    break;
                case 5:
                    addProduct();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    runLoop = false;
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void printAllCostumers(User[] users) {
        if (users.length==0||users[0]==null){
            System.out.println("There are no costumers in the system.\n");
        }else {
            int numbering=1;
            for (int i = 0; i < users.length; i++) {
                if (users[i]!=null) {
                    System.out.println(numbering + "- " + users[i]);
                    numbering++;
                }
            }
        }

    }

    private User[] onlyVip() {
        User[] costumers = new User[this.users.length];
        costumers=  copyArray(costumers,this.users);
        for (int i = 0; i < costumers.length; i++) {
            Costumer costumer= (Costumer) costumers[i];
            if (costumer!=null&&!costumer.isVip()) {
                costumers = (User[])  removeObjectFromArray(costumers, costumer);
            }
        }
        return costumers;
    }

    private User[] atLeastOnePurchase() {
        User[] costumers = new User[this.users.length];
        costumers=  copyArray(costumers,this.users);
        for (int i = 0; i < costumers.length; i++) {
            Costumer costumer=(Costumer) costumers[i];
            if (costumer!=null&&costumer.getNumberOfPurchase() < 1) {
                costumers = (User[]) removeObjectFromArray(costumers, costumer);
                i--;
            }
        }
        return costumers;
    }

    private void highestSumPurchase() {
        User[] costumers =  this.users;
        if (costumers.length == 0) {
            System.out.println("There are no costumers in the system.\n");
        } else {
            Costumer costumer=(Costumer) costumers[0];
            double highestSum = costumer.getSumPurchase();
            for (int i = 0; i < costumers.length; i++) {
                if (costumer.getSumPurchase() > highestSum) {
                    highestSum = costumer.getSumPurchase();
                    costumer =(Costumer) costumers[i];
                }
            }
            System.out.println("The costumer with the highest sum purchase is: " + costumer+" "+costumer.getSumPurchase()+"$");
        }
    }

    private void addProduct() {
        Scanner scanner = new Scanner(System.in);
        String productName = name("Product name: ");
        double price = 0;
        double amountOfDiscount=0;
        do {
            System.out.println("Enter price: ");
            price = scanner.nextDouble();
        } while (price <= 0);
        do {
            System.out.println("Enter amount of discount for VIP: ");
            amountOfDiscount=scanner.nextDouble();
        } while (amountOfDiscount<0||amountOfDiscount>1);
        Product product=new Product(productName,price,amountOfDiscount);
        this.products=(Product[]) addObjectToArray(product,this.products);
    }




    public void loginToCostumer(User user) {
        System.out.println("Hello " + user);
        chooseProduct(user);
    }

    private Product[] printAvailableProduct() {
        Product[] availableProducts = this.products;
        for (int i = 0; i < availableProducts.length; i++) {
            if (!availableProducts[i].isInStock()) {
                availableProducts = (Product[]) removeObjectFromArray(availableProducts, availableProducts[i]);
            }
        }
        if (availableProducts.length == 0) {
            System.out.println("There are no products in stock.");
        } else {
            for (int i = 0; i < availableProducts.length; i++) {
                System.out.println(i + 1 + "- " + availableProducts[i]);
            }
        }
        return availableProducts;
    }

    private void chooseProduct(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        int choose;
        Cart cart = new Cart(user);
        while (run) {
            Product[] availableProducts = (Product[]) printAvailableProduct();
            do {
                System.out.println("Select a product to purchase,\n-1 to finish");
                choose = scanner.nextInt();
            } while (choose != -1 && (choose < 0 || choose > availableProducts.length));
            if (choose == -1) {
                run = false;
                System.out.println("The total price is" + cart.getTotalPrice() + "$");
            } else {
                choose--;
                int numberOfProducts;
                do {
                    System.out.println("Select number of products.");
                    numberOfProducts = scanner.nextInt();
                } while (numberOfProducts <= 0);
                availableProducts[choose].addAmount(numberOfProducts);
                cart.addProductToArray(availableProducts[choose]);
                cart.addTotalPrice(cart.getTotalPrice() + (numberOfProducts * availableProducts[choose].getPrice()));
                System.out.println(cart);


            }

        }

    }



    private Object[] addObjectToArray(Object object, Object[] objects) {
        Object[] newArray = new Object[objects.length + 1];
        for (int i = 0; i < objects.length; i++) {
            newArray[i] = objects[i];
        }
        newArray[objects.length] = object;
        newArray = Arrays.asList(newArray).toArray(objects);
        return newArray;
    }

    private Object[] removeObjectFromArray(Object[] objects, Object object) {
        Object[] newArray = new Object[objects.length - 1];
        int indexNewArray = 0;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i]!=null&&!objects[i].equals(object) ) {
                newArray[indexNewArray] = objects[i];
                indexNewArray++;
            }
        }
        newArray =Arrays.asList(newArray).toArray(objects);
        return newArray;
    }
    private User[] copyArray(User[]users1,User[] users2){
        for (int i=0;i<users2.length;i++){
            users1[i]=users2[i];
        }
        return users1;
    }


}
