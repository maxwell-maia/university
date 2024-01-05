import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * The RandomTransactionGenerator class generates random transactions
 * It submits the transactions to the bank
 * 
 * @author Maxwell Maia, 21236277
 */
public class RandomTransactionGenerator implements Runnable
{
    private Bank bank;

    /**
     * Constructor for RandomTransactionGenerator with a bank object
     *
     * @param bank The bank to submit transactions to
     */
    public RandomTransactionGenerator(Bank bank)
    {
        this.bank = bank;
    }

    /**
     * Infinite loop that generates random transactions until interrupted
     * It submits generated transactions to the bank object
     * Sleeps for a random amount of time
     */
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                generateAndSubmitRandomTransaction();
                sleepRandomTime();
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        finally
        {
            //Insert end-of-stream object to indicate the end of transaction generation
            bank.submitTransaction(new Transaction(-1, Money.of(CurrencyUnit.EUR, 0)));
            System.out.println("Transaction generator terminated.");
        }
    }

    /**
     * Generates a random account number and a random transaction amount
     * Submits a new transaction to the bank
     */
    private void generateAndSubmitRandomTransaction()
    {
        int accountNumber = getRandomAccountNumber();
        Money amount = getRandomTransactionAmount();
        bank.submitTransaction(new Transaction(accountNumber, amount));
    }

    /**
     * Picks a random account number from the accounts already existing
     *
     * @return A random account number
     */
    private int getRandomAccountNumber()
    {
        int[] existingAccountNumbers = {12345, 12346, 12347};
        return existingAccountNumbers[new Random().nextInt(existingAccountNumbers.length)];
    }

    /**
     * Generates a random transaction amount between EUR -10000 and 10000
     *
     * @return A random transaction amount.
     */
    private Money getRandomTransactionAmount()
    {
        //Random transaction amount minimum -10000, maximum 10000
        float randomAmount = (new Random().nextFloat() * 20000) - 10000;
        return Money.of(CurrencyUnit.EUR, BigDecimal.valueOf(randomAmount).setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * Sleep for a random amount of time between 0 and 1 seconds
     *
     * @throws InterruptedException If the sleep is interrupted
     */
    private void sleepRandomTime() throws InterruptedException
    {
        Thread.sleep(new Random().nextInt(1000));
    }
}
