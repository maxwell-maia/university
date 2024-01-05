//Maxwell Maia
//21236277

package assignment2_maxwell_maia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class ExpensesPortal
{
	private List<Expense> expenses = new ArrayList<>();
	
	
	public void printExpenses(ExpensePrinter printer)
	{
		printer.print(expenses);
	}
	
	//Add an expense to the ArrayList
	public void submitExpense(Expense expense)
	{
		expenses.add(expense);
	}
	
	public static Money sumExpenses(List<Expense> expenses)
	{
		Money total = Money.zero(CurrencyUnit.EUR);
		
		for (Expense e : expenses)
		{
			Money expenseAmount = e.getAmount();
			
			//Convert the currency to EUR if it is not already EUR
			if(!expenseAmount.getCurrencyUnit().equals(CurrencyUnit.EUR))
			{
				//Define current exchange rate for USD to EUR
				BigDecimal exchangeRateToEUR;
				
				exchangeRateToEUR = new BigDecimal("0.95");
				
				//Convert amount to EUR
				Money convertedAmount = expenseAmount.convertedTo(CurrencyUnit.EUR, exchangeRateToEUR, RoundingMode.HALF_UP);
				total = total.plus(convertedAmount);
			}
			else
			{
				total = total.plus(expenseAmount);
			}
		}
		
		return total;
	}
	
	public static void main(String [] args)
	{
		//Create expenses
		Expense e1 = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", ExpenseCategory.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));
		Expense e2 = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", ExpenseCategory.EQUIPMENT, Money.parse("USD 540.00"));
		Expense e3 = new Expense(LocalDate.parse("2022-09-21"), "Java for Dummies", ExpenseCategory.OTHER, Money.parse("EUR 17.99"));
		Expense e4 = new Expense(LocalDate.parse("2023-10-04"), "Dinner", ExpenseCategory.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 64.00"));
		
		//Instantiate expense portal
		ExpensesPortal eP = new ExpensesPortal();
		
		//Submit expenses using the expense portal
		eP.submitExpense(e1);
		eP.submitExpense(e2);
		eP.submitExpense(e3);
		eP.submitExpense(e4);
		
		//Lambda expression to print expenses
		eP.printExpenses(expenses -> {
			for (Expense e : expenses)
			{
				System.out.println(e);
			}
		});
		
		//Anonymous inner class that prints summary of expenses
		eP.printExpenses(new ExpensePrinter() {
			@Override
			public void print(List<Expense> expenses)
			{
				System.out.println("");
				Money total = sumExpenses(expenses);
				System.out.println("There are " + expenses.size() + " expenses in the system totaling to a value of " + total);
				System.out.println("");
			}
		});
		
		//PrinterByLabel for organise and print
		PrintByLabel instance = new PrintByLabel();
		eP.printExpenses(instance);
	}
}
