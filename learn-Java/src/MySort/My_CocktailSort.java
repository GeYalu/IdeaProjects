package MySort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by geyalu on 2016/11/25.
 */
public class My_CocktailSort {
    public static void main(String[] args) {
        int arr []={45,19,77,81,13, 28, 18, 19, 77};
        cocktailSort(arr);
    }


    static void cocktailSort(int [] arr){

        int left=0;
        int right =arr.length-1;


        while (left<right){

            for (int i = left; i <right ; i++) {
                if (arr[i]>arr[i+1]){
                    int temp = arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                }
            }
            System.out.println(Arrays.toString(arr));
            System.out.println("-------------");

            right--;
            for (int j  = right; j >left ; j--) {
                if (arr[j]<arr[j-1]){
                    int temp = arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
            left++;
            System.out.println(Arrays.toString(arr));
        }

    }
}
