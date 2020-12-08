package kapistelykirjasto.ui.cli;

import java.util.Arrays;
import java.util.List;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Course;
import kapistelykirjasto.domain.Entry;
import kapistelykirjasto.ui.IO;

public class Util {
	
	public static boolean isValidIndex(String input, List<?> objects) {
        return parsable(input) && Integer.valueOf(input) > 0 && Integer.valueOf(input) <= objects.size();
    }
	
	public static void printEnumeratedList(IO io, List<?> objects, String emptyMessage) {
        if (emptyMessage != "" && objects.isEmpty()) {
            io.print(emptyMessage);
        }
        for (int i = 0; i < objects.size(); i++) {
            io.print("[" + (i + 1) + "]: " + objects.get(i).toString());
        }
    }
	
	public static void printList(IO io, List<?> objects, String emptyMessage) {
        if (emptyMessage != "" && objects.isEmpty()) {
            io.print(emptyMessage);
        }
        for (int i = 0; i < objects.size(); i++) {
            io.print(objects.get(i).toString());
        }
    }
	
	private static boolean parsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
