public class Worker extends Costumer{

    private String rank;
    private double amountOfDiscount;

    public Worker(String firstName, String lastName, String userName, String password,boolean isVip,double amountOfDiscount,String rank) {
        super(firstName, lastName, userName, password,isVip);
        this.rank=rank;
        this.amountOfDiscount=amountOfDiscount;
    }
    public Worker(){

    }
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public double getAmountOfDiscount() {
        return amountOfDiscount;
    }

    public void setAmountOfDiscount(double amountOfDiscount) {
        this.amountOfDiscount = amountOfDiscount;
    }

    public String toString(){
        return getFirstName()+" "+getLastName()+" ("+this.rank+")!";
    }
}
