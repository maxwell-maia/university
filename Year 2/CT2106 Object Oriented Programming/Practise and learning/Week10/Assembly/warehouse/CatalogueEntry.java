package warehouse;

public class CatalogueEntry
{
    private String name;
    private long number;
    private double cost;

    public CatalogueEntry(String name, long number, double cost)
    {
        // initialise instance variables
        this.name = name;
        this.number = number;
        this.cost = cost;
    }
    
    public String getName(){
        return name;
    }
    
    public long getNumber(){
        return number;
    }
    
    public double getCost(){
        return cost;
    }
    
}
