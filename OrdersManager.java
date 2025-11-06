package project;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrdersManager {
	
	private LinkedList<Order> all_orders;
	private Customers all_customers;
	static DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");

	
	public Orders(LinkedList<Customer> input_customers, LinkedList<Oreder> all_orders) {
		all_customers=new Customers(input_customers);
		this.all_orders= all_orders;
	}
	
	public Orders() {
		all_customers=new Customers();
		all_orders=new LinkedList<>();
	}
	
	public LinkedList<Order> get_Orders(){
		return all_orders;
	}
	
	
	
	
	public Order searchOrderById(int id) {
		if(all_orders.empty())return null;
		
		all_orders.findFirst();
		while(true) {
			Order o= all_orders.retrieve();
			if(o.getOrderId()==id)
				return o;
			if(all_orders.last())
				break;
			all_orders.findNext();
			
		}
		return null;
	}
	
	
	
	
	
	public void assign(Order ord) {
		Customer p= all_Customers.searchById(ord.getCustomerId());
		if(p==null)
			System.out.println("the customer does not exist to assign the order" );
		else p.addOrder(ord);
	}
	
	public void addOrder(Order ord) {
		if(searchOrderById(ord.getOrderId())==null) {
			all_orders.addLast(ord);
			assign(ord);
			System.out.println("ORDER ADDED SUCCESSFULLY!!!"+ ord.getOrderId());
		} else {
			System.out.println("Order with ID "+ ord.getOrderId()+ " ALREADY EXISTS!!");
		}
	}
	
	
	public static Order convert_String_to_order(String Line) {
		
		String a[]= Line.split(",");
		
		int OrderId=Integer.parseInt(a[0].trim().replaceAll("\"", ""));
		
		int customerId=Integer.parseInt(a[1].trim().replaceAll("\"", ""));
		
		String productIds=a[2].trim().replaceAll("\"", "");
		double totalPrice= Double.parseDouble(a[3]);
		String orderDate= a[4];
		String status= a[5].trim();
		
		Order ord= new Order(OrderId,customerId,productIds,totalPrice,orderDate,status);
		return ord;
	}
	
	
	
	
	public void loadOrders(String fileName) {
		try {
			File f= new File(fileName);
			Scanner read = new Scanner(f);
			DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			System.out.println("READING THE FILE: "+ fileName);
			System.out.println("-----------------------------------");
			read.nextLine();
			while(read.hasNextLine()) {
				String line=read.nextLine().trim();
				Order ord = convert_String_to_order(line);
				addOrder(ord);
			}
			read.close();
			System.out.println("FILE LOADED SUCCESSFULLY!!!\n");
			
			}catch(Exception e) {
				System.out.println("ERROR LOADING ALL ORDERS: "+ e.getMessage());

			}
}
	
	
	public void displayAllOrders() {
		if (all_orders.empty()) {
			System.out.println("NO ORDERS FOUND");
			return;
		}
		
		System.out.println("orderID\tCustomerID\tProductIDs\t\tTotalPrice\tDate\t\tStatus");
		System.out.println("-------------------------------------------------------");
		
		all_orders.findFirst();
		while(true) {
			Order o= all_orders.retrieve();
			o.display();
			if(all_orders.last())
				break;
			all_orders.findNext();
		}
		System.out.println("-----------------------------------------------------");

		
	}
	
	
	

}
