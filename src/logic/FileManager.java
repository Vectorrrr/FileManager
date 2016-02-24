package logic;

import model.Notice;
import view.reader.ConsoleReader;
import view.reader.NoticeFileReader;
import view.reader.Reader;
import view.writer.ConsoleWriter;
import view.writer.NoticeFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igladush on 24.02.16.
 */
public class FileManager {

    private final String DEFAULT_PATH = "lib";
    private final String ERROR_READ = "I can't read file";
    private final String EXPANSION_FILE = ".base";
    private final String EXPANSION_COMPACTED = ".compacted";
    private final String WRITE_ERROR = "I have error when i write notice";

    private final int FILE_MAX_SIZE = 100000;
    private final int FILE_BEFORE_REPACKING = 15;

    private int countOfCompacted = 1;
    private int countOfFile = 1;

    private File directory;
    private File workFile;
    private ConsoleWriter consoleWriter;
    private Reader reader;


    //todo May be create folder in constructor
    public FileManager() {
        directory = new File(DEFAULT_PATH);
        workFile = new File(DEFAULT_PATH + "/" + countOfFile + EXPANSION_FILE);
        consoleWriter = new ConsoleWriter();
        reader = new ConsoleReader();
    }

    public void addNotice(Notice notice) {
        writeNotice(notice);
    }

    public void addNotice() {
        Notice notice = getNotice();
        writeNotice(notice);
    }

    private void writeNotice(Notice notice) {
        checkDir();
        checkSizeFile();
        try (NoticeFileWriter noticeFileWriter = new NoticeFileWriter(workFile, true)) {
            noticeFileWriter.writeNotices(notice);
        } catch (IOException e) {
            System.err.println(WRITE_ERROR);
        }

    }

    private void checkSizeFile() {
        boolean alreadyRepacking = false;
        while (workFile.exists() && workFile.length() > FILE_MAX_SIZE) {
            if (countOfFile%FILE_BEFORE_REPACKING!=0 && !alreadyRepacking) {
                doRepacking();
                //countOfFile = 1;
                alreadyRepacking = true;
            }
            countOfFile++;
            workFile = new File(DEFAULT_PATH + "/" + countOfFile + EXPANSION_FILE);
            consoleWriter.writeString("Trying write in file" + workFile.getAbsolutePath());
        }
        consoleWriter.writeString("Write in file" + workFile.getAbsolutePath());
    }

    private void doRepacking() {
        File f ;
        List<Notice> content = new ArrayList<>();
        while (countOfFile > 0) {
            f = new File(DEFAULT_PATH + "/" + countOfFile + EXPANSION_FILE);
            content.addAll(getFileContent(f));
            countOfFile--;

        }
        countOfFile = 1;
        content = retainLastRecord(content);
        createNewCompacted(content);
    }

    private void createNewCompacted(List<Notice> notices) {
        File compacted = new File(DEFAULT_PATH + "/" + countOfCompacted + EXPANSION_COMPACTED);
        while (compacted.exists()) {
            countOfCompacted++;
            compacted = new File(DEFAULT_PATH + "/" + countOfCompacted + EXPANSION_COMPACTED);
        }
        try (NoticeFileWriter noticeFileWriter = new NoticeFileWriter(compacted, true)) {
            noticeFileWriter.writeNotices(notices);
            consoleWriter.writeString("Create new compacted" + compacted.getAbsolutePath());
        } catch (IOException e) {
            System.err.println(WRITE_ERROR);
        }

    }

    private List<Notice> retainLastRecord(List<Notice> content) {
        List<Notice> l = new ArrayList<>();
        boolean alreadyContains;
        for (int i = 0; i < content.size(); i++) {
            alreadyContains = false;
            for (int j = 0; j < l.size(); j++) {
                if (l.get(j).getName().equals(content.get(i).getName())) {
                    alreadyContains = true;
                }
            }
            if (!alreadyContains) {
                l.add(content.get(i));
            }
        }
        return l;
    }

    private ArrayList<Notice> getFileContent(File f) {
        ArrayList<Notice> result = new ArrayList<>();
        try (NoticeFileReader noticeFileReader = new NoticeFileReader(f)) {
            while (noticeFileReader.canRead()) {
                result.add(noticeFileReader.getNotice());
            }
        } catch (IOException e) {
            consoleWriter.writeString(ERROR_READ);
        }
        return result;
    }
    //todo May be make class NoticeReaderFromConsole
    private Notice getNotice() {
        String address;
        String name;
        long telephone;

        consoleWriter.writeString("Input address");
        address = reader.getString();

        consoleWriter.writeString("Input name");
        name = reader.getString();

        consoleWriter.writeString("Input telephone");
        telephone = reader.getLong();

        return new Notice(address, telephone, name);
    }

    private void checkDir() {
        if (!directory.exists()) {
            createDirs(directory);
        }

    }

    private void createDirs(File f) {
        consoleWriter.writeString("Create new file", "This is " + workFile);
        f.mkdirs();
    }
}
