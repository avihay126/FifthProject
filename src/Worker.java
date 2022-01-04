public abstract class Worker extends User{

    private String rank;

    public Worker(String firstName, String lastName, String userName, String password,double amountOfDiscount,String rank) {
        super(firstName, lastName, userName, password,amountOfDiscount);
        this.rank=rank;
    }
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String toString(){
        return super.toString();
    }
}
