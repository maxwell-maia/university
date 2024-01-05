/**
 * An exception representing an instance where a negative balance is set to an account
 * @author Maxwell Maia, 21236277
 */
public class NegativeBalanceException extends Exception
{
    public NegativeBalanceException(String message)
    {
        super(message);
    }
}

