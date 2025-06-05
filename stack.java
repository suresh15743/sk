public class Stack {
    private int[] arr;
    private int top;
    private int capacity;
    public Stack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }
    public void push(int item) {
        if (isFull()) {
            throw new IllegalStateException("Stack Overflow");
        }
        arr[++top] = item;
    }
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack Underflow");
        }
        return arr[top--];
    }
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[top];
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public boolean isFull() {
        return top == capacity - 1;
    }
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Top element is: " + stack.peek());
        System.out.println("Popped element is: " + stack.pop());
        System.out.println("Stack is empty: " + stack.isEmpty());
        stack.push(40);
        stack.push(50);
        stack.push(60); 
        System.out.println("Top element is: " + stack.peek());
    }
}