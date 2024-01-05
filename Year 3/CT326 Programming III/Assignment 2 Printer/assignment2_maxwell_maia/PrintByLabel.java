//Maxwell Maia
//21236277

package assignment2_maxwell_maia;

import java.util.List;

public class PrintByLabel implements ExpensePrinter
{
	@Override
	public void print(List<Expense> expenses)
	{
		ExpenseCategory[] categories = ExpenseCategory.values();
		
		System.out.println("");
		
		for (ExpenseCategory c : categories)
		{
			System.out.println(c);
			
			for (Expense e : expenses)
			{
				if (e.getCategory() == c)
				{
					System.out.println(e);
				}
			}
			System.out.println("");
		}
	}
}
