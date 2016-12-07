package Crawler_12306.QueryTicket;

import java.io.*;
import java.util.HashMap;

/**
 * 读取12306站点信息文件，存入HashMap 并return
 */
public class getStationCodeMap {
    public static HashMap getStationCodeMap(String path) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String row;
        int rownum = 0;
        try {
            FileReader read = new FileReader(path);
            BufferedReader br = new BufferedReader(read);

            while ((row = br.readLine()) != null) {
                String[] station = row.split(":");
                hashMap.put(station[0].trim(), station[1].trim());
                rownum++;
            }
            br.close();
            read.close();
            System.out.println("Station Data File Row: " + rownum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}
