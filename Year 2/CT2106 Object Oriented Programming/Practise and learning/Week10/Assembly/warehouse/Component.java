package warehouse;


/**
 * Abstract class Component - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public interface Component
{
    
    public double getCost();
    
}

//If we wanted to make a Canary object a able to be added to an assembly then:
// 1: add 'implements Component'
// 2: in Canary: add a concrete definition of the getCost() function as 
// defined by the Component interface tick >/