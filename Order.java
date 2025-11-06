package project;

public class Order {
	private int orderId;
	private int customerId;
	private String prod_id;
	private double totalPrice;
	private String orderDate; // store as yyyy-MM-dd
	private String status; // pending, shipped, delivered, canceled
	private LinkedList<Integer> productIds; // store product IDs in order
	
	
	
	public Order(int orderId, int customerId,String prod_id,double totalPrice, String orderDate, String status) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.prod_id=prod_id;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.status = status;
		this.productIds = new LinkedList<Integer>();
		addProductId(prod_id);
		}
	
	public void cancelOrder() {
		status ="canceled";
	}
	
	public void updateStatus(String newStatus) {
        // Only allow valid statuses
        if (newStatus.equals("pending") || newStatus.equals("shipped") ||
            newStatus.equals("delivered") || newStatus.equals("canceled")) {
            status = newStatus;
        }
    }
	
	
	public void addProductId(String ids) {
		String a[]= ids.split(";");
		for(int i=0;i<a.length;i++)
			productIds.addLast(Integer.parseInt(a[i].trim()));
	}
	
	
	public void addId(int id) {
		productIds.addLast(id);
	} 
	
	
	public void updateOrder(Order ord) {
		this.orderId = ord.orderId;
		this.customerId = ord.customerId;
		this.prod_id=ord.prod_id;
		this.totalPrice = ord.totalPrice;
		this.orderDate = ord.orderDate;
		this.status = ord.status;
		this.productIds = ord.productIds;
	}
	
	
	
	
	
	
	
	
	
	
	//SETTERS AND GETTERS

	public int getOrderId() {return orderId;}

	public int getCustomerId() {
		return customerId;
	}
	public String getprod_id() {return prod_id; }

	public LinkedList<Integer> getProductIds() {
		return productIds;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setProductIds(LinkedList<Integer> productIds) {
		this.productIds = productIds;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public void display() {
		System.out.println();
	}
	
	
	
	
	
	
	
	

}
