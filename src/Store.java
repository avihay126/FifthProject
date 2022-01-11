import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Store {

    private User[] users;
    private Product[] products;
    private Cart[] carts;

    public final int INITIAL_SIZE_ARRAY = 0, PASSWORD_MIN_SIZE = 6,WORKER=1,COSTUMER=2,
    REGULAR_WORKER=1,MANAGER=2,BOARD_MEMBER=3,FIRST_INDEX=0,COSTUMER_LIST=1,VIP_COSTUMER_LIST=2,
    AT_LEAST_ONE_PURCHASE=3,HIGHEST_SUM_PURCHASE=4,ADD_PRODUCT=5,CHANGE_INVENTORY_STATUS=6,MAKING_PURCHASE=7,
    LOG_OUT=8,START_NUMBERING=1,MIN_ONE_PURCHASE=1,MIN_DISCOUNT=0,MAX_DISCOUNT=1,FIRST_PRODUCT=1,
    IN_STOCK=1,OUT_OF_STOCK=2,FINISH_THE_PURCHASE=-1,MIN_AMOUNT=1,HUNDRED_PERCENT=1,RISE_BY_ONE=1,RESET_AMOUNT=0;
    public final char YES='Y',NO='N';
    public final double REGULAR_WORKER_DISCOUNT=0.1,MANAGER_DISCOUNT=0.2,BOARD_MEMBER_DISCOUNT=0.3,MIN_PRICE=0;
    public final boolean IS_IN_STOCK=true,IS_OUT_OF_STOCK=false;

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
        } while (choose < WORKER || choose > COSTUMER);
        if (choose == WORKER) {
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
                case REGULAR_WORKER:
                    rank = "Regular worker";
                    break;
                case MANAGER:
                    rank = "Manager";
                    break;
                case BOARD_MEMBER:
                    rank = "Board member";
                    break;
                default:
                    System.out.println("Invalid rank!");
            }
        } while (chosenRank < REGULAR_WORKER || chosenRank > BOARD_MEMBER);
        return rank;
    }
    private boolean isVip() {
        Scanner scanner = new Scanner(System.in);
        boolean isVip = false;
        char chooseVip;
        do {
            System.out.println("Vip?\nY- Yes \nN- No ");
            chooseVip = scanner.next().charAt(FIRST_INDEX);
        } while (chooseVip != YES && chooseVip != NO);
        if (chooseVip == YES) {
            isVip = true;
        }
        return isVip;
    }
    private double workerDiscount(String rank) {
        double discount = 0;
        switch (rank) {
            case "Regular worker":
                discount = REGULAR_WORKER_DISCOUNT;
                break;
            case "Manager":
                discount = MANAGER_DISCOUNT;
                break;
            case "Board member":
                discount = BOARD_MEMBER_DISCOUNT;
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
        boolean runLoop = true;
        int choose;
        while (runLoop) {
            printWorkerMenu();
            choose = scanner.nextInt();
            switch (choose) {
                case COSTUMER_LIST:
                    printAllCostumers(this.users);
                    break;
                case VIP_COSTUMER_LIST:
                    printAllCostumers(onlyVip());
                    break;
                case AT_LEAST_ONE_PURCHASE:
                    printAllCostumers(atLeastOnePurchase());
                    break;
                case HIGHEST_SUM_PURCHASE:
                    highestSumPurchase();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case CHANGE_INVENTORY_STATUS:
                    changeStatusForProduct(this.products);
                    break;
                case MAKING_PURCHASE:
                    chooseProductToBuy(user);
                    break;
                case LOG_OUT:
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
            int numbering=START_NUMBERING;
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
            if (costumer!=null&&costumer.getNumberOfPurchase() < MIN_ONE_PURCHASE) {
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
            Costumer costumer=(Costumer) costumers[FIRST_INDEX];
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
        } while (price <= MIN_PRICE);
        do {
            System.out.println("Enter amount of discount for VIP: ");
            amountOfDiscount=scanner.nextDouble();
        } while (amountOfDiscount<MIN_DISCOUNT||amountOfDiscount>MAX_DISCOUNT);
        Product product=new Product(productName,price,amountOfDiscount);
        this.products=(Product[]) addObjectToArray(product,this.products);
    }
    private void changeStatusForProduct(Product[] products) {
        Scanner scanner=new Scanner(System.in);
        boolean run=true;
        int choose;
        printProducts(products);
        do {
            System.out.println("Select product: ");
            choose=scanner.nextInt();
        }while (choose<FIRST_PRODUCT||choose>products.length);
        choose--;
        System.out.println("The product you choose to change status is: "+products[choose].getProductName()+"\n");
        int option=0;
        do {
            System.out.println("1- In stock.\n2- Out of stock.");
            option=scanner.nextInt();
        }while (option<IN_STOCK||option>OUT_OF_STOCK);
        if (option==IN_STOCK){
            products[choose].setInStock(IS_IN_STOCK);
            System.out.println(products[choose].getProductName()+" get in stock");

        }else {
            products[choose].setInStock(IS_OUT_OF_STOCK);
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
                } while ((choose < FIRST_PRODUCT || choose > products.length) && (choose != FINISH_THE_PURCHASE));
                if (choose == FINISH_THE_PURCHASE) {
                    run=false;
                    finishPurchase(costumer,cart);
                } else {
                    choose--;
                    do {
                        System.out.println("Select amount: ");
                        amount=scanner.nextInt();
                    }while (amount<MIN_AMOUNT);
                    products[choose].addAmount(amount);
                    if (!isProductAlreadyInCart(products[choose],cart)) {
                        cart.addProductToArray(products[choose]);
                    }
                    double totalPrice=0;
                    Product currentProduct=products[choose];
                    if (currentProduct.equals(products[choose])) {
                        if (costumer.isVip()) {
                            totalPrice = ((currentProduct.getPrice() * (HUNDRED_PERCENT - currentProduct.getVipDiscount())) * amount);
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
            cart.setTotalPrice(cart.getTotalPrice()*(HUNDRED_PERCENT-worker.getAmountOfDiscount()));
        }
        costumer.setDateOfLastPurchase(calendar.getTime());
        costumer.addNumberOfPurchase(RISE_BY_ONE);
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
            products[i].setAmount(RESET_AMOUNT);
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