package logic;

import view.View;
import view.reader.ConsoleReader;

/**
 * Created by igladush on 24.02.16.
 */
public class Controller {
    private String userAns;
    private View view;
    private FileManager fileManager;
    private ConsoleReader consoleReader;

    public Controller() {
        view = new View();
        fileManager = new FileManager();
        consoleReader = new ConsoleReader();
    }

    public void run() {
        while (true) {
            view.showMainMenu();
            userAns = consoleReader.getString();
            switch (userAns) {
                case "1":
                    fileManager.addNotice();
                    break;
                case "0":
                    view.buy();
                    return;
                default:
                    view.incorrectData();
            }
        }
    }
}
