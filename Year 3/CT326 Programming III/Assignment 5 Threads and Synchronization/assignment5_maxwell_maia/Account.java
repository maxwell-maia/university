import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * A class representing a bank account
 * @author Maxwell Maia, 21236277
 */
public class Account implements Serializable
{
    private static final long serialVersionUID = 202010191519L;
    private int accnum;
    private Money balance;
    private LocalDateTime activated;

    /**
     * Constructor for the Account class
     * Creates an account with a given account number and balance
     *
     * @param accnum The account number
     * @param balance The initial balance of the account
     * @throws NegativeBalanceException Thrown if the initial balance is negative
     */
    public Account(int accnum, Money balance) throws NegativeBalanceException
    {
        setBalance(balance);
        this.accnum = accnum;
        activated = LocalDateTime.now();
    }

    /**
    * Make a deposit to the account of the given amount
    * 
    * @param amount the amount to deposit
    */
    public synchronized void makeDeposit(Money amount)
    {
        if (amount.isGreaterThan(Money.of(CurrencyUnit.EUR, 0)))
        {
            balance = balance.plus(amount);
        }
    }

    /**
    * Make a withdrawal from the account of the given amount
    * 
    * @param amount the amount to withdraw
    * @throws InsufficientFundsException if the amount to withdraw is greater
    than the current balance
    */
    public synchronized void makeWithdrawal(Money amount)
    {
        if (amount.isGreaterThan(Money.of(CurrencyUnit.EUR, 0)))
        {
            balance = balance.minus(amount);
        }
    }

    /**
    * Set the balance of the account to the given amount
    * 
    * @param balance the new balance of the account
    * @throws NegativeBalanceException if the balance is negative
    */
    private synchronized void setBalance(Money balance) throws NegativeBalanceException
    {
        if (balance.isLessThan(Money.of(CurrencyUnit.EUR, 0)))
        {
            throw new NegativeBalanceException("Negative Balance Not Allowed!");
        }
        this.balance = balance;
    }

    /**
    * Get the balance of the account
    * 
    * @return the balance of the account
    */
    public synchronized Money getBalance()
    {
        return balance;
    }

    /**
    * Get the account number of the account
    * 
    * @return the account number of the account
    */
    public int getAccountNumber()
    {
        return accnum;
    }

    /**
    * Get the activation date of the account
    * 
    * @return the activation date of the account
    */
    public LocalDateTime getActivated()
    {
        return activated;
    }

    //This method is called when we are deserializing an instance of an Account object
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException
    {
        balance = (Money) aInputStream.readObject();
        accnum = aInputStream.readInt();
        activated = LocalDateTime.now();
    }

    //This method is called when we serialize an instance of an Account object
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException
    {
        aOutputStream.writeObject(balance);
        aOutputStream.writeInt(accnum);
    }

    /**
     * Get a string representation of the account
     *
     * @return A string containing the account number and balance
     */
    @Override
    public String toString()
    {
        return String.format("Account %d has a balance of %s.", accnum, balance);
    }
}
