import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import java.util.Random;

/**
 * A class representing a transaction processor
 * This class is processes transactions received from a bank
 *
 * @author Maxwell Maia, 21236277
 */

public class TransactionProcessor implements Runnable
{
    private String name;
    private Bank bank;
    private int numDeposits;
    private int numWithdrawals;

    /**
     * Constructor for a TransactionProcessor with a name and bank object
     *
     * @param name The name of the transaction processor
     * @param bank The bank from which transactions are processed
     */
    public TransactionProcessor(String name, Bank bank)
    {
        this.name = name;
        this.bank = bank;
        this.numDeposits = 0;
        this.numWithdrawals = 0;
    }

    /**
     * Infinite loop that processes transactions from the bank
     * Prints information about the processed transactions
     * Runs to completion when no new transactions are received for 5 seconds
     */
    @Override
    public void run()
    {
        while (true)
        {
            Transaction transaction = bank.getNextTransaction();

            if (transaction == null)
            {
                break;
            }

            processTransaction(transaction);
            
            //Sleep for a random amount of time between 0 and 1 seconds
            try
            {
                Thread.sleep(new Random().nextInt(1000));
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(name + " finished processing " + (numDeposits + numWithdrawals) +
                " transactions, including " + numDeposits + " deposits, and " + numWithdrawals + " withdrawals.");
    }

    /**
     * Processes a transaction
     * Update the account balance according to the transaction
     *
     * @param transaction The transaction to be processed
     */
    private void processTransaction(Transaction transaction)
    {
        int accountNumber = transaction.getAccountNumber();
        Money amount = transaction.getAmount();

        Account account = bank.getAccount(accountNumber);

        if (account != null)
        {
            if (amount.isGreaterThan(Money.of(CurrencyUnit.EUR, 0)))
            {
                //If the transaction is of a positive amount, make a deposit
                account.makeDeposit(amount);
                System.out.println(name + " is processing a deposit of " + amount + " to " + accountNumber + ".");
                numDeposits++;
            }
            else
            {
            	//Else the transaction is a withdrawal, make a withdrawal
                Money absAmount = amount.abs();
                if (account.getBalance().isGreaterThan(absAmount))
                {
                    //Make withdrawal if sufficient funds are available
                    account.makeWithdrawal(absAmount);
                    System.out.println(name + " is processing a withdrawal of " + amount + " from " + accountNumber + ".");
                    numWithdrawals++;
                }
            }
        }
        else
        {
            System.err.println(name + " encountered a null account for account number " + accountNumber);
        }
    }
}
