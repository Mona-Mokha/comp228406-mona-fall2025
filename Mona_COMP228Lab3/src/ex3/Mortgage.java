package ex3;

public abstract class Mortgage implements MortgageConstants {
    protected int mortgageNumber;
    protected String customerName;
    protected double mortgageAmount;
    protected double interestRate;
    protected int term;

   public Mortgage(int mortgageNumber, String customerName, double mortgageAmount, double interestRate, int term) {
       this.mortgageNumber = mortgageNumber;
       this.customerName = customerName;
       if (mortgageAmount > Max_Amount) {
           this.mortgageAmount = Max_Amount;
       } else {
           this.mortgageAmount = mortgageAmount;}
       this.interestRate = interestRate;

       if (term != SHORT_TERM && term != MEDIUM_TERM && term != LONG_TERM) {
           this.term = SHORT_TERM; }
       else {
           this.term = term; }

       }
    public String getMortgageInfo() {
        return "Bank name: " + BANK_NAME +
                "\nMortgage Number: " + mortgageNumber +
                "\nCustomer Name: " + customerName +
                "\nMortgage Amount: $" + String.format("%,.2f", mortgageAmount) +
                "\nInterest Rate: " + interestRate + "%" +
                "\nTerm: " + term + " year(s)" +
                "\nTotal Owed: $" + String.format("%,.2f", (mortgageAmount + (mortgageAmount * interestRate / 100 * term))) +
                "\n";
    }

}




