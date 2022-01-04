public class Product {
    private String productName;
    private int price;
    private boolean isInStock;

    public Product(String productName, int price, boolean isInStock) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
