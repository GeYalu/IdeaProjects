package Crawler_12306.Crawl_all;

import Crawler_12306.QueryTicket.StartQueryTicket;
import Crawler_12306.QueryTicket.TicketQuery;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Crawler_12306.Crawl_all.TraverseStation.getCombineStation;

class RunnableDemo implements Runnable {

    private static String queryDate = "2016-12-28";

    private Thread t;
    private String threadName;
    private List splitlist;

    RunnableDemo(String name, List list) {
        threadName = name;
        this.splitlist = list;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        getJson();

        try {
            Thread.sleep(1);
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

        List combine_StationList = splitlist;
        String jsonResultString;
        BufferedWriter bufw = null;
        StartQueryTicket startQueryTicket = new StartQueryTicket();
        int size = combine_StationList.size();


        try {
            bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(threadName, true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < size; i++) {

            if (i % 50 == 1) {
                System.out.println(threadName + "  List Size: " + size + " Now in " + i);
            }

            String[] list = (String[]) combine_StationList.get(i);
            String startStation = list[0].toString();
            String endStation = list[1].toString();
            //System.out.println(i + startStation + "_" + endStation);
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

public class MutilThread {
    public static void main(String args[]) {

        ArrayList combine_StationList = getCombineStation();

        int index = 0;
        int count = combine_StationList.size();
        int splitCount = 150000;

        for (int i = 0; i < 20; i++) {
            List splitList;
            if (count - index < splitCount) {
                splitList = combine_StationList.subList(index, count);
                System.out.println(splitList.size());
                RunnableDemo R1 = new RunnableDemo("Thread" + i, splitList);
                R1.start();
                break;
            } else {
                splitList = combine_StationList.subList(index, index + splitCount);
                index += splitCount;
                System.out.println(splitList.size());
                RunnableDemo R1 = new RunnableDemo("Thread" + i, splitList);
                R1.start();
            }

        }

    }
}