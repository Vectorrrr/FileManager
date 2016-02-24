package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by igladush on 24.02.16.
 */
//todo sequence of attribute
public class Notice {
    public static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
    private final String EMPTY_STRING = "";
    private String name;
    private long telephone;
    private String address;
    private Date date;

    public Notice() {
        this.name = EMPTY_STRING;
        this.telephone = -1;
        this.address = EMPTY_STRING;
        this.date = Calendar.getInstance().getTime();
    }

    public Notice(String name, long telephone, String address) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.date = Calendar.getInstance().getTime();
    }
    public Notice(String name, long telephone, String address,Date date) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public long getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        if (EMPTY_STRING.equals(name)) {
            return EMPTY_STRING;
        }
        return name + " " + telephone + " " + address + " " + dateFormat.format(date)+"\n";
    }

}
