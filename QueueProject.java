import java.util.*;
class QueueProject{
    ArrayList<String> queue = new ArrayList<>();
    public void enqueue(String activity) {
        queue.add(activity);
        System.out.println("Activity added: " + activity);
    }
    public String dequeue() {
        if (queue.isEmpty()) {
            System.out.println("No activities");
            return null;
        }
        return queue.remove(0);
    }
    public void display() {
        System.out.println("activities: " + queue);
    }
    public static void main(String args[]) {
        QueueProject q= new QueueProject();
        q.enqueue("Wake up");
        q.enqueue("Exercise");
        q.enqueue("Have breakfast");
        q.enqueue("Go to work");
        q.enqueue("Lunch break");
        q.enqueue("Evening walk");
        q.enqueue("Dinner");
        q.display();
        System.err.println("completed"+q.dequeue());
        q.display();
    }
}