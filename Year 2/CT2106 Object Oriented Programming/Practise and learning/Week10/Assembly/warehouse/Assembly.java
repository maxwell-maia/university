package warehouse;
import java.util.ArrayList;

public class Assembly implements Component
{
    private ArrayList<Component> components = new ArrayList();
    private String name;
    
    public Assembly(String name)
    {
        this.name = name;
    }

    public boolean add(Component component)
    {
        return components.add(component);
    }
    
    @Override
    public double getCost()
    {
        //recursion nr2
        
        double totalCost = 0;
        
        for(Component comp: components){
            totalCost+=comp.getCost();
        }
        
        return totalCost;
    }
    
    public String getName(){
        return name;
    }
}
