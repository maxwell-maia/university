
/**
 * Email - Compiles information to send an email (print to terminal).
 *
 * @author Maxwell Maia
 * @version 1.0
 */
public class Email
{
    private Order order;
    private Customer customer;

    
    public Email(Order order)
    {
        this.order = order;
        customer = order.getCustomer();
    }
    
    
    //Send an email describing success
    public void success()
    {
        String out = "";
        out += "\n\nEMAIL\n";
        out += "---------------------------------------------------------------------------------";
        out += "\nTo: "+customer.getEmailAddress()+"\nSubject: Order Success\n\nHello "+customer.getFirstName()+", your order has successfully been placed.";
        out += "\nDetails of your order:\n";
        out += "Order ID number " + order.getOrderId() + "\n";
        out += order.writeItems();
        out += "\n\n\n";
        out += "Delivery address:\n" + order.getDeliveryAddressString();
        out += "\n\nBilling address:\n" + order.getBillingAddressString();
        
        out += "\n\n\nThank you for your order.\n";
        
        out += "---------------------------------------------------------------------------------";

        
        System.out.println(out);
    }
    
    //Send an email describing order failure
    public void regret()
    {
        String out = "";
        out += "";
        out += "\n\nEMAIL\n";
        out += "---------------------------------------------------------------------------------";
        out += "\nTo: "+customer.getEmailAddress()+"\nSubject: Order Failed\n\nHello "+customer.getFirstName();;
        out += ", we regret to inform you that your order has not been placed. \nThere was a problem with you order. Please try again.\n";
        out += "\nDetails of your order that has been unsuccessful:\n";
        out += "Order ID number " + order.getOrderId() + "\n";
        out += order.writeItems();
        
        out += "\n\n\nIf the problem persists, feel free to contact us for assistance.\n\n";

        
        out += "---------------------------------------------------------------------------------";
        
        System.out.println(out);
    }
}
