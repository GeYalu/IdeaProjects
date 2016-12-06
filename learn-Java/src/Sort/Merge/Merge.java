package Sort.Merge;

import java.util.Arrays;

/**
 * Merge 将两个已经有序的数组合并成一个有序的数组；
 */
public class Merge {
    public static void main(String[] args) {

        int[] a = {2, 3, 6};
        int[] b = {1, 4, 5, 5, 7};
        int[] c = new int[a.length + b.length];

        merge(a, b, c);
        System.out.println(Arrays.toString(c));

    }

    //数组a 与 数组 b 合并，结果存入数组c，数组c需要提前初始化，长度为a+b
    static void merge(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }
        while (i < a.length) {
            c[k++] = a[i++];
        }
        while (j < b.length) {
            c[k++] = b[j++];
        }
    }

}
