package view.writer;

import model.Notice;

import java.util.List;

/**
 * Created by van on 24.02.16.
 */
public interface NoticeWriter {
    void writeNotices(Notice... notices);

    void writeNotices(List<Notice> notices);
}
