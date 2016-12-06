package QueryTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by geyalu on 2016/11/10.
 */
public class main {

    public static void main(String[] args) {

        String startStation = "青岛";
        String endStation = "北京";
        String queryDate = "2016-11-13";

        String jsonResultString = null;
        ArrayList jsonSplitResultList = new ArrayList();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap = StationCode.queryStationCode();

        String url = "https://kyfw.12306.cn/otn/lcxxcx/query?purpose_codes=ADULT&queryDate=2016-11-10&from_station=XNO&to_station=JNK";
        String startStationCode = "from_station=" + hashMap.get(startStation);
        String endStationCode = "to_station=" + hashMap.get(endStation);
        String querydate = "queryDate=" + queryDate;
        String newUrl = url.replace("from_station=XNO", startStationCode).replace("to_station=JNK", endStationCode).replace("queryDate=2016-11-10", querydate);

        System.out.println(newUrl);

        jsonResultString = Train.QueryLeftTicket(newUrl);
        jsonSplitResultList = SplitJson.splitJsonLeft(jsonResultString);

        Iterator it = jsonSplitResultList.iterator();

        ArrayList DataList  = new ArrayList();

        while (it.hasNext()) {

            //TransJson.transJson(it.next().toString());
            DataList = TransJson.transJson(it.next().toString());

            Iterator iterator = DataList.iterator();

            while (iterator.hasNext()){

                Datas datas =(Datas) iterator.next();
                System.out.println("-----------------------------");
                System.out.println(datas.getStation_train_code());
                System.out.println(datas.getStart_station_name()+"-->"+datas.getTo_station_name()+" 历时："+datas.getLishi());
                System.out.println("始发站："+datas.getFrom_station_name());
                System.out.println("终点站："+datas.getEnd_station_name());
                System.out.println("二等座："+datas.getZe_num()+"  一等座："+datas.getZy_num()+"  商务座："+datas.getSwz_num());

            }

        }

    }

}
