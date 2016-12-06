package QueryTicket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by geyalu on 2016/11/10.
 */
public class SplitJson {

    public static ArrayList splitJson(String json) {

        ArrayList jsonSplitResult = new ArrayList();
        String[] datas = json.split("queryLeft");

        for (String train : datas) {

            Matcher m= Pattern.compile("New(.*?),\"secretStr\"").matcher(train);
            while(m.find()){

                String res = m.group(1).replace("DTO","{\"DTO")+"}";

                jsonSplitResult.add(res);
            }

        }

        return jsonSplitResult;
    }

    public static ArrayList splitJsonLeft(String json) {

        ArrayList jsonSplitResult = new ArrayList();


            Matcher m= Pattern.compile("\"httpstatus\":200,(.*?),\"messages\"").matcher(json);
            while(m.find()){
                String res ="{"+m.group(1)+"}";

                jsonSplitResult.add(res);
            }

        return jsonSplitResult;
    }


}
