
/**
 * Item - set and retrieve information about an item.
 *
 * @author Maxwell Maia
 * @version 1.0
 */
public class Item
{
    private String name;
    private double price;
    private long itemId;
    

    public Item(String itemName, double price, long id)
    {
        this.name = itemName;
        this.price = price;
        this.itemId = id;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public String getName()
    {
        return name;
    }
    
    @Override
    public String toString()
    {
        
        String out = "ID: " + itemId + "\tName: " + name + "\tPrice:\t" + price;
        
        return out;
    }
}
