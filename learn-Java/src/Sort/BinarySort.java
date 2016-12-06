package Sort;

import java.util.Arrays;

/**
 * Created by geyalu on 2016/11/24.
 */
public class BinarySort {

    public static void main(String args[]) {
        int array[] = {4, 3, 6, 9, 7, 1, 2};
        binarySort(array, array.length);
        System.out.println("---------排序后的结果----------");
        System.out.println(Arrays.toString(array));

        //System.out.println(binarySearch(array,0,7,1));

    }

    //二分查找
    public static int binarySearch(int array[], int low, int high, int temp) {
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (array[mid] < temp && temp <= array[mid + 1])
                return (mid + 1);
            else if (array[mid] < temp)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return high;
    }


    //二分排序
    public static void binarySort(int array[], int size) {
        int i, j, k, temp;
        for (i = 1; i < size; i++) {
            temp = array[i];
            if (array[i] < array[0])
                k = 0;
            else
                k = binarySearch(array, 0, i, temp);
            for (j = i; j > k; j--) {
                array[j] = array[j - 1];
            }
            array[k] = temp;
            System.out.println(Arrays.toString(array));
        }
    }
}
