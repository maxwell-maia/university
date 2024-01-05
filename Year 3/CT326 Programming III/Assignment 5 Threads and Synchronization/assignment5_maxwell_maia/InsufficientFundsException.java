/**
 * An exception representing the fact that an account has insufficient funds for a given transaction
 * @author Maxwell Maia, 21236277
 */
public class InsufficientFundsException extends Exception
{
    public InsufficientFundsException()
    {
        super("Insufficient funds");
    }
}