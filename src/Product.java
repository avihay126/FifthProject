public class Product {
    private String productName;
    private double price;
    private boolean isInStock;
    private int amount;

    public Product(String productName, double price, boolean isInStock) {
        this.productName = productName;
        this.price = price;
        this.isInStock = isInStock;

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void addAmount(int amount){
        this.amount+=amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    public String toString(){
        return this.productName+", "+this.price+"$.";
    }
}
