package ex3;

public class PersonalMortgage extends Mortgage {
    public PersonalMortgage(int mortgageNumber,  String customerName, double mortgageAmount, double primeRate, int term) {
        super(mortgageNumber, customerName, mortgageAmount, primeRate + 2.0, term);
    }
}
