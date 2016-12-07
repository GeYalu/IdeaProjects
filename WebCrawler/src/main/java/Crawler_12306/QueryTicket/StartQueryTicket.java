package Crawler_12306.QueryTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 查询余票信息
 */
public class StartQueryTicket {
    private static String StationCodeFilePath = "12306_StationCode.txt";
    private static HashMap<String, String> hashMap = getStationCodeMap.getStationCodeMap(StationCodeFilePath);

    public static void StartQueryTicket(String startStation, String endStation, String queryDate) {
        String jsonResultString;
        ArrayList jsonSplitResultList;
        ArrayList DataList;

        String url = "https://kyfw.12306.cn/otn/lcxxcx/query?purpose_codes=ADULT&queryDate=2016-11-10&from_station=XNO&to_station=JNK";
        String startStationCode = "from_station=" + hashMap.get(startStation);
        String endStationCode = "to_station=" + hashMap.get(endStation);
        String querydate = "queryDate=" + queryDate;
        String newUrl = url.replace("from_station=XNO", startStationCode).replace("to_station=JNK", endStationCode).replace("queryDate=2016-11-10", querydate);

        jsonResultString = TicketQuery.TicketQuery(newUrl);
        jsonSplitResultList = SplitJson.splitJsonLeft(jsonResultString);

        System.out.println(newUrl);

        Iterator it = jsonSplitResultList.iterator();
        while (it.hasNext()) {
            DataList = ParseJson.transJson(it.next().toString());
            Iterator iterator = DataList.iterator();

            while (iterator.hasNext()) {
                Datas datas = (Datas) iterator.next();
                System.out.println("-----------------------------");
                System.out.println(datas.getStation_train_code());
                System.out.println(datas.getStart_station_name() + "-->" + datas.getTo_station_name() + " 历时：" + datas.getLishi());
                System.out.println("始发站：" + datas.getFrom_station_name());
                System.out.println("终点站：" + datas.getEnd_station_name());
                System.out.println("二等座：" + datas.getZe_num() + "  一等座：" + datas.getZy_num() + "  商务座：" + datas.getSwz_num());
            }
        }
    }

    public static String StartQueryTicket_returnJSON(String startStation, String endStation, String queryDate) {
        String jsonResultString;

        String url = "https://kyfw.12306.cn/otn/lcxxcx/query?purpose_codes=ADULT&queryDate=2016-11-10&from_station=XNO&to_station=JNK";
        String startStationCode = "from_station=" + startStation;
        String endStationCode = "to_station=" + endStation;
        String querydate = "queryDate=" + queryDate;
        String newUrl = url.replace("from_station=XNO", startStationCode).replace("to_station=JNK", endStationCode).replace("queryDate=2016-11-10", querydate);

        jsonResultString = TicketQuery.TicketQuery(newUrl);

        //System.out.println(newUrl);
        return jsonResultString;
    }

    public static void main(String[] args) {

/*        String startStation = "青岛";
        String endStation = "北京";
        String queryDate = "2016-12-13";*/

        System.out.println("Input StartStation endStation Date(青岛 北京 2016-12-31) : ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String startStation = scanner.next();
            String endStation = scanner.next();
            String queryDate = scanner.next();
            StartQueryTicket(startStation, endStation, queryDate);
        }
    }
}