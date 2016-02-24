package view;

import view.writer.ConsoleWriter;

/**
 * Created by igladush on 24.02.16.
 */
public class View {
    ConsoleWriter consoleWriter;
    public View(){
        consoleWriter=new ConsoleWriter();
    }
    public void showMainMenu(){
        consoleWriter.writeString("If you want add notice type 1");
        consoleWriter.writeString("If you want exit type 0" );
    }
    public void buy(){
        consoleWriter.writeString("Good buy, bro!!!!)))");
    }
    public  void incorrectData(){
        consoleWriter.writeString("You input incorrect data!");
    }
}
