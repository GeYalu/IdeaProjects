package hbase.WebCrawler_hq;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


/**
 * Created by geyalu on 2016/11/10.
 */
public class ScrapData {

    public static void main(String[] args) throws IOException {

        ArrayList shareList = new ArrayList();

        shareList.add("sh601006");
        shareList.add("sh000001");
        shareList.add("sh600066");

        String url ="http://hq.sinajs.cn/list=sh000001";

        Iterator it = shareList.iterator();

        while (it.hasNext()){

            String newUrl = url.replace("sh000001",it.next().toString());

            System.out.println(Scrap_hq(newUrl));
        }
    }


    static String Scrap_hq(String url) {

        BufferedReader rd = null;
        String result =null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);


        try {

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            rd = new BufferedReader(new InputStreamReader(entity.getContent(),"GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String line ;
        try {
            while ((line = rd.readLine()) != null) {
                result = new String(line.getBytes(), "UTF-8");
                //System.out.println(result);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
}


