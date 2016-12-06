package MutilThreadGetJson;

import QueryTicket.StationCode;
import QueryTicket.Train;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by geyalu on 2016/11/12.
 */
public class TraverseStation {
    public static void main(String[] args) {

        String queryDate = "2016-12-28";
        String url = "https://kyfw.12306.cn/otn/lcxxcx/query?purpose_codes=ADULT&queryDate=2016-11-10&from_station=XNO&to_station=JNK";
        String querydate = "queryDate=" + queryDate;
        String jsonResultString = null;
        BufferedWriter bufw= null;

        ArrayList combine_StationList = new ArrayList();
        ArrayList StationCodeList;

        int count = 0;
        StationCodeList=getStationCodeList();

        for (int i = 0; i < StationCodeList.size(); i++) {
            for (int j = i+1; j <StationCodeList.size() ; j++) {
                String [] Combine2Station = new String[2] ;
                //System.out.print("i"+": "+StationCodeList.get(i));
                //System.out.println("  j"+": "+StationCodeList.get(j));
                Combine2Station[0]=StationCodeList.get(i).toString();
                Combine2Station[1]=StationCodeList.get(j).toString();
                combine_StationList.add(Combine2Station);
                count++;

            }

        }

        try {
            bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[0],true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

         for (int i = 0; i <combine_StationList.size() ; i++) {
            String[] list = (String[]) combine_StationList.get(i);
            System.out.println(list[0].toString()+"_"+list[1].toString());
            System.out.println(i);

            String newUrl = url.replace("XNO", list[0].toString()).replace("JNK", list[1].toString()).replace("queryDate=2016-11-10", querydate);
            System.out.println(newUrl);

            jsonResultString = Train.QueryLeftTicket(newUrl);
            System.out.println(jsonResultString);

            if (jsonResultString.length()>200){
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

/*        Iterator it = combine_StationList.iterator();
        while (it.hasNext()){
            String[] list = (String[]) it.next();
            System.out.println(list[0].toString());
            System.out.println(list[1].toString());
        }*/

        System.out.println("StationCodeList Size : "+StationCodeList.size());
        System.out.println("Count of Combine: "+count);
    }


    public static ArrayList getStationCodeList(){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        ArrayList StationCodeList = new ArrayList();
        hashMap = StationCode.queryStationCode();

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            //Object key = entry.getKey();
            Object val = entry.getValue();
            StationCodeList.add(val.toString());
            //System.out.println(val.toString());
        }

        return StationCodeList;
    }
}
