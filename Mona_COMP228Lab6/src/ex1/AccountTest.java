package ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account("A12345", 1000);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(account, "deposit", 200));
        transactionList.add(new Transaction(account, "withdraw", 500));
        transactionList.add(new Transaction(account, "withdraw", 800));
        transactionList.add(new Transaction(account, "deposit", 300));

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (Transaction transaction : transactionList) {
            executorService.execute(transaction);
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {

        }

        System.out.println("Final balance for account " + account.getAccountNumber() +
                " is " + account.getBalance());
    }
}