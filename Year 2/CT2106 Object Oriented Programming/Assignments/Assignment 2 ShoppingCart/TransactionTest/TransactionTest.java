
/**
 * TransactionTest - Test the program.
 *
 * @author Maxwell Maia
 * @version 1.0
 */
public class TransactionTest
{
    public TransactionTest()
    {
        // initialise instance variables
        
    }

    public static void main(String[] args)
    {
        TransactionTest test = new TransactionTest();
        
        //Run scenarios
        test.transaction1();
        test.transaction2();
    }
    
    public void transaction1()
    {
        System.out.println("\n\n=================================================================================");

        System.out.println("\n\nWelcome to my shop!\n\n");
        
        Customer customer1 = new Customer("Niamh", "O'Leary", "niamholz@zmail.com");
        ShoppingCart cart1 = new ShoppingCart(customer1);
        
        
        //Create 3 items
        Item item1 = new Item("10KW Generator", 999.99, 1);
        Item item2 = new Item("Watering can", 2.99, 31);
        Item item3 = new Item("Logitech G305", 35.99, 41);
        
        //Add 3 items to the cart
        cart1.addItem(item1);
        cart1.addItem(item2);
        cart1.addItem(item3);
        
        
        //Set addresses
        Address deliveryAddress = new Address("Woodsgift House, L1815", "Borrisbeg", "County Kilkenny", "Ireland", "E41 V9K3");
        Address billingAddress = new Address("Woodsgift House, L1815", "Borrisbeg", "County Killkenny", "Ireland", "E41 V9K3");
    
        //Confirm the cart and make an order
        cart1.close();
        
        Order order = new Order(cart1, customer1, deliveryAddress, billingAddress);
        
        
        //Add payment method
        
        Payment payment1 = new Payment(customer1, billingAddress, "MasterCard", 2658418599332067L, order.getTotal(), "03/12/2023", "AIB");
        
        /*
         * I included a payment amount in the call to payment below [order.getTotal()]
         * so that someone can upgrade this code in the future
         * to be able to check whether the card has enough funds to make the order.
        */
       
        //Validate the card details
        payment1.validateCard();
        
        
        Email email = new Email(order);
        if (payment1.isValid())
        {
            //Payment details are valid
            //EMAIL SUCCESS
            email.success();
        }
        else
        {
            //Payment details are invalid
            //EMAIL
            email.regret();
        }
        
        
        System.out.println("\n");
        System.out.println("\n");
    }
    
    public void transaction2()
    {
        System.out.println("\n\n=================================================================================");
        
        System.out.println("\n\nWelcome to my shop!\n\n");
        
        Customer customer2 = new Customer("Maxwell", "Maia", "bestemail@zmail.com");
        ShoppingCart cart2 = new ShoppingCart(customer2);
        
        //Create 3 items
        Item nr1 = new Item("Minecraft", 7.99, 1);
        Item nr2 = new Item("Overwatch 2", 30.00, 31);
        Item nr3 = new Item("StatTrak Case Hardened FN #1 SCAR PATTERN", 20.00, 999);
        
        //Add 3 items to the cart
        cart2.addItem(nr1);
        cart2.addItem(nr2);
        cart2.addItem(nr3);
        
        //Request display of the shopping cart items and total
        cart2.printItems();
        
        //Remove one item
        cart2.removeItem(nr1);
        
        //Set addresses
        Address deliveryAddress = new Address("Kill Lane", "Foxrock", "County Galway", "Ireland", "H91 GP8D");
        Address billingAddress = new Address("25 Rose Inn St.", "Killkenny", "County Killkenny", "Ireland", "R95 VK02");
    
        //Confirm the cart and make an order
        cart2.close();
        
        Order order = new Order(cart2, customer2, deliveryAddress, billingAddress);
        
        
        //Add payment method
        Payment payment2 = new Payment(customer2, billingAddress, "MasterCard", 5428L, order.getTotal(), "09/09/2023", "AIB");
        
        /* REPEATED COMMENT
         * I included a payment amount in the call to payment below [order.getTotal()]
         * so that someone can upgrade this code in the future
         * to be able to check whether the card has enough funds to make the order.
        */
       
        //Validate the card details
        payment2.validateCard();
        
        
        Email email = new Email(order);
        if (payment2.isValid())
        {
            //Payment details are valid
            //EMAIL SUCCESS
            email.success();
        }
        else
        {
            //Payment details are invalid
            //EMAIL
            email.regret();
        }
        
        
        System.out.println("\n");
        System.out.println("\n");
    }
    
    
}
