import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
        Worker worker=(Worker) user;
        worker.printHello();
        workerMenuChoose(user);
    }

    private void printWorkerMenu() {
        System.out.println("1- Costumer list: \n" +
                "2- VIP costumer list: \n" +
                "3- Costumer who have made at least one purchase: \n" +
                "4- Costumer with the highest sum of purchase: \n" +
                "5- Adding a new product: \n" +
                "6- Change inventory status fo product: \n" +
                "7- Making a purchase: \n" +
                "8- Log out: \n" +
                "------------------------------------");
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
                    changeStatusForProduct();
                    break;
                case 7:
                    chooseProductToBuy(user);
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
        if (!isEmptyArray(users)){
            int numbering=1;
            for (int i = 0; i < users.length; i++) {
                if (users[i]!=null) {
                    System.out.println(numbering + "). " + users[i]+"\n---------------------------------");
                    numbering++;
                }
            }
        }

    }
    private User[] onlyVip() {
        User[] costumers = new User[this.users.length];
        costumers= (User[]) copyArray(costumers,this.users);
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
        costumers= (User[]) copyArray(costumers,this.users);
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
        User[] costumers = new User[this.users.length];
        costumers=(User[]) copyArray(costumers,this.users);
        if (!isEmptyArray(costumers)) {
            Costumer costumer=(Costumer) costumers[0];
            double highestSum = costumer.getSumPurchase();
            for (int i = 0; i < costumers.length; i++) {
                Costumer currentCostumer=(Costumer) costumers[i];
                if (currentCostumer.getSumPurchase() > highestSum) {
                    highestSum = currentCostumer.getSumPurchase();
                    costumer = currentCostumer;
                }
            }
            System.out.println("The highest sum purchase is: "+costumer.getSumPurchase()+"$\nThe Costumer is: " + costumer+" \n");
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
    private void changeStatusForProduct(){
        chooseProductToChangeStatus(this.products);
    }
    private void chooseProductToChangeStatus(Product[] products) {
        Scanner scanner=new Scanner(System.in);
        boolean run=true;
        int choose;
        printProducts(products);
        do {
            System.out.println("Select product: ");
            choose=scanner.nextInt();
        }while (choose<1||choose>products.length);
        choose--;
        System.out.println("The product you choose to change status is: "+products[choose].getProductName()+"\n");
        int option=0;
        do {
            System.out.println("1- In stock.\n2- Out of stock.");
            option=scanner.nextInt();
        }while (option<1||option>2);
        if (option==1){
            products[choose].setInStock(true);
            System.out.println(products[choose].getProductName()+" get in stock");

        }else {
            products[choose].setInStock(false);
            System.out.println(products[choose].getProductName()+" get out of stock");
        }
        System.out.println("-------------------------------");
    }




    public void loginToCostumer(User user) {
        Costumer costumer=(Costumer) user;
        costumer.printHello();
        chooseProductToBuy(costumer);

    }

    private void printProducts(Product [] products){
        if (!isEmptyArray(products)) {
            for (int i=0;i<products.length;i++){
                if (products[i]!=null)
                System.out.println(i+1+"- "+products[i]);
            }
        }
    }
    private void chooseProductToBuy(User user){
        Costumer costumer=(Costumer) user;
        Scanner scanner=new Scanner(System.in);
        boolean run=true;
        int choose;
        int amount=0;
        Product[] products = onlyAvailableProducts();
        if (!isEmptyArray(products)) {
            Cart cart= new Cart(costumer);
            while (run) {
                printProducts(products);
                do {
                    System.out.println("Select a product to buy.\n-1. finish the purchase.");
                    choose = scanner.nextInt();
                } while ((choose < 1 || choose > products.length) && (choose != -1));
                if (choose == -1) {
                    run=false;
                    finishPurchase(costumer,cart);
                } else {
                    choose--;
                    do {
                        System.out.println("Select amount: ");
                        amount=scanner.nextInt();
                    }while (amount<1);
                    products[choose].addAmount(amount);
                    if (!isProductAlreadyInCart(products[choose],cart)) {
                        cart.addProductToArray(products[choose]);
                    }
                    double totalPrice=0;
                    Product currentProduct=products[choose];
                    if (currentProduct.equals(products[choose])) {
                        if (costumer.isVip()) {
                            totalPrice = ((currentProduct.getPrice() * (1 - currentProduct.getVipDiscount())) * amount);
                        } else {
                            totalPrice = currentProduct.getPrice() * amount;
                        }
                        cart.addTotalPrice(totalPrice);
                    }

                    System.out.println(cart);



                }
            }
        }

    }
    private Product[] onlyAvailableProducts() {
        Product[] availableProducts=new Product[this.products.length];
        availableProducts= (Product[]) copyArray(availableProducts,this.products);
        for (int i=0;i<availableProducts.length;i++){
            if (availableProducts[i]!=null&&!availableProducts[i].isInStock()){
                availableProducts= (Product[]) removeObjectFromArray(availableProducts,availableProducts[i]);
                i--;
            }
        }
        availableProducts=removeNullProducts(availableProducts);
        return availableProducts;
    }
    private void finishPurchase(Costumer costumer,Cart cart){
        Calendar calendar= GregorianCalendar.getInstance();
        if (costumer instanceof Worker){
            Worker worker=(Worker) costumer;
            cart.setTotalPrice(cart.getTotalPrice()*(1-worker.getAmountOfDiscount()));
        }
        costumer.setDateOfLstPurchase(calendar.getTime());
        costumer.addNumberOfPurchase(1);
        System.out.println("The total price is "+cart.getTotalPrice()+"$\n---------------------" );
        costumer.addSumOfPurchase(cart.getTotalPrice());
        setAllProductAmountToZero(onlyAvailableProducts());
    }
    private boolean isProductAlreadyInCart(Product product,Cart cart){
        boolean alreadyExistInCart=false;
        for (int i=0;i<cart.getProducts().length;i++){
            if (product.getProductName().equals(cart.getProducts()[i].getProductName())){
                alreadyExistInCart=true;
                break;
            }
        }
        return alreadyExistInCart;
    }
    private void setAllProductAmountToZero(Product[] products){
        for (int i=0;i<products.length;i++){
            products[i].setAmount(0);
        }
    }




    private Product[] removeNullProducts(Product[] products){
        Product[] newArray=new Product[products.length];
        int indexNewArray=0;
        for (int i=0;i<products.length;i++){
            if (products[i]!=null){
                newArray[indexNewArray]=products[i];
                indexNewArray++;
            }
        }
        Product[] retArray= new Product[indexNewArray];
        for (int i=0;i<retArray.length;i++){
            retArray[i]=newArray[i];
        }
        return retArray;
    }
    private boolean isEmptyArray(Object[] objects){
        boolean isEmpty=false;
        if (objects.length==0||objects[0]==null){
            System.out.println("There are no suitable details to display.\n-------------------------------");
            isEmpty=true;
        }
        return isEmpty;
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
    private Object[] copyArray(Object[]objects1,Object[] objects2){
        for (int i=0;i<objects2.length;i++){
            objects1[i]=objects2[i];
        }
        return objects1;
    }


}