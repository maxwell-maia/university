import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * A class representing an account transaction for the CT326 Assignment 5 (23/24)
 * A transaction has an account number and a transaction amount
 *
 * @author Maxwell Maia, 21236277
 */

public class Transaction
{
    private Money amount;
    private int accountNumber;

    /**
    * Create a transaction for the Account with the given account number, of the given amount.
    * 
    * @param accNumber the account number of the transaction account
    * @param amount the amount to withdraw/deposit. A positive value represents 
    * a deposit, a negative value represents a withdrawal
    */
    public Transaction(int accNumber, Money amount)
    {
        this.accountNumber = accNumber;
        this.amount = amount;
    }

    /**
     * Gets the transaction amount.
     *
     * @return The transaction amount.
     */
    public Money getAmount()
    {
        return amount;
    }

    /**
     * Gets the account number associated with the transaction.
     *
     * @return The account number.
     */
    public int getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Returns a string representation of the transaction.
     * The format depends on whether the transaction amount is positive (deposit) or negative (withdrawal).
     *
     * @return A string representation of the transaction.
     */
    @Override
    public String toString()
    {
        if (amount.isGreaterThan(Money.of(CurrencyUnit.EUR, 0)))
            return String.format("a deposit of %s to %d", amount, accountNumber);
        else
            return String.format("a withdrawal of %s from %d", amount, accountNumber);
    }
}
