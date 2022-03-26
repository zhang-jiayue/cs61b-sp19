import java.util.Stack;

public class MyQueue {
    private int size;
    Stack<Integer> queue;
    Stack<Integer> reversed;

    public MyQueue() {
        size= 0;
        queue = new Stack<>();
        reversed = new Stack<>();
    }

    public void push(int x) {

        this.size++;
    }

    public int pop() {
        this.size--;
        return 0;
    }

    public int peek() {
        return queue.peek();
    }

    public boolean empty() {
        return this.size == 0;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */