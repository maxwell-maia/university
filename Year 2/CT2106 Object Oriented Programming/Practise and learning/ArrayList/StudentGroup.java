import java.util.ArrayList;

/**
 * Write a description of class StudentGroup here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentGroup
{
    //reference variable
    private ArrayList<Student> group; //object (array list object) pointing to null

    /**
     * Constructor for objects of class StudentGroup
     */
    public StudentGroup()
    {
        //create object (empty array list) and assign to group reference
        group = new ArrayList<>();
    }
 
    public void addStudent(Student s)
    {
        //add() is a method defined in ArrayList<>. (from library)
        //add() expects an object.
        group.add(s);
    }
    
    public Student getStudent(int index)
    {
        if(group.get(index)!=null)
        {
            return group.get(index);
        }
        else
        {
            System.out.println("This student record does not exist!");
            return null;
        }
    }
    
    //intention to remove student from array list
    //intention to return a boolean describing method success (might not be a valid index).
    public boolean removeStudent(int index) //take an index.
    {
        if(group.get(index)!=null) //Make sure something exists at that index
        {
            //remove() takes an index number.
            group.remove(index);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int numStudents()
    {
        return group.size(); //how many items are in it. 5 students e.g.
    }
    
    public void listStudents()
    {
        if(numStudents()>0)
        {
            for(int i = 0; i < numStudents(); i++)
            {
                System.out.println("Name: "+getStudent(i).getStudentName()+"|| Grade: " + getStudent(i).getGrade());
            }
        }
        else
        {
            System.out.println("This group is currently empty." );
        }
        
        
    }
}
