package Crawler_12306.Crawl_all;

import Crawler_12306.QueryTicket.StartQueryTicket;
import Crawler_12306.QueryTicket.getStationCodeMap;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by geyalu on 2016/11/12.
 */

public class TraverseStation {
    private static String StationCodeFilePath = "12306_StationCode1.txt";

    public static ArrayList getCombineStation() {

        ArrayList combine_StationList = new ArrayList();
        ArrayList StationCodeList = getStationCodeList();
        int count = 0;
        for (int i = 0; i < StationCodeList.size(); i++) {
            for (int j = i + 1; j < StationCodeList.size(); j++) {
                String[] Combine2Station = new String[2];
                Combine2Station[0] = StationCodeList.get(i).toString();
                Combine2Station[1] = StationCodeList.get(j).toString();
                combine_StationList.add(Combine2Station);
                count++;
            }
        }

/*      Iterator it = combine_StationList.iterator();
        while (it.hasNext()){
            String[] list = (String[]) it.next();
            System.out.print(list[0].toString());
            System.out.println(list[1].toString());
        }*/

        System.out.println("StationCodeList Size : " + StationCodeList.size());
        System.out.println("Count of Combine: " + count);

        return combine_StationList;
    }

    public static ArrayList getStationCodeList() {
        HashMap<String, String> hashMap = getStationCodeMap.getStationCodeMap(StationCodeFilePath);
        ArrayList StationCodeList = new ArrayList();
        Iterator it = hashMap.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            //Object key = entry.getKey();
            Object val = entry.getValue();
            StationCodeList.add(val.toString());
            //System.out.println(val.toString());
        }

        return StationCodeList;
    }


    public static void main(String[] args) {

        String queryDate = "2016-12-28";

        ArrayList combine_StationList = getCombineStation();
        String jsonResultString;
        BufferedWriter bufw = null;
        StartQueryTicket startQueryTicket = new StartQueryTicket();

        try {
            bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test", true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < combine_StationList.size(); i++) {

            String[] list = (String[]) combine_StationList.get(i);
            String startStation = list[0].toString();
            String endStation = list[1].toString();
            System.out.println(i + startStation + "_" + endStation);
            jsonResultString = startQueryTicket.StartQueryTicket_returnJSON(startStation, endStation, queryDate);

            if (jsonResultString.length() > 200) {
                try {
                    bufw.write(jsonResultString);
                    bufw.newLine();
                    bufw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            bufw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}