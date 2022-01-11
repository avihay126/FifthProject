public class Cart {

    private Product[] products;
    private User user;
    private double totalPrice;

    public final int INITIAL_SIZE=0;

    public Cart(User user) {
        this.user = user;
        this.products=new Product[INITIAL_SIZE];
        this.totalPrice=INITIAL_SIZE;
    }
    public void addProductToArray(Product product){
        Product[] newArray=new Product[this.products.length+1];
        for (int i=0;i<this.products.length;i++){
            newArray[i]=this.products[i];
        }
        newArray[this.products.length]=product;
        this.products=newArray;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }
    public void addTotalPrice(double price){
        this.totalPrice+=price;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product[] getProducts() {
        return this.products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString(){
        StringBuilder output=new StringBuilder();
        for (int i=0;i<this.products.length;i++){
            output.append(i+1).append("- ").append(this.products[i]).append(" "+this.products[i].getAmount()+".\n");

        }
        return "Cart info:\n"+output.toString()+"\nTotal price: "+this.totalPrice+"$";
    }
}
