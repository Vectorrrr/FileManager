package tester;

import logic.FileManager;
import model.Notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by van on 24.02.16.
 */
public class Tester {
    private static final int HOW_MANY_ADD=10000;
    private static final int LENGTH_PULL_NUMBER=50;

    public static void main(String[] args){
        FileManager fileManager=new FileManager();
        Random rm=new Random();
        List<String> pullNames=new ArrayList<String>(){{
            add("Vanya");
            add("Petya");
           // add("Vasya");
           // add("Vova");
            //add("Tolya");
           // add("Kolya");
        }};
        List<String> pullAddress=new ArrayList<String>(){{
            add("Lenina");
            add("Univer");
            add("Celinogradskay");
            add("Sumskaya");
            add("Vesnina");
            add("sadovaya");
        }};
        List<Long> pullPhoneNumber=new ArrayList<>();
        for(int i=0;i<LENGTH_PULL_NUMBER;i++){

            pullPhoneNumber.add(rm.nextLong());
        }
        for(int i=0;i<HOW_MANY_ADD;++i){
            int temp =Math.abs(rm.nextInt());
            String address=pullAddress.get(temp%pullAddress.size());
            String name=pullNames.get(temp%pullNames.size());
            Long telephone=pullPhoneNumber.get(temp%LENGTH_PULL_NUMBER);
            fileManager.addNotice(new Notice(name,telephone,address));
        }

    }
}
