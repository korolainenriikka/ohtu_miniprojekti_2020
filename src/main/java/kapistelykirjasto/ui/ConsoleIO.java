package kapistelykirjasto.ui;

import java.util.Scanner;

public class ConsoleIO implements IO {
    private Scanner scanner = new Scanner(System.in);

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    public String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public boolean hasNextLine() {
        return true;
    }
}