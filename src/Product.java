public class Product {
    private String productName;
    private double price;
    private boolean isInStock;

    public Product(String productName, double price, boolean isInStock) {
        this.productName = productName;
        this.price = price;
        this.isInStock = isInStock;
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
        return this.productName+", "+this.price+"$. "+(this.isInStock? "In stock.":"Out of stock.");
    }
}
