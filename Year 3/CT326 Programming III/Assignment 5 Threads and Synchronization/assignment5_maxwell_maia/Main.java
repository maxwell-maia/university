import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The Main class is the entry point of the transaction processor application
 * 
 * @author Maxwell Maia, 21236277
 */
public class Main
{
    /**
     * The main method to start the application
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        //Declare and instantiate a Bank object
        Bank bank = new Bank();

        //Add three Account objects to the bank
        try
        {
            bank.addAccount(new Account(12345, Money.of(CurrencyUnit.EUR, 10000)));
            bank.addAccount(new Account(12346, Money.of(CurrencyUnit.EUR, 15000)));
            bank.addAccount(new Account(12347, Money.of(CurrencyUnit.EUR, 20000)));
        }
        catch (NegativeBalanceException e)
        {
            System.err.println("Error creating accounts: " + e.getMessage());
            return;
        }

        //Declare and instantiate two TransactionProcessor threads and one RandomTransactionGenerator thread
        TransactionProcessor tp1 = new TransactionProcessor("TPT1", bank);
        TransactionProcessor tp2 = new TransactionProcessor("TPT2", bank);
        RandomTransactionGenerator rtg = new RandomTransactionGenerator(bank);

        //Execute the threads using a thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(tp1);
        executorService.execute(tp2);
        executorService.execute(rtg);        
        executorService.shutdown();

        try
        {
            //Wait for all threads to complete
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS))
            {
                //If RandomTransactionGenerator is still running after 10 seconds, shut it down
                executorService.shutdownNow();
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.err.println("Error waiting for thread completion: " + e.getMessage());
        }

        //Print out the details of the accounts after the transactions have finished
        bank.printAccountDetails();
    }
}
