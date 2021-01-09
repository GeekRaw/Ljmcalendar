package com.jnu.ljmcalendar.dataprocessor;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBank {
    static private ArrayList<Record> receive_records = new ArrayList<>();
    static private ArrayList<Record> give_records = new ArrayList<>();
    static private ArrayList<Record> date_records = new ArrayList<>();

    static private String BOOK_FILE_RECEIVE = "receive.txt";
    static private String TYPE_RECEIVE = "收礼";
    static private String TYPE_GIVE = "随礼";
    static private String BOOK_FILE_GIVE = "give.txt";
    static private String date;

    public static void Save(Context context, String type){
        // 把对象序列化到文件
        ObjectOutputStream oos = null;
        try {
            if(type.equals(TYPE_RECEIVE)){
                oos = new ObjectOutputStream(context.openFileOutput(BOOK_FILE_RECEIVE,Context.MODE_PRIVATE));
                oos.writeObject(receive_records);
            }else{
                oos = new ObjectOutputStream(context.openFileOutput(BOOK_FILE_GIVE,Context.MODE_PRIVATE));
                oos.writeObject(give_records);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void Load(Context context, String type){
        ObjectInputStream ois = null;
        // 反序列化到内存
        try {
            if(type.equals(TYPE_RECEIVE)){
                ois = new ObjectInputStream(context.openFileInput(BOOK_FILE_RECEIVE));
            }else{
                ois = new ObjectInputStream(context.openFileInput(BOOK_FILE_GIVE));
            }

            try {
                if(type.equals(TYPE_RECEIVE)){
                    receive_records = (ArrayList<Record>) ois.readObject();
                }else{
                    give_records = (ArrayList<Record>) ois.readObject();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Record> getRecords(String type){
        if(type.equals(TYPE_RECEIVE)){
            return receive_records;
        }else{
            return give_records;
        }
    }

    public static ArrayList<Record> getDateRecords(Context context, String date) {
        Load(context, TYPE_RECEIVE);
        Load(context, TYPE_GIVE);
        date_records.clear();
        for (Record record : receive_records) {
            if (record.getDate().equals(date)) {
                date_records.add(record);
            }
        }
        for (Record record : give_records) {
            if (record.getDate().equals(date)) {
                date_records.add(record);
            }
        }
        return date_records;
    }

    public static void setDate(String currentDay){
        date = currentDay;
        //Log.d("hello", "setDate: "+date);
    }
    public static String getDate(){
        //Log.d("hello", "getDate: "+date);
        return date;
    }
}
