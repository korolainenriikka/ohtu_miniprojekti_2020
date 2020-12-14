package kapistelykirjasto.ui;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

    private List<String> lines;
    private int indeksi;
    private ArrayList<String> prints;

    public StubIO(List<String> values) {
        this.lines = values;
        prints = new ArrayList<>();
    }

    public void print(String toPrint) {
        prints.add(toPrint);
    }

    public ArrayList<String> getPrints() {
        return prints;
    }

    public String readLine(String prompt) {
        print(prompt);
        if (indeksi < lines.size()) {
            String line = lines.get(indeksi);
            indeksi++;
            return line;
        }
        return "";
    }

    public boolean hasNextLine() {
        return indeksi < lines.size();
    }
}