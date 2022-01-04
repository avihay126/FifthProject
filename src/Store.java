public class Store {

    private User[] users;
    private Product[]products;

    public final int INITIAL_SIZE_ARRAY=0;

    public Store() {
        this.users =new User[INITIAL_SIZE_ARRAY];
        this.products = new Product[INITIAL_SIZE_ARRAY];
    }
}
