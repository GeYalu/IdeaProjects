package QueryTicket;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * Created by geyalu on 2016/11/10.
 */
public class Train {

    static public String QueryLeftTicket(String url){

        BufferedReader rd = null;
        HttpClient client = null;
        HttpResponse response = null;
        HttpGet request ;


        try {
            client = new SSLClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request = new HttpGet(url);
        request.addHeader("Accept", "*/*");
        request.addHeader("Accept-Encoding", "gzip, deflate, sdch, br");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
        request.addHeader("Connection", "keep-alive");
        //request.addHeader("Host", "kyfw.12306.cn");

        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Header responseEncoding = response.getEntity().getContentEncoding();

        //System.out.println("Encoding : " + responseEncoding);
        //System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        //如果网页返回采用gzip压缩，则使用GZIPInputStream，如果否，则直接流入InputStreamReader
        //12306 查询页面，如果查询结果只有一列车，则不进行gzip压缩，若大于等于2列车，则使用gzip压缩

        //Response Headers : Content-Encoding:gzip
        //System.out.println(responseEncoding.getValue());

        try {
            if (responseEncoding == null) {
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            } else if ("gzip".equals(responseEncoding.getValue())) {
                GZIPInputStream gzipInputStream = new GZIPInputStream(response.getEntity().getContent());
                rd = new BufferedReader(new InputStreamReader(gzipInputStream));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        StringBuffer result = new StringBuffer();
        String line ;
        try {
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }
}


