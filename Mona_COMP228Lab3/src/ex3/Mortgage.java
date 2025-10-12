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
           this.mortgageAmount = mortgageNumber;}
       this.interestRate = interestRate;
       if (term != SHORT_TERM && term != MEDIUM_TERM && term != LONG_TERM) {
           this.term = SHORT_TERM; }
       else {
           this.term = MEDIUM_TERM; }

       }
       public String getMortgageInfo() {
           return "Bank name: " + BANK_NAME +
                   "\nMortgage Number: " + mortgageNumber +
                   "\nCustomer Name: " + customerName +
                   "\nMortgage Amount: " + mortgageAmount +
                   "\nInterest Rate: " + interestRate + "%" +
                   "\nTerm: " + term + " years" +
                   "\nTotal Owed: $" + (mortgageAmount + (mortgageAmount * interestRate / 100 * term)) + "\n";
       }
       }




