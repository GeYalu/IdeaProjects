package Sort;

import java.util.Arrays;

public class Shell_sort {

    public static void main(String[] args) {

        int[] x = {9, 8,7,6,5,4,3,3,2,1,1,1,0,6,4,2,66,8,31,2};
        shellSort(x);

    }

    public static void shellSort(int[] list) {
        int gap = 3;//list.length / 2;

        while (1 <= gap) {

            // 把距离为 gap 的元素编为一个组，扫描所有组
            for (int i = gap; i < list.length; i++) {
                int j = 0;
                int temp = list[i];

                // 对距离为 gap 的元素组进行排序
                for (j = i - gap; j >= 0; j = j - gap) {
                    if (list[j]>temp) {
                        list[j + gap] = list[j];
                        list[j] = temp;
                    }

                }
            }
            gap = gap / 2; // 减小增量

            System.out.format("gap = %d:\t", gap);
            System.out.println(Arrays.toString(list));

        }
    }
}
