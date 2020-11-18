package kapistelykirjasto.ui;

public interface IO {
    void print(String toPrint);
    String readLine(String prompt);
    boolean hasNextLine();
}