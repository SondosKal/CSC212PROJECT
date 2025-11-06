import java.io.File;
import java.util.Scanner;

public class Customers {
    private LinkedList<Customer> customers;

    public Customers() {
        customers = new LinkedList<>();
    }

    Customers(LinkedList<Customer> input_customers) {
        customers = input_customers;
    }

    public LinkedList<Customer> get_customers() {
        return customers;
    }

    public Customer searchById(int id) {
        if (customers.empty())
            return null;

        customers.findFirst();
        while (true) {
            if (customers.retrieve().getCustomerId() == id)
                return customers.retrieve();

            if (customers.last())
                break;

            customers.findNext();
        }
        return null;
    }

    public void addCustomer(Customer c) {
        if (searchById(c.getCustomerId()) == null) {
            customers.addLast(c);
            System.out.println("✓ Added customer: " + c.getName());
        } else {
            System.out.println("✗ Customer with ID " + c.getCustomerId() + " already exists!");
        }
    }

    public void displayAll() {
        if (customers.empty()) {
            System.out.println("No customers found!");
            return;
        }

        System.out.println("=== All Customers ===");
        customers.findFirst();
        while (true) {
            customers.retrieve().display();
            if (customers.last())
                break;
            customers.findNext();
        }
    }

    public void loadCustomers(String fileName) {
        try {
            File f = new File(fileName);
            Scanner read = new Scanner(f);
            System.out.println("→ Reading file: " + fileName);
            System.out.println();

            if (read.hasNextLine())
                read.nextLine();

            while (read.hasNextLine()) {
                String line = read.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] a = line.split(",");
                int id = Integer.parseInt(a[0].trim());
                String name = a[1].trim();
                String email = a[2].trim();

                Customer c = new Customer(id, name, email);
                customers.addLast(c);
            }

            read.close();
            System.out.println("✓ Customers loaded successfully!\n");
        } catch (Exception e) {
            System.out.println("✗ Error loading customers: " + e.getMessage());
        }
    }

    public static void test1() {
        Customers all = new Customers();
        Customer c1 = new Customer(201, "Omar Hassan", "omar.hassan@gmail.com");
        Customer c2 = new Customer(202, "Nour Adel", "nour.adel@yahoo.com");

        all.addCustomer(c1);
        all.addCustomer(c2);

        System.out.println("\nAfter adding manually:");
        all.displayAll();
    }

    public static void test2() {
        Customers all = new Customers();
        all.loadCustomers("/Users/janamac31/Desktop/dataset/customers.csv");
        all.displayAll();
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
