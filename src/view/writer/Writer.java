package view.writer;

import java.io.IOException;

/**
 * Created by igladush on 24.02.16.
 */
public interface Writer  {
    void writeString(String... strings) throws IOException;
}
