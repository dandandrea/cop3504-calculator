import java.util.Scanner;

public class UI 
{
	static Scanner sc = new Scanner(System.in);
	static String input;
	static String choice;
	static String expression;
	static Calculator calculator = new Calculator();
	
	public static void main(String[] args) 
	{	
		System.out.println("Welcome to the Calculator!");
		input = sc.nextLine();
	/*	
		calculator.addHistoryItem(new HistoryItem("1+0", "235"));
		calculator.addHistoryItem(new HistoryItem("1+1", "3"));
		calculator.addHistoryItem(new HistoryItem("1+2", "123214"));
		calculator.addHistoryItem(new HistoryItem("1+3", "54"));
	*/	
		while(!(input.equalsIgnoreCase("Exit")))
		{
			if (input.equalsIgnoreCase("Help"))
			{
				Help();
				System.out.println("Welcome to the Calculator!");
				System.out.println();
				input = sc.nextLine();
			}
			else
			{
				expression = sc.nextLine();
			}
		}
			Exit();		
	}
	
	private static void Exit()
	{
		System.out.println("Bye!");
		System.exit(0);
	}
	
	private static void Help()
	{
		System.out.println("Choose from the following:");
		System.out.println("1. See examples of good input.");
		System.out.println("2. See supported operations and numbers.");
		System.out.println("3. See the previous History Items.");
		System.out.println("4. Set History Item.");
		System.out.println();
		
		choice = sc.nextLine();
		Help(choice);
		
	}

	private static void Help(String choice)
	{
		if(choice.equals("1"))
		{
			System.out.println("(1/4)*(1/8)");
			System.out.println("3^(-1/2)");
			System.out.println("1/(2^(1/2))");
			System.out.println();
		}
		else if(choice.equals("2"))
		{
			System.out.println("The supported operations are: ");
			System.out.println("Addition, Subtraction, Multiplication, Division, and Exponentiation.");
			System.out.println("The supported numbers are: ");
			System.out.println("Rational numbers and the irrational numbers pi and e.");
			System.out.println();
		}
		else if(choice.equals("3"))
		{
			for(int i = 0; i < calculator.getHistory().size(); i++)
			{
				System.out.println((i+1) + ". " + calculator.getHistory().get(i).getExpression() + "=" + calculator.getHistory().get(i).getAnswer());
			}
			System.out.println();
			
		}
		else if(choice.equals("4"))
		{
			System.out.println("Choose a history item.");
			String choiceNum = sc.nextLine();
			try {
				if(Integer.parseInt(choiceNum) >= 1 && Integer.parseInt(choiceNum) <= calculator.getHistory().size())
				{
					calculator.setCurrentHistoryItem(Integer.parseInt(choiceNum) - 1);
					System.out.println("You chose " + calculator.getHistory().get(Integer.parseInt(choiceNum) - 1).getAnswer());
				}
					
				else
					System.out.println("Please enter a number between 1 and " + calculator.getHistory().size());
				System.out.println();
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a valid number");
				
				System.out.println();
			}
		}
		else;
	}
}
