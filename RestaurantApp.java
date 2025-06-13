import java.util.*;
class MenuItem {
    String name;
    double price;
    MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public boolean equals(Object o) {
        return o instanceof MenuItem m && name.equalsIgnoreCase(m.name);
    }
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
class Order {
    Map<MenuItem, Integer> items = new HashMap<>();
    String status = "Pending";
    String paymentMode = "Not Paid";
    void add(MenuItem item, int qty) {
        items.put(item, items.getOrDefault(item, 0) + qty);
    }
    void remove(MenuItem item, int qty) {
        if (!items.containsKey(item)) return;
        int remaining = items.get(item) - qty;
        if (remaining <= 0) items.remove(item);
        else items.put(item, remaining);
    }
    double total() {
        return items.entrySet().stream().mapToDouble(e -> e.getKey().price * e.getValue()).sum();
    }
    boolean isPaid() {
        return "Paid".equalsIgnoreCase(status);
    }
}
class Reservation {
    String name, datetime;
    int people, table;

    Reservation(String name, String datetime, int people, int table) {
        this.name = name;
        this.datetime = datetime;
        this.people = people;
        this.table = table;
    }
}
class RMS {
    List<MenuItem> menu = new ArrayList<>();
    List<Order> orders = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();
    Set<Integer> availableTables = new HashSet<>();

    RMS(int totalTables) {
        for (int i = 1; i <= totalTables; i++) availableTables.add(i);
    }

    void addMenuItem(String name, double price) {
        menu.add(new MenuItem(name, price));
    }
    boolean reserve(Reservation r) {
        if (availableTables.remove(r.table)) {
            reservations.add(r);
            return true;
        }
        return false;
    }
    void place(Order o) {
        orders.add(o);
    }
    void printMenu() {
        System.out.println("\n--- Menu ---");
        if (menu.isEmpty()) System.out.println("No items.");
        else menu.forEach(i -> System.out.printf("- %s : Rs%.2f\n", i.name, i.price));
    }
    void printTables() {
        System.out.println("\n--- Available Tables ---");
        System.out.println(availableTables.isEmpty() ? "None" : availableTables);
    }
    void printOrders() {
        System.out.println("\n--- Orders ---");
        if (orders.isEmpty()) System.out.println("No orders.");
        else for (Order o : orders) {
            System.out.printf("Status: %s, Payment: %s, Total: Rs%.2f\n", o.status, o.paymentMode, o.total());
            o.items.forEach((i, q) -> System.out.printf("  - %s (Qty: %d) Rs%.2f\n", i.name, q, i.price));
        }
    }
    void printReservations() {
        System.out.println("\n--- Reservations ---");
        if (reservations.isEmpty()) System.out.println("None.");
        else for (Reservation r : reservations)
            System.out.printf("For %s on %s, Table %d, People: %d\n", r.name, r.datetime, r.table, r.people);
    }
    void printUnpaidOrders() {
        System.out.println("\n--- Unpaid Orders ---");
        boolean found = false;
        for (Order o : orders) {
            if (!o.isPaid()) {
                found = true;
                System.out.printf("Status: %s, Payment: %s, Total: Rs%.2f\n", o.status, o.paymentMode, o.total());
                o.items.forEach((i, q) -> System.out.printf("  - %s (Qty: %d) Rs%.2f\n", i.name, q, i.price));
            }
        }
        if (!found) {
            System.out.println("All customers have paid.");
        } else {
            System.out.println("⚠ Customer(s) with pending bills are not allowed in the restaurant until they pay!");
        }
    }
    MenuItem findItem(String name) {
        return menu.stream().filter(i -> i.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
public class RestaurantApp {
    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        RMS rms = new RMS(10);
        rms.addMenuItem("Burger", 120);
        rms.addMenuItem("Pizza", 250);
        rms.addMenuItem("Pasta", 200);
        rms.addMenuItem("Fries", 90);
        rms.addMenuItem("Coke", 50);
        while (true) {
            System.out.println("\n--- Welcome to Restaurant Management System ---");
            System.out.println("1. Customer Panel");
            System.out.println("2. Admin Panel");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = getValidChoice(sc, 1, 3);
            if (mainChoice == 3) break;
            if (mainChoice == 2) {
                System.out.print("Enter admin password: ");
                String password = sc.nextLine();
                if (!password.equals("admin123")) {
                    System.out.println("Incorrect password. Access denied.");
                    continue;
                }
                while (true) {
                    System.out.println("\n--- Admin Panel ---");
                    System.out.println("1. View Orders");
                    System.out.println("2. View Reservations");
                    System.out.println("3. View Available Tables");
                    System.out.println("4. View Unpaid Customers");
                    System.out.println("5. Back");
                    System.out.print("Enter your choice: ");
                    int admin = getValidChoice(sc, 1, 5);
                    if (admin == 1) rms.printOrders();
                    else if (admin == 2) rms.printReservations();
                    else if (admin == 3) rms.printTables();
                    else if (admin == 4) rms.printUnpaidOrders();
                    else break;
                }
            } else {
                while (true) {
                    rms.printMenu();
                    rms.printTables();
                    System.out.println("\n--- Customer Panel ---");
                    System.out.println("1. Make Reservation");
                    System.out.println("2. Place Order");
                    System.out.println("3. Back");
                    System.out.print("Enter your choice: ");
                    int c = getValidChoice(sc, 1, 3);

                    if (c == 3) break;
                    if (c == 1) {
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Date & Time: ");
                        String dt = sc.nextLine();
                        System.out.print("People: ");
                        int p = getPositiveInt(sc);
                        System.out.print("Table #: ");
                        int t = getPositiveInt(sc);
                        boolean ok = rms.reserve(new Reservation(name, dt, p, t));
                        System.out.println(ok ? "Reserved!" : "Table not available.");
                    } else if (c == 2) {
                        Order order = new Order();
                        boolean ordering = true;
                        while (ordering) {
                            while (true) {
                                System.out.print("Enter item-qty (e.g., Burger-2) or 'done': ");
                                String input = sc.nextLine();
                                if (input.equalsIgnoreCase("done")) break;

                                try {
                                    for (String pair : input.split(",")) {
                                        String[] parts = pair.split("-");
                                        if (parts.length != 2) {
                                            System.out.println("Invalid input format.");
                                            continue;
                                        }
                                        MenuItem item = rms.findItem(parts[0].trim());
                                        int qty = Integer.parseInt(parts[1].trim());
                                        if (item != null && qty > 0) order.add(item, qty);
                                        else System.out.println("Not found or invalid quantity: " + parts[0]);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid input.");
                                }
                            }

                            if (order.items.isEmpty()) {
                                System.out.println("No items in order.");
                                break;
                            }
                            while (!order.items.isEmpty()) {
                                System.out.print("Remove item? (yes/no): ");
                                String rem = sc.nextLine();
                                if (!rem.equalsIgnoreCase("yes")) break;
                                order.items.forEach((i, q) -> System.out.printf("- %s (Qty: %d)\n", i.name, q));
                                System.out.print("Item name: ");
                                String n = sc.nextLine();
                                System.out.print("Qty to remove: ");
                                int q = getPositiveInt(sc);
                                MenuItem i = rms.findItem(n);
                                if (i != null) order.remove(i, q);
                                else System.out.println("Not found.");
                            }
                            if (order.items.isEmpty()) {
                                System.out.println("Order canceled.");
                                break;
                            }

                            System.out.print("Do you want to add more items? (yes/no): ");
                            String addMore = sc.nextLine();
                            if (!addMore.equalsIgnoreCase("yes")) ordering = false;
                        }
                        if (!order.items.isEmpty()) {
                            order.status = "Received";
                            System.out.println("\n--- Final Order ---");
                            order.items.forEach((i, q) -> System.out.printf("- %s (Qty: %d) Rs%.2f\n", i.name, q, i.price));
                            double total = order.total();
                            System.out.printf("Total: Rs%.2f\n", total);
                            boolean paymentSuccess = false;
                            while (!paymentSuccess) {
                                System.out.println("\nChoose Payment Method:");
                                System.out.println("1. UPI Scanner");
                                System.out.println("2. Cash");
                                System.out.print("Enter your choice: ");
                                int payChoice = getValidChoice(sc, 1, 2);

                                if (payChoice == 1) {
                                    System.out.println("Please scan the UPI QR code...");
                                    System.out.print("Enter paid amount: ");
                                    double paid = getPositiveDouble(sc);
                                    if (paid >= total) {
                                        paymentSuccess = true;
                                        System.out.println("Payment successful via UPI!");
                                    } else {
                                        System.out.println("Insufficient amount paid. Please pay full amount.");
                                    }
                                } else if (payChoice == 2) {
                                    System.out.print("Enter cash amount: ");
                                    double cash = getPositiveDouble(sc);
                                    if (cash == total) {
                                        paymentSuccess = true;
                                        System.out.println("Cash payment successful!");
                                    } else {
                                        System.out.println("Please put the exact amount.");
                                    }
                                }
                            }
                            order.status = "Paid";
                            order.paymentMode = (order.paymentMode.equals("Not Paid") && paymentSuccess) ? 
                                (order.paymentMode = (order.paymentMode.equals("Not Paid") ? "Paid" : order.paymentMode)) : order.paymentMode;
                            order.paymentMode = (paymentSuccess) ? (order.paymentMode.equals("Not Paid") ? "Paid" : order.paymentMode) : order.paymentMode;
                            order.paymentMode = (order.paymentMode.equals("Not Paid")) ? "Paid" : order.paymentMode;
                            order.paymentMode = (order.paymentMode.equals("Not Paid")) ? "Paid" : order.paymentMode; 
        
                        }
                    }
                }
            }
        }
        sc.close();
        System.out.println("Thank you for visiting!");
    }
    static int getValidChoice(Scanner sc, int min, int max) {
        int val = -1;
        while (true) {
            if (sc.hasNextInt()) {
                val = sc.nextInt();
                sc.nextLine();
                if (val >= min && val <= max) break;
                else System.out.print("Invalid choice. Enter your choice: ");
            } else {
                System.out.print("Invalid choice. Enter your choice: ");
                sc.nextLine();
            }
        }
        return val;
    }
    static int getPositiveInt(Scanner sc) {
        int val = -1;
        while (true) {
            if (sc.hasNextInt()) {
                val = sc.nextInt();
                sc.nextLine();
                if (val > 0) break;
                else System.out.print("Please enter a positive number: ");
            } else {
                System.out.print("Invalid input. Enter a positive number: ");
                sc.nextLine();
            }
        }
        return val;
    }
    static double getPositiveDouble(Scanner sc) {
        double val = -1;
        while (true) {
            if (sc.hasNextDouble()) {
                val = sc.nextDouble();
                sc.nextLine();
                if (val > 0) break;
                else System.out.print("Please enter a positive amount: ");
            } else {
                System.out.print("Invalid input. Enter a valid amount: ");
                sc.nextLine();
            }
        }
        return val;
    }
}