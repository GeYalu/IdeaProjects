package hbase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static hbase.HBaseOperation.addDataWithTS;
import static hbase.RE2.get_IpData;
import static hbase.RE2.get_page;


/**
 * Created by geyalu on 2016/11/3.
 */
public class main {

    public static void main(String[] args) {

        FileReader read = null;

        try {
            read = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(read);

        ArrayList result = new ArrayList();
        ArrayList resultpage = new ArrayList();
        String row;
        long lasttime = 12345678910L;

        int max = 10;
        int min = 1;
        Random random = new Random();

        try {
            while ((row = br.readLine()) != null) {

                result = get_IpData(row);

                String ip = result.get(0).toString().trim();
                String date = result.get(2).toString();
                String url = result.get(3).toString();
                long datems = TransDateToMS.tranDate(date);
                int s = 0;
                String pageURL = null;
                String pageNUM = null;


                if (datems == lasttime) {

                    s = random.nextInt(max) % (max - min + 1) + min;

                }

                lasttime = datems;
                datems = datems + s;


                resultpage = get_page(url);

                if (resultpage.size() == 2) {
                    pageNUM = resultpage.get(0).toString();
                    pageURL = resultpage.get(1).toString();

                }


                //System.out.println("----------------------");
                //System.out.println(ip + " " + datems + " " + url+" "+pageURL+" "+" "+pageNUM);

                if (pageNUM != null && pageNUM != null) {

                    String[] column = {pageURL};
                    String[] value = {pageNUM};

                    addDataWithTS(ip, "log", "url", column, value, datems);

                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
