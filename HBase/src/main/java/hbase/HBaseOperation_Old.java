package hbase;

/**
 * Created by geyalu on 2016/11/3.
 */


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;


/**
 * Created by geyalu on 2016/11/2.
 */
public class HBaseOperation_Old {

    private static Configuration conf = null;
    private static HBaseAdmin admin = null;
    private static Logger log = Logger.getLogger(HBaseOperation_Old.class);

    @SuppressWarnings("deprecation")

    public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
        conf = HBaseConfiguration.create();
        // 初始化log模块，这是hbase-client的依赖带来的额外工作
        initLog();
        // 设定conf，指定ZooKeeper的位置
        //conf.set("hbase.zookeeper.property.", "2181");
        conf.set("hbase.zookeeper.quorum", "115.28.202.71,115.28.78.15,121.42.158.107");
        // 建立一个角色，登陆到ZooKeeper上
        admin = new HBaseAdmin(conf);
        log.info("Log in");


        /*String[] families = {"cf1", "cf2"};
        createTable("test", families);*/

/*        String [] column = {"name","age"};
        String [] values = {"name1","24"};
        insertData("mygirls","base_info","002",column,values);
        insertData("mygirls","base_info","003",column,values);
        insertData("mygirls","base_info","004",column,values);*/

        //getResult("test", "001");

        //getResultScann("test");

        //getResultScanByRowKey("test","001","003");

        //getResultByColumn("test","001","cf1","c1");

        //getResultByVersion("test","002","cf1","c1");

        //deleteColumn("test","001","cf1","c1");

        //deleteAllColumn("test","001");

        //deleteTable("test");

        testScan();

        admin.close();
    }


    private static void createTable(String tableName, String[] families) throws IOException {

        TableName name = TableName.valueOf(tableName);
        HTableDescriptor desc = new HTableDescriptor(name);

        for (int iFamily = 0; iFamily < families.length; iFamily++) {
            HColumnDescriptor columnFamilyDesc = new HColumnDescriptor(families[iFamily]);
            columnFamilyDesc.setMaxVersions(5);
            desc.addFamily(columnFamilyDesc);
        }
        admin.createTable(desc);
    }


    public static void insertData(String tableName, String columnFamily, String rowKey, String[] column1, String[] value1) throws IOException {

        HTable table = new HTable(conf, Bytes.toBytes(tableName));

        Put put = new Put(Bytes.toBytes(rowKey));

        HColumnDescriptor[] columnFamilies = table.getTableDescriptor() // 获取所有的列族
                .getColumnFamilies();

        for (int i = 0; i < columnFamilies.length; i++) {
            String familyName = columnFamilies[i].getNameAsString(); // 获取列族名
            if (familyName.equals(columnFamily)) { // article列族put数据
                for (int j = 0; j < column1.length; j++) {
                    put.add(Bytes.toBytes(familyName), Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));
                }
            }

        }
        table.put(put);
        System.out.println("add data Success!");

    }


    public static Result getResult(String tableName, String rowKey) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTable table = new HTable(conf, Bytes.toBytes(tableName));// 获取表
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.println("family:" + Bytes.toString(kv.getFamily()));
            System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
            System.out.println("value:" + Bytes.toString(kv.getValue()));
            System.out.println("Timestamp:" + kv.getTimestamp());
            System.out.println("-------------------------------------------");
        }
        table.close();
        return result;
    }


    public static void getResultScan(String tableName) throws IOException {
        Scan scan = new Scan();
        ResultScanner rs = null;
        HTable table = new HTable(conf, Bytes.toBytes(tableName));
        try {
            rs = table.getScanner(scan);
            for (Result r : rs) {
                for (KeyValue kv : r.list()) {
                    System.out.println("row:" + Bytes.toString(kv.getRow()));
                    System.out.println("family:" + Bytes.toString(kv.getFamily()));
                    System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
                    System.out.println("value:" + Bytes.toString(kv.getValue()));
                    System.out.println("timestamp:" + kv.getTimestamp());
                    System.out.println("-------------------------------------------");
                }
            }
        } finally {
            rs.close();
            table.close();
        }
    }

    //查询结果 包含start rowKey 不包含stop rowKey
    public static void getResultScanByRowKey(String tableName, String start_rowkey, String stop_rowkey) throws IOException {
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(start_rowkey));
        scan.setStopRow(Bytes.toBytes(stop_rowkey));
        ResultScanner rs = null;
        HTable table = new HTable(conf, Bytes.toBytes(tableName));
        try {
            rs = table.getScanner(scan);
            for (Result r : rs) {
                for (KeyValue kv : r.list()) {
                    System.out.println("row:" + Bytes.toString(kv.getRow()));
                    System.out.println("family:" + Bytes.toString(kv.getFamily()));
                    System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
                    System.out.println("value:" + Bytes.toString(kv.getValue()));
                    System.out.println("timestamp:" + kv.getTimestamp());
                    System.out.println("-------------------------------------------");
                }
            }
        } finally {
            rs.close();
            table.close();
        }
    }

    public static void getResultByColumn(String tableName, String rowKey, String familyName, String columnName) throws IOException {
        HTable table = new HTable(conf, Bytes.toBytes(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName)); // 获取指定列族和列修饰符对应的列
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.println("family:" + Bytes.toString(kv.getFamily()));
            System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
            System.out.println("value:" + Bytes.toString(kv.getValue()));
            System.out.println("Timestamp:" + kv.getTimestamp());
            System.out.println("-------------------------------------------");
        }
        table.close();

    }



    public static void getResultByVersion(String tableName, String rowKey, String familyName, String columnName) throws IOException {
        HTable table = new HTable(conf, Bytes.toBytes(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
        get.setMaxVersions(5);
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.println("family:" + Bytes.toString(kv.getFamily()));
            System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
            System.out.println("value:" + Bytes.toString(kv.getValue()));
            System.out.println("Timestamp:" + kv.getTimestamp());
            System.out.println("-------------------------------------------");
        }
        /*
         * List<?> results = table.get(get).list(); Iterator<?> it =
         * results.iterator(); while (it.hasNext()) {
         * System.out.println(it.next().toString()); }
         */
    }



    public static void deleteColumn(String tableName, String rowKey, String familyName, String columnName) throws IOException {
        HTable table = new HTable(conf, Bytes.toBytes(tableName));
        Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
        deleteColumn.deleteColumns(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
        table.delete(deleteColumn);
        System.out.println(familyName + ":" + columnName + "is deleted!");
    }

    /*
     * 删除指定的列
     */
    public static void deleteAllColumn(String tableName, String rowKey) throws IOException {
        HTable table = new HTable(conf, Bytes.toBytes(tableName));
        Delete deleteAll = new Delete(Bytes.toBytes(rowKey));
        table.delete(deleteAll);
        System.out.println("all columns are deleted!");
    }

    /*
     * 删除表
     */
    public static void deleteTable(String tableName) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
        System.out.println(tableName + "is deleted!");
    }


    /**
     * 多种过滤条件的使用方法
     * @throws Exception
     */




    public static void testScan() throws IOException {
        HTable table = new HTable(conf,Bytes.toBytes("mygirls"));
        Scan scan = new Scan(Bytes.toBytes("001"), Bytes.toBytes("003"));

        //前缀过滤器----针对行键
        //Filter filter = new PrefixFilter(Bytes.toBytes("101.226.167"));

        Filter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("003"))); // OK 筛选出匹配的所有的行

        scan.setFilter(rf);
        //scan.addFamily(Bytes.toBytes("url"));
        ResultScanner scanner = table.getScanner(scan);

        for(Result r : scanner){

             for(KeyValue kv : r.list()){
                 System.out.println("row:" + Bytes.toString(kv.getRow()));
                 System.out.println("family:" + Bytes.toString(kv.getFamily()));
                 System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
                 System.out.println("value:" + Bytes.toString(kv.getValue()));
                 System.out.println("timestamp:" + kv.getTimestamp());
                 System.out.println("-------------------------------------------");
             }

            //直接从result中取到某个特定的value
            //byte[] value = r.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("name"));
            //System.out.println(new String(value));
        }
        table.close();
    }



    private static void initLog() {
        BasicConfigurator.configure();
        log.setLevel(Level.OFF);

    }
}
