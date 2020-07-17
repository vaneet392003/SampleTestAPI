package main.java.com.harness.restassured.model;

public class OrderRequestObj
{
    private String OrderStatus;

    private String orderId;

    private ItemRequestObj Items;

    private String CustomerName;
    
    private String CustomerId;


    private String OrderDate;

    public String getOrderStatus ()
    {
        return OrderStatus;
    }

    public void setOrderStatus (String OrderStatus)
    {
        this.OrderStatus = OrderStatus;
    }

    public String getOrderId ()
    {
        return orderId;
    }

    public void setOrderId (String orderId)
    {
        this.orderId = orderId;
    }

    public ItemRequestObj getItems ()
    {
        return Items;
    }

    public void setItems (ItemRequestObj Items)
    {
        this.Items = Items;
    }

    public String getCustomerName ()
    {
        return CustomerName;
    }

    public void setCustomerName (String CustomerName)
    {
        this.CustomerName = CustomerName;
    }

    public String getOrderDate ()
    {
        return OrderDate;
    }

    public void setOrderDate (String OrderDate)
    {
        this.OrderDate = OrderDate;
    }

    public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [OrderStatus = "+OrderStatus+", orderId = "+orderId+", Items = "+Items+", CustomerName = "+CustomerName+", OrderDate = "+OrderDate+"]";
    }
}