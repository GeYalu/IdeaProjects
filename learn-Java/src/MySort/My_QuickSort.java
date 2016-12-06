package MySort;

import java.util.Arrays;

/**
 * Created by geyalu on 2016/11/26.
 */
public class My_QuickSort {

    public static void main(String[] args) {
        int[] x = {1, 2,0};
        myQuicksort(x,0,x.length-1);
        System.out.println(Arrays.toString(x));

    }

    static void swap(int [] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] =temp;
    }


    static void myQuicksort(int[] arr,int low ,int high){
        if (low>=high){
            return;
        }

        int left = low;
        int right = high-1;
        int mid = high;
        int midValue = arr[mid];

        while (left<right){
            while (arr[left]<midValue&&left<right){
                left++;
            }
            while (arr[right]>midValue&&left<right){
                right--;
            }
            if (left<right){
                swap(arr,left,right);

            }
        }

        if (arr[left]>midValue){
            swap(arr,left,mid);
        }else{
            left++;
        }


        myQuicksort(arr,low,left-1);
        myQuicksort(arr,left+1,high);

    }

}
