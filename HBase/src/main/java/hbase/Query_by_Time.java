package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by geyalu on 2016/11/9.
 */
public class Query_by_Time {

    public static void main(String[] args) {

        String beginTime = "20151230_06_00";
        String endTime = "20151232_07_01";

        //Filter filter = new PrefixFilter(Bytes.toBytes(beginTime));
        FirstKeyOnlyFilter filter = new FirstKeyOnlyFilter();

        HbaseOperation_query.scanResult("log", filter, beginTime, endTime);

    }
}

class HbaseOperation_query {

    public static Configuration config = null;
    private static Logger log = Logger.getLogger(HBaseOperation.class);
    static Connection connection = null;
    static Table table = null;
    static HColumnDescriptor[] columnFamilies;
    static Put put;

    static Set resultSet = new HashSet();

    static {
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "192.168.2.240");
        //config.set("hbase.zookeeper.quorum", "10.163.165.35,10.251.35.198,10.144.59.125");
    }

    public static void hbaseConn(String tableName) {

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

    public static void scanResult(String tableName, Filter filter, String beginRowKey, String endRowKey) {

        Connection connection = null;
        Scan scan = new Scan();

        scan.setFilter(filter);
        scan.setStartRow(Bytes.toBytes(beginRowKey));
        scan.setStopRow(Bytes.toBytes(endRowKey));

        ResultScanner rs = null;
        Table table = null;

        try {
            connection = ConnectionFactory.createConnection(config);
            table = connection.getTable(TableName.valueOf(tableName));
            rs = table.getScanner(scan);
            for (Result result : rs) {

                String rowKey = Bytes.toString(result.getRow());

                System.out.println("RowKey: " + rowKey);

                String[] rowkeyArray = rowKey.split("_");
                String ip = rowkeyArray[4];

                System.out.println("Date:" + rowkeyArray[0]);
                System.out.println("H:" + rowkeyArray[1] + " M:" + rowkeyArray[2] + " S:" + rowkeyArray[3]);
                System.out.println("ip:" + rowkeyArray[4]);
                System.out.println(rowkeyArray[5]);

                resultSet.add(ip);

            }

            System.out.println("Result Size:" + resultSet.size());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != rs) {
                rs.close();
            }
            try {
                if (null != table) {
                    table.close();
                }
                if (null != connection && !connection.isClosed()) {
                    System.out.println("scanResult is close");
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
