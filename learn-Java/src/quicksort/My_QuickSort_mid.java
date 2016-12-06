package quicksort;

import java.util.Arrays;


/**
 * Created by geyalu on 2016/11/27.
 */
public class My_QuickSort_mid {

    public static void main(String[] args) {
        int[] x = {5, 2, 3, 6, 8, 9, 7, 1, 6};
        sort(x);
        System.out.println(Arrays.toString(x));
    }

    static void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(int[] a, int lo, int hi) {
        //选择数组中间元素作为基准，只需要，将数组开头元素与mid交换
        int mid = (lo+hi)/2;
        swap(a,lo,mid);

        int i = lo + 1, j = hi;
        int pivot = a[lo];

        System.out.println("P: "+pivot);

        while (true) {
            while (a[i] <= pivot && i < hi) {
                i++;
            }
            while (a[j] >= pivot && j > lo) {
                j--;
            }
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

}