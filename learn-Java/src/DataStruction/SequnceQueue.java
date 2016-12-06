package DataStruction;

import java.util.Arrays;

/**
 * Created by geyalu on 2016/12/6.
 */
public class SequnceQueue {

    public static void main(String[] args) {
        SequnceQueue sequnceQueue = new SequnceQueue(3);

        sequnceQueue.printArray();
        sequnceQueue.set(5);
        sequnceQueue.set(2);
        sequnceQueue.set(3);
        sequnceQueue.set(4);

        sequnceQueue.printArray();
        System.out.println(sequnceQueue.get());
        System.out.println(sequnceQueue.get());
        System.out.println(sequnceQueue.get());
    }

    private Integer[] array;
    private int length;
    private int front = 0;
    private int rear = 0;

    public SequnceQueue(int length) {
        array = new Integer[length];
        this.length = length;
    }

    public boolean set(Integer element) {
        if (isFull()) {
            return false;
        } else {
            array[rear++] = element;
            return true;
        }
    }

    public Integer get() {
        if (isEmpty()) return null;
        if (front < length) {
            return array[front++];
        } else {
            return null;
        }
    }

    private boolean isEmpty() {
        return (front == 0 && rear == 0);
    }

    public boolean isFull() {
        if (rear >= length) {
            return true;
        } else {
            return false;
        }
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }
}
