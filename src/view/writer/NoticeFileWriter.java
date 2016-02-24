package view.writer;

import model.Notice;

import java.io.*;
import java.util.List;

/**
 * Created by igladush on 24.02.16.
 */
public class NoticeFileWriter implements NoticeWriter,AutoCloseable,Closeable {
    private final String CLOSE_ERROR = "I can't close file";
    private final String WRITE_ERROR = "I can't write in file";
    FileOutputStream fileOutputStream;

    public NoticeFileWriter(File file, boolean append) throws FileNotFoundException {
        fileOutputStream = new FileOutputStream(file, append);
    }

    @Override
    public void writeNotices(Notice... notices) {
        for (Notice notice : notices) {
            try {
                fileOutputStream.write(notice.toString().getBytes());
            } catch (IOException e) {
                System.err.println(WRITE_ERROR);
            }
        }

    }
    @Override
    public void writeNotices(List<Notice> notices) {
        for (Notice notice : notices) {
            try {
                fileOutputStream.write(notice.toString().getBytes());
            } catch (IOException e) {
                System.err.println(WRITE_ERROR);
            }
        }
    }

    @Override
    public void close() {
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            System.err.println(CLOSE_ERROR);
        }
    }
}
