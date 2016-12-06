package MutilThreadGetJson;

import QueryTicket.Train;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;
    private List list;

    RunnableDemo(String name, List list) {
        threadName = name;
        this.list = list;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        getJson();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void getJson() {

        String queryDate = "2016-11-30";
        String url = "https://kyfw.12306.cn/otn/lcxxcx/query?purpose_codes=ADULT&queryDate=2016-11-10&from_station=XNO&to_station=JNK";
        String querydate = "queryDate=" + queryDate;
        String jsonResultString = null;
        BufferedWriter bufw = null;

        List combine_StationList = list;
        int size  = combine_StationList.size();
        int count = 0;

        try {
            bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(threadName, true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < size; i++) {
            count++;
            System.out.println(threadName+"  List Size: "+size+" Now in "+ i);

            String[] list = (String[]) combine_StationList.get(i);
            //System.out.println(list[0].toString() + "_" + list[1].toString());
            //System.out.println(i);

            String newUrl = url.replace("XNO", list[0].toString()).replace("JNK", list[1].toString()).replace("queryDate=2016-11-10", querydate);
            //System.out.println(newUrl);

            jsonResultString = Train.QueryLeftTicket(newUrl);
            //System.out.println(jsonResultString);

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

        //System.out.println("Count of Combine: " + count);

    }

}

public class TestThread {
    public static void main(String args[]) {
        ArrayList StationCodeList = new ArrayList();
        StationCodeList = TraverseStation.getStationCodeList();
        ArrayList combine_StationList = new ArrayList();

        for (int i = 0; i < StationCodeList.size(); i++) {
            for (int j = i + 1; j < StationCodeList.size(); j++) {
                String[] Combine2Station = new String[2];
                Combine2Station[0] = StationCodeList.get(i).toString();
                Combine2Station[1] = StationCodeList.get(j).toString();
                combine_StationList.add(Combine2Station);
            }

        }
        int index = 0;
        int count = combine_StationList.size();
        System.out.println("Count: " + count);

        int splitCount = 50000;

        for (int i = 0; i < 60; i++) {
            List temp;
            if (count - index < splitCount) {
                temp = combine_StationList.subList(index, count);
                System.out.println(temp.size());
                RunnableDemo R1 = new RunnableDemo("Thread" + i, temp);
                R1.start();
                break;
            } else {
                temp = combine_StationList.subList(index, index + splitCount);
                index += splitCount;
                System.out.println(temp.size());
                RunnableDemo R1 = new RunnableDemo("Thread" + i, temp);
                R1.start();
            }

        }

    }
}