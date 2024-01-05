//Maxwell Maia
//21236277

package assignment2_maxwell_maia;

import java.time.LocalDate;

import org.joda.money.Money;

public class Expense
{
	private LocalDate date;
	private String description;
	private ExpenseCategory category;
	private Money amount;
	private boolean approved;
	
	public Expense(LocalDate date, String description, ExpenseCategory category, Money amount)
	{
		this.date = date;
		this.description = description;
		this.category = category;
		this.amount = amount;
		this.approved = false;
	}
	
	//Getters
	public void approveExpense()
	{
		approved = true;
	}
	
	public LocalDate getDate()
	{
		return date;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public ExpenseCategory getCategory()
	{
		return category;
	}
	
	public Money getAmount()
	{
		return amount;
	}
	
	public boolean isApproved()
	{
		return approved;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s: %s - %s - %s", date, description, category, amount);
	}
}
