package warehouse;

/**
 * AssemblyTest is used to write test code
 * for the Assembly Part classes.
 * @author (conor hayes)
 * @version (Nov 2nd)
 */
public class AssemblyTest
{
   
 public void partTest(){
     CatalogueEntry entry  = new CatalogueEntry("nail", 2333445, 0.02);
     
     Part[] parts = new Part[1000];
     for(int i=0; i< parts.length; i++){
        parts[i] = new Part(entry); 
     }
     
 }   
    
    
 public void costTest(){
     System.out.println("****Cost Test v1*****");
     
     Assembly assembly = new Assembly("Assembly #1");
     CatalogueEntry catEntryScrew = new CatalogueEntry("screw", 12344455, 0.02);
     CatalogueEntry catEntryStrut = new CatalogueEntry("strut", 3455522, 0.05);
     Part s1 = new Part(catEntryScrew); // cost 0.02
     Part s2 = new Part(catEntryScrew); // cost 0.02
     Part s3 = new Part(catEntryStrut); // 0.05
     assembly.add(s1);
     assembly.add(s2);
     assembly.add(s3);
     double total= assembly.getCost(); // should return 0.09
     System.out.printf(assembly.getName() + ": total cost of : %f \n", total);
 }
 
 public void costTestv2(){
     System.out.println("****Cost Test v2*****");
     
     CatalogueEntry catEntryScrew = new CatalogueEntry("screw", 12344455, 0.02);
     CatalogueEntry catEntryStrut = new CatalogueEntry("strut", 3455522, 0.05);
     
     Assembly assembly1 = new Assembly("Assembly #1");
     Assembly assembly2 = new Assembly("Assembly #2");
     assembly1.add(assembly2); // add Assembly #2 to Assembly #1
     
     Part s1 = new Part(catEntryScrew); // cost 0.02
     Part s2 = new Part(catEntryScrew); // cost 0.02
     Part s3 = new Part(catEntryStrut); // 0.05
     
     assembly1.add(s1);
     assembly2.add(s2);
     assembly2.add(s3);
     
     double total= assembly1.getCost(); // should return 0.09
     System.out.printf(assembly1.getName() + ": total cost of : %f \n", total);
 }
 
 
 public static void main(String[] args)
 {
        // put your code here
        AssemblyTest assmblTest = new AssemblyTest();
        assmblTest.partTest();
        assmblTest.costTest();
        assmblTest.costTestv2();
 }
 
 
 
}
