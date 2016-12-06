package hbase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by geyalu on 2016/11/3.
 */
public class RE {

    public static void main(String[] args) {

        ArrayList result = new ArrayList();

        result = get_IpData("acc.txt");

        Iterator it  = result.iterator();

        while (it.hasNext()){

            System.out.println(it.next().toString());
        }

    }

    static ArrayList get_IpData(String path) {


        FileReader read = null;

        try {
            read = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(read);


        String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* \\[(\\d+/[A-Z][a-z]+/\\d+:\\d+:\\d+:\\d+) [^ ]*\\] \"[^ ]+ ([^ ]+) .*";

        String row;

        ArrayList result  =new ArrayList();
        try {
            while ((row = br.readLine()) != null) {



                String line = row;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }


}
