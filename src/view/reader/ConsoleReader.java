package view.reader;

import java.util.Scanner;

/**
 * Created by igladush on 24.02.16.
 */
public class ConsoleReader implements Reader {
    Scanner scanner;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }
    @Override
    public String getString() {
        return scanner.nextLine();
    }

    @Override
    public long getLong() {
        long ans = -1;
        String s;
        while (ans == -1) {
            try {
                s = scanner.nextLine();
                ans = Long.parseLong(s);
            } catch (NumberFormatException e) {
                System.out.println("You input incorrect data! Input telephone again");

            }
        }

        return ans;
    }

    public boolean canRead() {
        return scanner.hasNext();
    }


}

