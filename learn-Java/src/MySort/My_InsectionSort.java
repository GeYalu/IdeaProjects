package MySort;

import java.util.Arrays;

public class My_InsectionSort {

    public static void main(String[] args) {
        int[] arr = {9, 2, 4, 7, 3, 7, 10};
        My_InsectionSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    static void My_InsectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {

                if (arr[j] >= arr[j - 1]) {
                    break;
                }

                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }

            }

        }

    }

}
