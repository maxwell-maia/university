import java.util.ArrayList;
import java.util.Scanner;

/**
 * Write a description of class Order here.
 *
 * @author Maxwell Maia
 * @version 1.0
 */
public class Order
{
    private long orderId; 
   
    private Address deliveryAddress;
    private Address billingAddress;
    
    private ShoppingCart cart;
    private Customer customer;
    
    private ArrayList<Item> orderItems;
    
    private double total;
    
    
    public Order(ShoppingCart cart, Customer customer, Address delivery, Address billing)
    {
        orderId = generateOrderId();
        
        this.cart = cart;
        this.customer = customer;
        deliveryAddress = delivery;
        billingAddress = billing;
        
        orderItems = new ArrayList<>(); //To store items in order.
        
        //Transfer items one by one to Order object
        for(int i = 0; i < cart.numItems(); i++) 
        {
            orderItems.add(cart.getItem(i));
        }
        
        total = cart.getTotal(); //get the total
        
        cart.clear(); //Empty the shopping cart.
    }

    public long generateOrderId()
    {
        return (long)(Math.random() * 99999L);
    }
    
    public long getOrderId()
    {
        return orderId;
    }
    
    public double getTotal()
    {
        return total;
    }
    
    
    //Return a String with the item details
    // of the items that the customer is attempting to order.
    public String writeItems()
    {
        String out = "";
        for(int i = 0; i < orderItems.size(); i++)
            {
                
                out += orderItems.get(i).toString() + "\n";
                
            }
            out += "\nTotal price: " + total + " euro.";
            return out;
    }
    
   
   public Customer getCustomer()
   {
       return customer;
   }
   
   //Return a string of all address details via the Address toString()
   public String getDeliveryAddressString()
   {
       return deliveryAddress.toString();
   }
   
   //Return a string of all address details via the Address toString()
   public String getBillingAddressString()
   {
       return billingAddress.toString();
   }
}
