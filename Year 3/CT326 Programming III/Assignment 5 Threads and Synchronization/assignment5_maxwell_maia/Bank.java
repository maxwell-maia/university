import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A class representing a bank
 * @author Maxwell Maia, 21236277
 */
public class Bank
{
    private Map<Integer, Account> accounts;
    private LinkedBlockingQueue<Transaction> transactionQueue;
    private volatile boolean terminated;  //Added flag

    /**
     * Constructor for the Bank class
     * 
     * Creates a ConcurrentHashMap for the accounts
     * Creates a LinkedBlockingQueue for the transactions
     */
    public Bank()
    {
        this.accounts = new ConcurrentHashMap<>();
        this.transactionQueue = new LinkedBlockingQueue<>();
        this.terminated = false;
    }

    /**
     * Add account to the bank
     *
     * @param account The account to add
     */
    public void addAccount(Account account)
    {
        accounts.put(account.getAccountNumber(), account);
    }

    /**
     * Get account by account number
     *
     * @param accountNumber The account number
     * @return The account linked to the account number
     */
    public Account getAccount(int accountNumber)
    {
        return accounts.get(accountNumber);
    }

    /**
     * Submit a transaction to the bank
     *
     * @param transaction The transaction to submit
     */
    public void submitTransaction(Transaction transaction)
    {
        try
        {
            if (!terminated)
            {
                transactionQueue.put(transaction);
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Get the next transaction from the bank
     *
     * @return The next transaction, or null if terminated
     */
    public Transaction getNextTransaction()
    {
        try
        {
            if (!terminated)
            {
                return transactionQueue.take();
            }
            else
            {
                return null;  //Returns null when terminated
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /**
     * Print the account details of all accounts in the bank
     */
    public void printAccountDetails()
    {
        for (Account account : accounts.values())
        {
            System.out.println(account);
        }
    }

    /**
     * Get an Iterable of account numbers in the bank
     *
     * @return An Iterable of account numbers
     */
    public Iterable<Integer> getAccountNumbers()
    {
        return accounts.keySet();
    }

    /**
     * Set the terminated flag, indicating that no more transactions should be accepted
     */
    public void setTerminated()
    {
        this.terminated = true;
    }
}
