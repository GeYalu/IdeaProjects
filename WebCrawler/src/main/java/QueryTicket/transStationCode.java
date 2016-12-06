package httpclient.QueryTicket;

import java.io.*;

/**
 * Created by geyalu on 2016/11/10.
 */
public class transStationCode {

    public static void main(String[] args) {
        try {
            FileReader read = new FileReader("12306StationCode.txt");
            BufferedReader br = new BufferedReader(read);
            String row;
            int rownum = 1;
            FileWriter fw1 = new FileWriter("action1");

            while ((row = br.readLine()) != null) {
                String[] station = row.split(":");
                fw1.append("hashMap.put(\"" + station[0].trim() + "\", \"" + station[1].trim() + "\");" + "\r\n");
                rownum++;
            }
            fw1.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
