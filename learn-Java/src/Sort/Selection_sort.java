package Sort;

import java.util.Arrays;

public class Selection_sort {
    /*
    取出数组中第i个值，遍历剩下的所有元素，寻找是否有比arr[i]更小的值，若有，交换；没有，i++，继续遍历数组

     */

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 6, 8, 9, 7, 1, 6};
        selection_sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    public static void selection_sort(int[] arr) {
        int i, j, min, len = arr.length;
        for (i = 0; i < len; i++) {
            min = i;
            for (j = i + 1; j < len; j++) {
                if (arr[min] > arr[j]) {
                    swap(arr, min, j);
                }
            }
        }
    }

}