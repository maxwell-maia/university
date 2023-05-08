
/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student
{
    // instance variables - replace the example below with your own
    private String name;
    private float grade;

    /**
     * Constructor for objects of class Student
     */
    public Student(String name, float grade)
    {
        this.name = name;
        this.grade = grade;
    }
    
    public String getStudentName()
    {
        return name;
    }
    
    public float getGrade()
    {
        return grade;
    }
}
