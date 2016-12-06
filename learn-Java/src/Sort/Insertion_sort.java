package Sort;

import java.util.Arrays;

/**
 * Created by geyalu on 2016/11/22.
 */
public class Insertion_sort {

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 6, 8, 9, 7, 1, 6};
        insertion_sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void insertion_sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len-1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j - 1] <= arr[j])
                    break;
                swap(arr,j,j-1);
            }
        }
    }

}
