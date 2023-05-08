package warehouse;

public class Part implements Component //anything else to do here?
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
    public double getCost(){
        return entry.getCost();
    }
    
}
