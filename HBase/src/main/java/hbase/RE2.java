package hbase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by geyalu on 2016/11/3.
 */
public class RE2 {



    static ArrayList get_IpData(String line) {

        String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* \\[(\\d+/[A-Z][a-z]+/\\d+:\\d+:\\d+:\\d+) [^ ]*\\] \"[^ ]+ ([^ ]+) .*";
        ArrayList result = new ArrayList();

        String[] words = line.split("-");
        String ip = words[0];

        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(line);

        while (matcher.find()) {
            //System.out.println(ip + "\t" + matcher.group(1) + matcher.group(2) + matcher.group(3));
            result.add(ip);
            result.add(matcher.group(1));
            result.add(matcher.group(2));
            result.add(matcher.group(3));
        }
        return result;


    }

    static ArrayList get_page(String line) {

        ArrayList result = new ArrayList();

        String patternNUM = "[1-9]\\d{2,7}";
        String patternPage ="[/][a-zA-Z]+[-/]";

        Pattern pNum = Pattern.compile(patternNUM);
        Matcher matcherNum = pNum.matcher(line);

        Pattern pPage = Pattern.compile(patternPage);
        Matcher matcherPage = pPage.matcher(line);

        if (line.length()<2){
            result.add(line);
            result.add(" ");
            return result;
        }else if (matcherNum.find()&&matcherPage.find()){

                result.add(matcherNum.group(0));
                result.add(matcherPage.group(0).replaceAll("/","").replaceAll("-",""));

            return result;

        }else {
            result.add(line);
            result.add(" ");
            return result;

        }

    }

    public static void main(String[] args) {

        ArrayList result = new ArrayList();
        ArrayList result2 = new ArrayList();

        String row = "182.118.21.228 - - [31/Dec/2015:23:54:46 +0800] \"GET /show/17189 HTTP/1.1\" 200 26818 \"http://services.youyanchu.com/show/17189\" \"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0); 360Spider(compatible; HaosouSpider; http://www.haosou.com/help/help_3_2.html)\"";

        //String row = "111.13.13.72 - - [31/Dec/2015:22:50:22 +0800] \"GET /?yyc_src=baidu_zhida&bd_source_light=6451104 HTTP/1.1\" 301 182 \"-\" \"Mozilla/5.0 (iphone; U; CPU iPhone OS 4_3_5 like Mac OS X; zh-cn; zhidajiankong_qp) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5\"";

        result = get_IpData(row);

        String ip = result.get(0).toString().trim();
        String date = result.get(2).toString();
        String url = result.get(3).toString();
        System.out.println(url);


        result2 = get_page(url);

        System.out.println(result2.get(0));
        //System.out.println(result2.get(1));


    }

}