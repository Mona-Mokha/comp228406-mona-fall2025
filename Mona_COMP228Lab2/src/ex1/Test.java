package ex1;

import javax.swing.*;
import java.util.Random;

public class Test {

    private String[] questions = {
            "Q1: Which of the following is true about instance variables?\nA) Belong to each object\nB) Shared among all objects\nC) Cannot be modified\nD) Only used in static methods",
            "Q2: Which IDE feature helps you automatically fix syntax errors?\nA) Debugger\nB) Code completion\nC) Compiler\nD) JVM",
            "Q3: What does JVM stand for?\nA) Java Virtual Method\nB) Java Virtual Machine\nC) Java Variable Manager\nD) Java Visual Model",
            "Q4: Which IDE can be used for Java?\nA) Eclipse\nB) IntelliJ IDEA\nC) NetBeans\nD) All of the above",
            "Q5: In Java, what keyword is used to define a class?\nA) def\nB) function\nC) class\nD) object",
            "Q6: Which method is the entry point in Java programs?\nA) run()\nB) main()\nC) start()\nD) execute()",
            "Q7: Which symbol is used for single-line comments?\nA) /* */\nB) //\nC) #\nD) <!-- -->"

    };
    private char[] answers =  {'A', 'B', 'B', 'D', 'C', 'B', 'B'};
    private int correct = 0;
    private int incorrect = 0;
    private Random randomObject = new Random();

    public void inputAnswer() {
        for (int i = 0; i < questions.length; i++) {
            String response = JOptionPane.showInputDialog(null,questions[i]);
            if (response == null) return;
            char answer = Character.toUpperCase(response.charAt(0));
            checkAnswer(answer,answers[i]);
        }
        int percentage = (correct * 100)/questions.length;
        JOptionPane.showMessageDialog(null,"Correct answers:" + correct + "\nIncorrect answers:" + incorrect + "\nPercentage:" + percentage);
    }
    private void checkAnswer(char userAnswer, char correctAnswer) {
        if(userAnswer == correctAnswer) {
            JOptionPane.showMessageDialog(null,generateMessage(true));
            correct++;
        }
        else {
            JOptionPane.showMessageDialog(null,generateMessage(false) + "\nCorrect Answer:" + correctAnswer);
            incorrect++;
        }
    }
    private String generateMessage(boolean isCorrect) {
        switch (randomObject.nextInt(4)) {
            case 0:
                return isCorrect ? "Excellent!" : "No. Please try again.";
            case 1:
                return isCorrect ? "Good!" : "Wrong. Try once more.";
            case 2:
                return isCorrect ? "Keep up the good work!" : "Don't give up!";
            default:
                return isCorrect ? "Nice work!" : "No. Keep trying..";
        }
    }

}
