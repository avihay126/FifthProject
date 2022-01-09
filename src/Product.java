public class Product {
    private String productName;
    private double price;
    private boolean isInStock;
    private double vipDiscount;
    private int amount;

    public Product(String productName, double price,double vipDiscount) {
        this.productName = productName;
        this.price = price;
        this.vipDiscount=vipDiscount;
        this.isInStock = true;

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

    public double getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(double vipDiscount) {
        this.vipDiscount = vipDiscount;
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
