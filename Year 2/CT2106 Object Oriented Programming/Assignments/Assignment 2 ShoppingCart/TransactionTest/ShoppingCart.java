import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * ShoppingCart - Store and manipulate Items in an Array list.
 *
 * @author Maxwell Maia
 * @version 1.0
 */
public class ShoppingCart
{
    private final long cartId;
    private String time;
    private ArrayList<Item> items;
    private double total;
    
    private Customer customer;

    private boolean locked; //boolean to show whether the cart is locked or not
    //this will be checked to add or remove items
    //this will be updated by close()
    
    public ShoppingCart(Customer customer)
    {
        this.cartId = generateCartId();
        this.time = new SimpleDateFormat("yyyy.MM.dd | HH:mm:ss").format(new java.util.Date());
        
        this.items = new ArrayList<Item>();
        
        this.total = 0;
        
        this.customer = customer;
        
        this.locked = false;
    }
    
    
    //Add items to array list (add to shopping cart)
    public void addItem(Item i)
    {
        if(!locked) //Only add an item if cart is not locked
        {
            items.add(i); //Add the item to the array list.
            System.out.println("You have added \"" + i.getName() + "\" to the shopping cart.");
            total += i.getPrice(); //Update total price.
        }
        else
        {
            System.out.println("Sorry, item not added. The cart is locked.");
        }
    }
    
    //Return an item using its index
    public Item getItem(int index)
    {
        if(items.get(index) != null)
        {
            return items.get(index);
        }
        else
        {
            System.out.println("This item does not exist! Error code: ShoppingCartGetItem");
            return null;
        }
    }
    
    //Removes an item from the array list
    public void removeItem(Item item)
    {
        if(!locked) //Only remove an item if cart is not locked
        {
            if(items.contains(item)) //check that the item is present
            {
                items.remove(item); //Remove item
                total = total - item.getPrice(); //update the total price
                System.out.println("\n\"" + item.getName() + "\" has been removed from from the shopping cart.\n");
            }
            else
            {
                System.out.println("\nError: Item not found in shopping cart. Please try again.");
            }
        }
        else
        {
            System.out.println("\nYour cart is locked. Item has not been removed from shopping cart.\n");
        }
    }
    
    //Return size of array list
    public int numItems()
    {
        return items.size();
    }
    
    public double getTotal()
    {
        return total;
    }
    
    public long getCartId()
    {
        return cartId;
    }
    
    //Print all item details of all items and the current date and time
    public void printItems()
    {
        System.out.println("\n\nYour shopping cart...");
        if(numItems() > 0)
        {
            System.out.println(writeItems());
            System.out.println("Current time: "+ time + "\n");
        }
        else
        {
            System.out.println("The shopping cart is empty.");
        }
    }
    
    //Returns a string of all item details of all items.
    public String writeItems()
    {
        String out = "";
        for(int i = 0; i < numItems(); i++)
            {
                
                out += getItem(i).toString() + "\n";
                
            }
            out += "\nTotal price: " + total + " euro.";
            return out;
    }
    
    //Lock the cart
    public void close()
    {
        locked = true;
    }
    
    //Empty the cart
    public void clear()
    {
        items = null;
        total = 0;
    }
    
    public long generateCartId()
    {
        return (long)(Math.random() * 9999L);
    }
}
