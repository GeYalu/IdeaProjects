package test;

public class BubbleSort {
    public static void bubbleSort(int [] a) {
        //int a[] = {1, 5, 7, 3, 9};
        int temp ;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                //System.out.println("a[j]:" + j + " " + a[j] + "  a[j+1]:" + (j + 1) + " " + a[j + 1]);
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
      /*  for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);*/
    }

    public static void main(String[] args) {
        //bubbleSort();
    }
}


