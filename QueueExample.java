import java.util.*;
public class QueueProject {
    private ArrayList<String> queue = new ArrayList<>();
    public void enqueue(String activity) {
        queue.add(activity);
        System.out.println("Activity added: " + activity);
    }
    public String dequeue() {
        if (queue.isEmpty()) {
            System.out.println("No activities left!");
            return null;
        }
        return queue.remove(0);
    }
    public String peek() {
        if (queue.isEmpty()) {
            System.out.println("No activities to peek!");
            return null;
        }
        return queue.get(0);
    }
    public boolean removeActivity(String activity) {
        if (queue.remove(activity)) {
            System.out.println("Removed activity: " + activity);
            return true;
        } else {
            System.out.println("Activity not found: " + activity);
            return false;
        }
    }
    public String front() {
        if (queue.isEmpty()) {
            System.out.println("No activities available!");
            return null;
        }
        return queue.get(0);
    }
    public void display() {
        System.out.println("Upcoming activities: " + queue);
    }
    public static void main(String args[]) {
        QueueProject q = new QueueProject();
        q.enqueue("Wake up");
        q.enqueue("Exercise");
        q.enqueue("Have breakfast");
        q.enqueue("Go to work");
        q.enqueue("Lunch break");
        q.enqueue("Evening walk");
        q.enqueue("Dinner");
        q.display();
        System.out.println("Next activity: " + q.peek());
        System.out.println("Front activity: " + q.front());
        System.out.println("Completed: " + q.dequeue());
        q.display();
        q.removeActivity("Evening walk");
        q.display();
    }
}