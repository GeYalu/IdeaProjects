import java.util.*;

/**
 * Created by geyalu on 2016/11/20.
 */
public class java_test {


    static ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                if (sum == 0) {
                    list.add(i);
                    list.add(j);
                    sum = 0;
                }
            }
        }

        return list;
    }


    static int sqrt1(int x) {
        // write your code here
        long mid = (long) x / 2;

        while (true) {
            long low;
            long high;

            high = mid * mid;
            System.out.println(mid + " " + high);
            if (x < high) {

                mid = mid / 5;
                continue;
            } else {
                System.out.println("----------------------------");
                low = mid;
                while (true) {
                    long res = low * low;
                    System.out.println(low + " " + res);
                    if (res <= x && x < (low + 1) * (low + 1)) {

                        return (int) low;
                    } else {
                        low++;
                    }
                }
            }

        }

    }


    static int findMax(int[] arr) {

        int max = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;

    }


    static class findMaxclass {
        private int maxInt;

        public void begin(int[] arr) {
            maxInt = arr[0];
        }

        public void word(int i, int number) {
            if (number > maxInt) {
                maxInt = number;
            }
        }

        public void end() {
            System.out.println("MAX " + maxInt);
        }
    }


    static int replaceBlank2(char[] string, int length) {

        for (int i = 0; i < length; i++) {
            if (' ' == string[i]) {
                System.out.println(i);
                length += 2;

                for (int j = length; j > i + 1; j--) {
                    string[j] = string[j - 2];

                }
                string[i] = '%';
                string[i + 1] = '2';
                string[i + 2] = '0';

            }
        }
        System.out.println(Arrays.toString(string));
        return 0;


    }


    static int replaceBlank(char[] string, int length) {
        char[] string1 = new char[20];
        ArrayList list = new ArrayList();

        for (int i = 0; i < string.length; i++) {
            list.add(string[i]);
        }

        Collections.replaceAll(list, ' ', "%20");
        Iterator it = list.iterator();

        while (it.hasNext()) {

        }


        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res = res + list.get(i).toString();
        }

        System.out.println(res);

        for (int i = 0; i < res.length(); i++) {
            char c = res.charAt(i);
            string1[i] = c;
        }

        System.out.println(string1.length);
        System.out.println(Arrays.toString(string1));

        return 0;
    }

    static int removeElement(int[] A, int elem) {


        ArrayList list = new ArrayList();

        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                list.add(A[i]);
            }
        }

        int[] B = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            B[i] = (int) list.get(i);

        }
        Arrays.toString(B);
        return B.length;


    }

    static int[] plusOne(int[] digits) {
        // Write your code here

        int len = digits.length;

        if (digits[len - 1] < 9) {
            int temp = digits[len - 1];
            digits[len - 1] = temp + 1;
        } else {

            int size = 0;
            boolean flag = true;
            for (int i = len - 1; i >= 0; i--) {

                if (digits[i] == 9 && flag == true) {
                    size++;
                } else {
                    flag = false;
                    break;
                }
            }


            for (int i = len - 1; i >= len - size; i--) {
                digits[i] = 0;

            }

            if (size >= len) {
                int[] arr = new int[len + 1];
                arr[0] = 1;
                for (int i = 1; i < len - 1; i++) {
                    arr[i] = 0;

                }
                System.out.println(Arrays.toString(arr));
                return arr;
            }

            //int tmp = digits[len - size - 1];
            //digits[len - size - 1] = tmp + size;

        }

        return digits;


    }


    static int compare(String a, String b) {
        if (a.length() > b.length()) {
            return 1;
        } else if (a.length() < b.length()) {
            return -1;
        } else {
            return 0;
        }
    }


    static void longestWords(String[] arr) {
        // write your code here
/*        int len = dictionary.length;
        for (int i =0;i<len-1;i++){
            for(int j=i+1;j>0;j--){
                String temp = dictionary[j];
                String tempj = dictionary[j-1];
                int res = compare(temp,tempj);
                if (res==1||res==0){
                    break;
                }
                dictionary[j]=tempj;
                dictionary[j-1]=temp;
            }
        }*/

        int len = arr.length;
        int min, i, j, res;
        String temp;
        for (i = 0; i < len - 1; i++) {
            min = i;
            for (j = i + 1; j < len; j++) {
                res = compare(arr[min], arr[j]);
                if (res == 1) {
                    min = j;
                    temp = arr[min];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }


    }


    static void pp(Object p){
        System.out.println(p.toString());
    }



    public boolean compareStrings(String A, String B) {
        // write your code here
        ArrayList<Character> alist = new ArrayList<Character>();
        ArrayList<Character> blist = new ArrayList<Character>();

        char ac;
        char bc;

        for (int j =0;j<A.length();j++){
            ac = A.charAt(j);
            alist.add(ac);
        }

        for (int i =0;i<B.length();i++){
            bc = B.charAt(i);
            blist.add(bc);
        }


        for (int i =0;i<blist.size();i++){

            for (int j =0;j<alist.size();j++){
                if(blist.get(i).toString().equals(alist.get(j).toString())){
                    blist.set(i, 'a');
                    alist.set(j, 'a');
                    break;
                }
            }

        }

        for(Character c :blist){
            if(c!='a'){
                return false;
            }

        }
        return true;


    }




    public static void main(String[] args) {

        int n =10;

        int [] array;

       /* int arr[] = {-1, -19, 7, 81};

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(1);
        queue.add(2);
        queue.add(3);


        for (int q:queue) {
            System.out.println(q);
        }


        pp(queue.poll());

        pp(queue.element());

        String a = "hello";

        ArrayList list = new ArrayList();
        ArrayList list1 = new ArrayList();

        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(0);
        list.remove(1);
        list.remove(2);

        pp(list==null);
*/

    }




}
