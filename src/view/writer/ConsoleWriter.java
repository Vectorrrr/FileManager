package view.writer;

/**
 * Created by igladush on 24.02.16.
 */
public class ConsoleWriter implements Writer {

    @Override
    public void writeString(String... strings)  {
        for(String s: strings){
            System.out.println(s);
        }
    }
}
