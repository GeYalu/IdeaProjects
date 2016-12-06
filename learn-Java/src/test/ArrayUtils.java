package test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by geyalu on 2016/11/24.
 */
public class ArrayUtils {

    public static void main(String[] args) {
        int[] a = getrandomarray(10);
        System.out.println(Arrays.toString(a));
        shuffleArray(a);
        System.out.println(Arrays.toString(a));
    }


    static void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


    //得到数组内容从0到log-1的随机数组
    public static int[] getrandomarray(int log) {
        int[] result = new int[log];
        for (int i = 0; i < log; i++) {
            result[i] = i;
        }
        for (int i = 0; i < log; i++) {
            int random = (int) (log * Math.random());
            int temp = result[i];
            result[i] = result[random];
            result[random] = temp;
        }
        return result;
    }

}



