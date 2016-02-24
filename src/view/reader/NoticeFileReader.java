package view.reader;

import model.Notice;

import java.io.*;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by igladush on 24.02.16.
 */
public class NoticeFileReader implements NoticeReader {
    private final String EMPTY_STRING="";
    private final String ERROR_READ="I can't read next line";
    private final String PARSE_DATE_ERROR="I can't parse date";

    private BufferedReader bufferReader;
    private String nextLine;

    public NoticeFileReader(File file) throws IOException {
        bufferReader= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        nextLine=bufferReader.readLine();

    }

    @Override
    public Notice getNotice() {
        String[] s = nextLine.split(" ");
        String address = s[0];
        long telephone = Long.parseLong(s[1]);
        String name = s[2];
        Date date = new Date();
        try {
            date = Notice.dateFormat.parse(s[3]);
        } catch (ParseException e) {
            System.err.println(PARSE_DATE_ERROR);
        }
        getNextLine();
        return new Notice(address, telephone, name, date);
    }

    private void getNextLine(){
        try {
            nextLine=bufferReader.readLine();
        } catch (IOException e) {
            System.err.println(ERROR_READ);
        }
    }
    @Override
    public void close() throws IOException {
        bufferReader.close();

    }
    @Override
    public boolean canRead(){
        if(nextLine==null){
            return false;
        }
        return !EMPTY_STRING.equals(nextLine);
        // todo but I want write that string
        // return nextLine != null && !EMPTY_STRING.equals(nextLine);
    }
}
