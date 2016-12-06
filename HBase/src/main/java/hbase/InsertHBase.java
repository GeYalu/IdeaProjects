package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


import static hbase.RE2.get_IpData;
import static hbase.RE2.get_page;

/**
 * Created by geyalu on 2016/11/3.
 */
public class InsertHBase {


    public static void main(String[] args) {

        FileReader read = null;
        ArrayList result = new ArrayList();
        ArrayList resultpage = new ArrayList();
        String row;
        long lasttime = 12345678910L;
        int max = 10;
        int min = 1;
        int time = 0;
        Random random = new Random();


        try {
            read = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(read);

        Hbase.hbaseConn("log");


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


                    Hbase.addData(ip,datems,"url",column,value);
                    //addDataWithTS(ip, "log", "url", column, value, datems);
                    System.out.println(ip);

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Hbase.close();
    }


}


class Hbase {

    public static Configuration config = null;
    private static Logger log = Logger.getLogger(HBaseOperation.class);
    static Connection connection = null;
    static Table table = null;
    static HColumnDescriptor[] columnFamilies;
    static Put put;

    static {
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "192.168.2.240");
        //config.set("hbase.zookeeper.quorum", "10.163.165.35,10.251.35.198,10.144.59.125");
    }


    public static void hbaseConn( String tableName) {

        try {
            connection = ConnectionFactory.createConnection(config);

            table = connection.getTable(TableName.valueOf(tableName));
            columnFamilies = table.getTableDescriptor().getColumnFamilies();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void close() {

        try {
            if (null != table) {
                table.close();
            }
            if (null != connection && !connection.isClosed()) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void addData(String rowKey, long timestamp,String familycolumn, String[] column, String[] value) {



            put = new Put(Bytes.toBytes(rowKey), timestamp);
            for (int i = 0; i < columnFamilies.length; i++) {
                String familyName = columnFamilies[i].getNameAsString();
                if (familyName.equals(familycolumn)) {
                    for (int j = 0; j < column.length; j++) {
                        put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column[j]), Bytes.toBytes(value[j]));

                    }
                }
                try {
                    table.put(put);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Add Data Success!!!");
            }


    }

}