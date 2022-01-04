public class Costumer extends User{
    private boolean isVip;

    public Costumer(String firstName, String lastName, String userName, String password, boolean isVip) {
        super(firstName, lastName, userName, password);
        this.isVip = isVip;
    }
    public Costumer(){

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
