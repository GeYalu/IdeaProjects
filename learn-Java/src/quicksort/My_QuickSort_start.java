package quicksort;

import java.util.Arrays;


/**
 * Created by geyalu on 2016/11/27.
 */
public class My_QuickSort_start {

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
        //选择数组的第一个元素作为基准，从第二个元素往最后查找
        int i = lo + 1, j = hi;
        int pivot = a[lo];

        while (true) {
            //从左侧开始查找，找到大于pivot值的元素时，停下，指针指向大于等于pivot的元素;
            //设定边界条件，防止数组越界
            while (a[i] <= pivot && i < hi) {
                i++;
            }
            //a[0]不可能比自己pivot还大，所以不需要边界判断，减少一次边界判断;
            while (a[j] > pivot) {
                j--;
            }
            if (i >= j) break;
            swap(a, i, j);
        }
        //将基准元素放到合适位置，因为上面 i与j进行了最后一次交换，所以j比i小，j在i左侧，
        // a[j]一直保持小于等于pivot，所以将lo与交换后，lo所在位置是分区中间位置
        swap(a, lo, j);
        return j;
    }

}