package warehouse;

import java.util.ArrayList;

/**
 * Write a description of class Assembly here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
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
    public double getCost() //look at the model diagram * that is the Composite design pattern
    // all coded. sir will continue next week
    {
        double totalCost = 0;
        
        //Add cost of all parts in this assembly
        for(Component comp: components)
        {
            totalCost += comp.getCost(); //If its an assembly its basically CAALLING ITSELF of  A SMALLER OBJECT. CALLING GETCOST OF ASSEMBLY INSIDE THIS THIS ASSEMBLY
        }
        
        return totalCost; //If we were inside another assembly this one goes into the getCost() above, which will get returned here again up 1.
        // little assembly cost return to -> big assembly cost return to -> return
    }
}
