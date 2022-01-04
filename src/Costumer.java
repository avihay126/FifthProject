public class Costumer extends User{
     private boolean isVip;


    public Costumer(String firstName, String lastName, String userName, String password,double amountOfDiscount, boolean isVip) {
        super(firstName, lastName, userName, password,amountOfDiscount);
        this.isVip = isVip;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
    public String toString(){
        return super.toString()+(this.isVip? " (VIP)":".");
    }
}
