package ex2;

import javax.swing.*;

public class LottoDriver {

    public static void main(String[] args) {

        String input = JOptionPane.showInputDialog("Welcome to Lotto Game\nChooses a number between 3 and 27 :");
        if (input == null) {
            JOptionPane.showMessageDialog(null, "Invalid input..Exiting the game");
            return;
        }
        int choice = Integer.parseInt(input);

        if (choice < 3 || choice > 27) {
            JOptionPane.showMessageDialog(null, "Number out of range");
            return;
        }


        boolean won = false;
        String summary = "";


        for (int i = 1; i <= 5; i++) {
            Lotto lotto = new Lotto();
            int sum = lotto.getSum();

            int[] nums = lotto.getNumbers();
            String numbersStr = "";
            for (int n : nums) {
                numbersStr += n + " ";
            }


            // Add this roll to the running summary
            summary += "Roll " + i + ": Lotto numbers are " + numbersStr + "| Sum = " + sum + "\n";

            // Show popup after each roll
            JOptionPane.showMessageDialog(null, summary);

            if (sum == choice) {
                JOptionPane.showMessageDialog(null, "You win");
                won = true;
                break;
            }
        }

        if (!won) {
            JOptionPane.showMessageDialog(null, "Sorry, you didn't win. Computer wins!");
        }
    }

}

