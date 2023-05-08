package warehouse;

public class Part implements Component
{
    //every Part has a reference to a CatalogueEntry object
    private CatalogueEntry entry;

    public Part(CatalogueEntry e)
    {
        entry = e;
    }
    
    public String getName(){
        return entry.getName();
    }
    
    public long getNumber(){
        return entry.getNumber();
    }
    
    @Override 
    public double getCost()
    {
        // recursion nr2
        return entry.getCost();
    }
    
}
