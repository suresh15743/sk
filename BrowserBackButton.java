import java.util.Scanner;
import java.util.Stack;back
public class BrowserBackButton {
    public static void main(String[] args) {
        Stack<String> pageStack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        pageStack.push("https://www.google.com");
        pageStack.push("https://www.geeksforgeeks.org");
        pageStack.push("https://www.geeksforgeeks.org/data-structures/");
        pageStack.push("https://www.geeksforgeeks.org/java-program-to-print-array-elements/");
        String currentPage = pageStack.peek();  // Start with top of the stack
        System.out.println("You are currently on: " + currentPage);
        while (true) {
            System.out.print("Press 'b' to go back or 'q' to quit: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("b")) {
                if (pageStack.size() > 1) {
                    pageStack.pop();  
                    currentPage = pageStack.peek();
                    System.out.println("You went back to: " + currentPage);
                } else {
                    System.out.println("You are at the first page. No more history.");
                }
            } else if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting browser.");
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }

        scanner.close();
    }
}