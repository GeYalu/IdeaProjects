package hbase.WebCrawler_hq;

import hbase.HBaseOperation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import java.io.IOException;


public class Insert_hq_HBase {

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


        public static void addData(String rowKey, String familycolumn, String[] column, String[] value) {

            put = new Put(Bytes.toBytes(rowKey));
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