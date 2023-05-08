
/**
 * Write a description of class TestStudentGroup here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestStudentGroup
{
    public static void main(String[] args)
    {
        Student s1 = new Student("Bilbo Baggins", 88.2F);
        Student s2 = new Student("Jiminy Cricket", 64.0F);
        
        StudentGroup group1 = new StudentGroup();
        
        group1.addStudent(s1);
        group1.addStudent(s2);
        
        System.out.println("Size: "+group1.numStudents());
        System.out.println("\nThe list is...");
        group1.listStudents();
    }
}
