package view.writer;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by igladush on 24.02.16.
 */
public class FileWriters implements Writer,AutoCloseable,Closeable {

    private FileWriter fw;

    public FileWriters(File f,boolean append) throws IOException {
        fw = new FileWriter(f,append);
    }

    @Override
    public void writeString(String... strings) throws IOException {
        for(String s: strings){
            fw.write(s);
        }
    }

    @Override
    public void close() throws IOException {
            fw.close();

    }


}
