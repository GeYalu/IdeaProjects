package hbase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by geyalu on 2016/11/3.
 */
public class TransDateToMS {


    public static long tranDate(String line) {


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss", Locale.ENGLISH);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = formatter.parse(line);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println("转换后的日期格式：" + date.getTime());
        return date.getTime();

    }

    public static String tranDatetoNormal(String line) {


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss", Locale.ENGLISH);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        Date date = null;

        try {
            date = formatter.parse(line);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newdate = format.format(date);

        //System.out.println("转换后的日期格式：" + date.getTime());
        //System.out.println(date.getYear()+" "+date.getMonth());

        return newdate;

    }

    public static void main(String[] args) {

        String line = "31/Dec/2015:00:02:04";
        System.out.println(tranDatetoNormal(line));
    }


}
