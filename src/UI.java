import java.util.Scanner;

public class UI 
{
	static Scanner sc = new Scanner(System.in);
	static String input = "";
	static String choice;
//	static String expression;
	static String choice2;
	static Calculator calculator = new Calculator();

	public static void main(String[] args) 
	{	
		// For now we'll simply load some dummy data for the history
		calculator.addHistoryItem(new HistoryItem("1+0", "235"));
		calculator.addHistoryItem(new HistoryItem("1+1", "3"));
		calculator.addHistoryItem(new HistoryItem("1+2", "123214"));
		calculator.addHistoryItem(new HistoryItem("1+3", "54"));

		// Display the welcome banner
		System.out.println("Welcome to the Calculator!");
		printMainMenu();

		while (input != null && input.equalsIgnoreCase("Exit") == false && input.equalsIgnoreCase("Back") == false) {
			input = sc.nextLine();

			if (input.equalsIgnoreCase("Calculate"))
			{
				calculate();
				printMainMenu();
			}
			else if (input.equalsIgnoreCase("Help"))
			{
				help();
				printMainMenu();
			}
			else if(input.equalsIgnoreCase("History"))
			{
				history();
				printMainMenu();
			}
			else if(input.equalsIgnoreCase("Exit") || input.equalsIgnoreCase("Back"))
			{
				exit();
			}
			else
			{
				System.out.println("Unknown command \"" + input + "\".");
				printMainMenu();
			}
		}
	}

	private static void calculate()
	{
		System.out.println("Type in an expression.");
		
		System.out.println("Back: Go back to the main menu.");
		System.out.println("Exit to exit.");
		
		choice2 = sc.nextLine();
		
			if(choice2.equalsIgnoreCase("exit"))
			{
				System.out.println("Bye!");
				System.exit(0);
			}
			else if(choice2.equalsIgnoreCase("back"))
			{
				printMainMenu();
			}
			else
			{
				calculator.calculate(choice2);
			}
		}
	
	
	private static void exit()
	{
		System.out.println("Bye!");
		System.exit(0);
	}
	
	private static void history()
	{
		choice = "";
		while (choice != null && choice.equalsIgnoreCase("back") == false) {
			System.out.println();
			System.out.println("*** History menu ***");
			System.out.println("Please choose from the following commands:");
			System.out.println("1: See the previous History Items.");
			System.out.println("2: Set History Item.");
			System.out.println("Back: Go back to the main menu.");
			System.out.println("Exit to exit.");
			System.out.println();

			choice = sc.nextLine();
			if (choice.equalsIgnoreCase("back")) 
			{
				break;
			} 
			else if(choice.equalsIgnoreCase("exit"))
			{
				System.out.println("Bye!");
				System.exit(0);
			}
			else {
				history(choice);
			}
		}
	}
	
	private static void history(String choice)
	{
		if(choice.equals("1"))
		{
			for(int i = 0; i < calculator.getHistory().size(); i++)
			{
				System.out.println((i+1) + ". " + calculator.getHistory().get(i).getExpression() + "=" + calculator.getHistory().get(i).getAnswer());
			}
		}
		else if(choice.equals("2"))
		{
			System.out.println("Pleae enter a history item number:");
			String choiceNum = sc.nextLine();
			try {
				if(Integer.parseInt(choiceNum) >= 1 && Integer.parseInt(choiceNum) <= calculator.getHistory().size())
				{
					calculator.setCurrentHistoryItem(Integer.parseInt(choiceNum) - 1);
					System.out.println("You chose " + calculator.getHistory().get(Integer.parseInt(choiceNum) - 1).getAnswer());
				}
					
				else
					System.out.println("Please enter a number between 1 and " + calculator.getHistory().size());
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a valid number");
			}
		}
		else
		{
			System.out.println("Unknown command \"" + choice + "\".");
		}
	}
	
	private static void help()
	{
		choice = "";
		while (choice != null && choice.equalsIgnoreCase("back") == false) {
			System.out.println();
			System.out.println("*** Help menu ***");
			System.out.println("Please choose from the following commands:");
			System.out.println("1: See examples of good input.");
			System.out.println("2: See supported operations and numbers.");
			System.out.println("Back: Go back to the main menu.");
			System.out.println("Exit to exit.");
			System.out.println();

			choice = sc.nextLine();
			if (choice.equalsIgnoreCase("back")) 
			{
				break;
			} 
			else if(choice.equalsIgnoreCase("exit"))
			{
				System.out.println("Bye!");
				System.exit(0);
			}
			else 
			{
				help(choice);
			}
		}
	}

	private static void help(String choice)
	{
		if(choice.equals("1"))
		{
			System.out.println("(1/4)*(1/8)");
			System.out.println("3^(-1/2)");
			System.out.println("1/(2^(1/2))");
		}
		else if(choice.equals("2"))
		{
			System.out.println("The supported operations are: ");
			System.out.println("Addition, Subtraction, Multiplication, Division, and Exponentiation.");
			System.out.println("The supported numbers are: ");
			System.out.println("Rational numbers and the irrational numbers pi and e.");
		}
		else
		{
			System.out.println("Unknown command \"" + choice + "\".");
		}
	}

	private static void printMainMenu() {
		System.out.println();
		System.out.println("*** Main menu ***");
		System.out.println("Please choose from the following commands:");
		System.out.println("Calculate");
		System.out.println("Help");
		System.out.println("History");
		System.out.println("Exit or Back");
		System.out.println();
	}
}
