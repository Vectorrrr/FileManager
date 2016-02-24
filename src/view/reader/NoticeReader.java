package view.reader;

import model.Notice;

import java.io.Closeable;

/**
 * Created by igladush on 24.02.16.
 */
public interface NoticeReader extends AutoCloseable,Closeable {
    Notice getNotice();
    boolean canRead();
}
