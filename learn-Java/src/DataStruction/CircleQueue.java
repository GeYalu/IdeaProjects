package DataStruction;


public class CircleQueue {

    private int[] data;
    private int size;
    private int front;
    private int rear;

    public CircleQueue(int size) {
        this.size = size;
        data = new int[size];
        front = 0;// 队头指针，指向实际队头元素
        rear = 0;// 队尾指针，指向实际队尾元素的下一个位置
    }

    public boolean isEmpty() {
        if (front == rear) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (rear - front >= data.length) {
            return true;
        } else {
            return false;
        }
    }

    public void insert(int t) throws Exception {
        if (isFull()) {
            throw new Exception("push into full queue exception");
        }
        data[rear % data.length] = t;
        rear++;
    }

    public void remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("remove from empty queue exception");
        }
        front++;
    }


    public int getFront() throws Exception {
        if (isEmpty()) {
            throw new Exception("remove from empty queue exception");
        }
        return data[front % data.length];
    }

    public void show() {
        System.out.print("front:" + front + ",rear:" + rear + ",[");
        for (int i = front; i < rear; i++) {
            System.out.print(data[i % data.length]);
            if (i != rear - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) throws Exception {

        CircleQueue queue = new CircleQueue(10);

        queue.show();
        // 插入操作
        queue.insert(1);
        queue.show();
        queue.insert(2);
        queue.show();
        queue.insert(3);
        queue.show();
        queue.insert(4);
        queue.show();
        queue.insert(5);
        queue.show();
        queue.insert(6);
        queue.show();
        queue.insert(7);
        queue.show();
        queue.insert(8);
        queue.show();
        queue.insert(9);
        queue.show();
        queue.insert(10);
        queue.show();
        // 删除操作
        queue.remove();
        queue.show();
        queue.remove();
        queue.show();
        queue.remove();
        queue.show();
        queue.remove();
        queue.show();
        queue.remove();
        queue.show();

        // 插入操作
        queue.insert(11);
        queue.show();
        queue.insert(12);
        queue.show();
        queue.insert(13);
        queue.show();
        queue.insert(14);
        queue.show();
        queue.insert(15);
        queue.show();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.show();
    }
}