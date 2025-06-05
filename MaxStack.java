import java.util.Stack;
class MaxStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> maxStack = new Stack<>();
    public void push(int value) {
        stack.push(value);
        if (maxStack.isEmpty() || value >= maxStack.peek()) {
            maxStack.push(value);
        }
    }
    public int pop() {
        if (stack.isEmpty()) return -1;
        int removed = stack.pop();
        if (removed == maxStack.peek()) {
            maxStack.pop();
        }
        return removed;
    }
    public int peek() {
        return stack.isEmpty() ? -1 : stack.peek();
    }
    public int getMax() {
        return maxStack.isEmpty() ? -1 : maxStack.peek();
    }
    public void displayStack() {
        System.out.println("Original Stack: " + stack);
    }
    public static void main(String[] args) {
        MaxStack ms = new MaxStack();
        ms.push(5);
        ms.push(2);
        ms.push(10);
        ms.push(1);
        ms.displayStack(); 
        System.out.println("Peek Element: " + ms.peek());
        System.out.println("Max Element: " + ms.getMax());
        int poppedElement = ms.pop();
        System.out.println("Popped Element: " + poppedElement);
        ms.displayStack();
        System.out.println("Max Element after pop: " + ms.getMax());
    }
}