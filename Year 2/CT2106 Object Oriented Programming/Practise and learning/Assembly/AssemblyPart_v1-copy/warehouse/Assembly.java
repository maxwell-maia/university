package warehouse;

import java.util.ArrayList;

/**
 * Write a description of class Assembly here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Assembly
{
    private ArrayList<Part> parts = new ArrayList();
    private ArrayList<Assembly> assemblies = new ArrayList(); //inital solution
    // poor solution because adding assembly requires adding all this code. loop added too
    private String name;
    
    public Assembly(String name)
    {
        this.name = name;
    }
    
    public boolean add(Part part)
    {
        return parts.add(part);
    }
    
    //Overloaded differed by parameters
    
    public boolean add(Assembly assembly)
    {
        return assemblies.add(assembly);
    }
    
    public double getCost()
    {
        double totalCost = 0;
        
        //Add cost of all parts in this assembly
        for(Part part: parts)
        {
            totalCost += part.getCost();
        }
        
        //Add cost of all assemblies in this assembly (will return the cost of their own assemblies)
        //Recursion is taking place here.
        //
        
        for(Assembly assembly: assemblies)
        {
            totalCost += assembly.getCost();
        }
        
        return totalCost;
    }
}
