package test;

import quicksort.QuickSort_mid;

import java.util.Arrays;


public class main {

    public static void main(String[] args) {


        int[] arr = ArrayUtils.getrandomarray(1000000);
        ArrayUtils.shuffleArray(arr);

        //System.out.println(Arrays.toString(arr));
        int[] arr1 = arr.clone();
        int[] arr2 = arr.clone();
        int[] arr3 = arr.clone();



        long start1 = System.currentTimeMillis();
        My_QuickSort_mid.sort(arr1);
        long stop1 = System.currentTimeMillis();



        long start2 = System.currentTimeMillis();
        My_QuickSort_start.sort(arr2);
        long stop2 = System.currentTimeMillis();


        long start3 = System.currentTimeMillis();
        My_QuickSort_end.sort(arr3);
        long stop3 = System.currentTimeMillis();



        float excTime1 = (float) (stop1 - start1);
        float excTime2 = (float) (stop2 - start2);
        float excTime3 = (float) (stop3 - start3);


/*        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));
        System.out.println(Arrays.toString(arr5));*/

        System.out.println("mid  " + excTime1);
        System.out.println("start " + excTime2);
        System.out.println("end  " + excTime3);



    }


}
