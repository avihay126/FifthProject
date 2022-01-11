public class Product {
    private String productName;
    private double price;
    private boolean isInStock;
    private double vipDiscount;
    private int amountInCart;
    public static final int CHANGE_T0_PERCENT=100;

    public Product(String productName, double price,double vipDiscount) {
        this.productName = productName;
        this.price = price;
        this.vipDiscount=vipDiscount;
        this.isInStock = true;

    }

    public int getAmount() {
        return this.amountInCart;
    }

    public void setAmount(int amount) {
        this.amountInCart = amount;
    }
    public void addAmount(int amount){
        this.amountInCart +=amount;
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
        return this.productName+", "+this.price+"$. "+this.vipDiscount*CHANGE_T0_PERCENT+"% (VIP)| ";
    }
}
