package ex1;

public class Transaction implements Runnable {
    private Account account;
    private String transactionType;
    private double amount;

    public Transaction(Account account, String transactionType, double amount) {
        this.account = account;
        this.transactionType = transactionType.toLowerCase();
        this.amount = amount;
    }

    @Override
    public void run() {
        if ("deposit".equals(transactionType)) {
            account.deposit(amount);
        } else if ("withdraw".equals(transactionType)) {
            account.withdraw(amount);
        } else {
            System.out.println("Invalid transaction type: " + transactionType);
        }
    }
}