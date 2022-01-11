import java.util.Date;

public class Costumer extends User implements PrintHello{
    private boolean isVip;
    private int numberOfPurchase;
    private double sumPurchase;
    private Date dateOfLastPurchase;


    public final int INITIAL_SIZE=0;



    public Costumer(String firstName, String lastName, String userName, String password, boolean isVip) {
        super(firstName, lastName, userName, password);
        this.isVip=isVip;
        this.numberOfPurchase=INITIAL_SIZE;
        this.sumPurchase=INITIAL_SIZE;

    }
    public Costumer(){

    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public int getNumberOfPurchase() {
        return numberOfPurchase;
    }

    public void setNumberOfPurchase(int numberOfPurchase) {
        this.numberOfPurchase = numberOfPurchase;
    }
    public void addNumberOfPurchase(int numberOfPurchaseToAdd){
        this.numberOfPurchase+=numberOfPurchaseToAdd;
    }
    public double getSumPurchase() {
        return this.sumPurchase;
    }

    public void setSumPurchase(double sumPurchase) {
        this.sumPurchase = sumPurchase;
    }
    public void addSumOfPurchase(double sumToAdd){
        this.sumPurchase+=sumToAdd;
    }
    public Date getDateOfLastPurchase() {
        return dateOfLastPurchase;
    }

    public void setDateOfLastPurchase(Date dateOfLstPurchase) {
        this.dateOfLastPurchase = dateOfLstPurchase;
    }

    @Override
    public void printHello() {
        System.out.println("Hello "+getFirstName()+" "+getLastName()+(this.isVip? " (VIP)":". "));
    }

    public String toString(){
        return super.toString()+(this.isVip? " (VIP)":". ")+"\n" +
                "Number of purchases: "+this.numberOfPurchase+"\n" +
                "Sum of all purchases: "+this.sumPurchase+"$\n" +
                "Last purchase date: "+this.dateOfLastPurchase;
    }
}
