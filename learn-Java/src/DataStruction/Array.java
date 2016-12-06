package DataStruction;

import java.util.Arrays;

/**
 * Created by geyalu on 2016/11/29.
 */
public class Array {

    public static int[] insert(int[] old, int value, int index) {
        int[] destination = new int[old.length+1];

        for (int i = 0; i <= index; i++){
            destination[i]=old[i];
        }
        destination[index+1] = value;
        for (int k = index+2; k <=old.length; k++){
            destination[k] = old[k-1];
        }

        return destination;
    }


    private static int[] insertElement(int original[], int element, int index) {
        int length = original.length;
        int destination[] = new int[length + 1];
        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(original, index, destination, index
                + 1, length - index);
        return destination;
    }


    public static void main(String[] args) {
        int[] x = {5, 2, 3};
        int[] newarr = insert(x, 0, 1);
        System.out.println(Arrays.toString(newarr));
    }


}
