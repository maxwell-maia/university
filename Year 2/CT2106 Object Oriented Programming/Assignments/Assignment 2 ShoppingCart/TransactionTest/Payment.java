
/**
 * Payment - validate the payment details and return the valid boolean.
 *
 * @author Maxwell Maia
 * @version 1.0
 */
public class Payment
{
    private Customer customer;
    
    private String cardType;
    private long cardNumber;
    private String expDate;

    private Address billingAddress;
    
    private String bankName;
    
    private boolean valid;
    
    private double total;
    

    
    public Payment(Customer customer, Address billingAddress, String cardType, long cardNumber, double total, String expDate, String bankName)
    {
        this.customer = customer;
        this.billingAddress = billingAddress;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.total = total;
        this.expDate = expDate;
        this.bankName = bankName;
    }

    //Validate payment details and update the valid boolean.
    public void validateCard()
    {
        
        int cardNumLength = String.valueOf(cardNumber).length();
        
        //The card type has to be either Visa or MasterCard.
        //And the card number must be 16 digits long.
        if((cardType.equals("MasterCard") || cardType.equals("Visa")) && cardNumLength == 16)
        {
            valid = true;
        }
        else
        {
            valid = false;
        }
    }
    
    //Return the boolean that describes whether the payment details are valid or not
    public boolean isValid()
    {
        return valid;
    }
}
