package warehouse;


public class AssemblyTest
{
   
     public void partTest()
     {
         CatalogueEntry entry  = new CatalogueEntry("nail", 2333445, 0.02);
         
         Part[] parts = new Part[1000];
         
         //Part p = new Part(entry);
         
         for(int i=0; i< parts.length; i++)
         {
            parts[i] = new Part(entry); 
            //parts[i] = p; //this also works ( maybe what he said )
            
         }
     
     }   
    
     public int costTest() //added via lecture notes in class
     {
         // TODO :Create the test code here
         Assembly assembly = new Assembly("My Assembly");
         
         CatalogueEntry entry1 = new CatalogueEntry("Screw", 212388888, 0.08); //add details to ()
         CatalogueEntry entry2 = new CatalogueEntry("Strut", 353834344, 2.00);
         
         Part p1 = new Part(entry1);
         Part p2 = new Part(entry1);
         Part p3 = new Part(entry2);
         
         assembly.add(p1);
         assembly.add(p2);
         assembly.add(p3);
         
         double total = assembly.getCost();
         
         System.out.printf("total cost: %f", total);
         
         return 0;
     }
 
     public static void main(String[] args)
     {
         AssemblyTest assmblTest = new AssemblyTest();
         assmblTest.partTest();
         int value = assmblTest.costTest();
     }
}
