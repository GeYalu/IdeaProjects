package hbase.WebCrawler_hq;

import java.util.*;

public class Main {
    static Iterator it;
    static String url;

    public static void init() {

        ArrayList shareList = new ArrayList();

        shareList.add("sh601006");
        shareList.add("sh000001");
        shareList.add("sh600066");

        url = "http://hq.sinajs.cn/list=sh000001";
        it = shareList.iterator();
        Insert_hq_HBase.hbaseConn("crawhq");

    }

    public static void start() {

        while (it.hasNext()) {

            String newUrl = url.replace("sh000001", it.next().toString());
            String result = ScrapData.Scrap_hq(newUrl);
            String splitResult = StoreHBase.splitData(result);

            String[] column = {"content"};
            String[] value = {splitResult};

            Insert_hq_HBase.addData(newUrl, "crawinfo", column, value);

        }
    }

    public static void close() {

        Insert_hq_HBase.close();
    }

    // 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
    // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
    public static void timer() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9); // 控制时
        calendar.set(Calendar.MINUTE, 28);       // 控制分
        calendar.set(Calendar.SECOND, 0);       // 控制秒

        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                System.out.println("-------导弹发射||||--------");
                init();
                start();
                close();


            }
        }, time, 1000 * 5);// 这里设定将延时每天固定执行
    }

    public static void main(String[] args) {

        timer();
    }

}






