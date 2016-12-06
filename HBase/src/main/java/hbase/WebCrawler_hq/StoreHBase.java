package hbase.WebCrawler_hq;

/**
 * Created by geyalu on 2016/11/10.
 */
public class StoreHBase {


    public static String splitData(String result) {

        String [] data = result.split("\"");

        //System.out.println(data[0]);
        String value = data[1];

        return value;

    }
}



