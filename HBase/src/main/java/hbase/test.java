package hbase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by geyalu on 2016/11/9.
 */
public class test {

    public static void main(String[] args) throws IOException {

        FileReader read = null;
        Set resultSet = new HashSet();

        try {
            read = new FileReader("access.log");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(read);
        String row;
        ArrayList result = new ArrayList();

        int i = 0;
        while ((row = br.readLine()) != null) {

            i++;

            String line = row;
            String[] words = line.split("-");
            String ip = words[0];

            resultSet.add(ip);

        }

        System.out.println(resultSet.size());
        System.out.println(i);
    }

}
