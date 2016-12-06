package Sort;

import java.util.Arrays;

/**
 * Created by geyalu on 2016/11/23.
 */
public class Cocktail_sort {

    public static void main(String[] args) {

        int arr []={45,19,77,81,13, 28, 18, 19, 77};
        cocktail_sort(arr);

    }

    public static void cocktail_sort(int[] arr) {
        int i, left = 0, right = arr.length - 1;
        int temp;
        while (left < right) {
            for (i = left; i < right; i++)
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            System.out.println(Arrays.toString(arr));
            System.out.println("-------------");
            right--;
            for (i = right; i > left; i--)
                if (arr[i - 1] > arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
            left++;
            System.out.println(Arrays.toString(arr));
        }
    }

}
