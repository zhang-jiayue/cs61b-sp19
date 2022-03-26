public class MinStack {
    StackNode [] arr;
    int size;
    int top;
    int minimum;


    public MinStack() {
        arr = new StackNode[8];
        this.top = 3;
        this.minimum = (int)Math.pow(2, 31);
    }

    public void push(int val) {
        resize();
        if (val < this.minimum) {
            this.minimum = val;
        }
        arr[this.top] = new StackNode(val, this.minimum);
        this.top = plusOne(this.top);
        size ++;
    }

    public void pop() {
        arr[minisOne(this.top)] = null;
        this.top = minisOne(this.top);
        this.size--;
        if (size != 0) {
            this.minimum = arr[minisOne(this.top)].min;
        } else {
            this.minimum = (int)Math.pow(2, 31);
        }
    }

    public int top() {
        resize();
        return arr[minisOne(this.top)].val;
    }

    public int getMin() {
        return arr[minisOne(this.top)].min;
    }

    public void resize() {

    }

    public int plusOne(int x) {
        if (x == arr.length - 1) {
            return 0;
        }
        else return x + 1;
    }

    public int minisOne(int x) {
        if (x == 0) {
            return arr.length - 1;
        }
        else return x - 1;
    }

    public static class StackNode {
        int val;
        int min;
        public StackNode(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2147483646);
        stack.push(2147483646);
        stack.push(2147483647);
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        stack.push(2147483647);
        System.out.println(stack.top());
        System.out.println(stack.getMin());
        stack.push(-2147483648);
        System.out.println(stack.top());
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());

    }
}


