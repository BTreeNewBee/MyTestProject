package iguigui.leetcodes.queue;

import java.util.Stack;

public class LC232 {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        for (int i = 0; i < 3; i++) {
            myQueue.push(i);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(myQueue.peek());
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(myQueue.pop());
        }
    }


}

class MyQueue {

    private Stack<Integer> writeStack = new Stack<>();

    private Stack<Integer> readStack = new Stack<>();

    private int mode = 0;

    public MyQueue() {

    }

    private void readMode() {
        if (mode == 0) {
            while (!writeStack.empty()) {
                readStack.push(writeStack.pop());
            }
            mode = 1;
        }
    }

    private void writeMode() {
        if (mode == 1) {
            while (!readStack.empty()) {
                writeStack.push(readStack.pop());
            }
            mode = 0;
        }
    }

    public void push(int x) {
        writeMode();
        writeStack.push(x);
    }

    public int pop() {
        readMode();
        return readStack.pop();
    }

    public int peek() {
        readMode();
        return readStack.peek();
    }

    public boolean empty() {
        readMode();
        return readStack.empty();
    }
}
