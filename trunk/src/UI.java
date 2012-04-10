import java.util.Scanner;

public class UI 
{
	static Scanner sc = new Scanner(System.in);
	static String input;
	static String choice;
	static String expression;
	// Test commend for Subversion
	
	public static void main(String[] args) 
	{	
		System.out.println("Welcome to the Calculator!");
		input = sc.nextLine();
		
		while(!(input.equals("Exit")))
		{
			if (input.equals("Help"))
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
		System.out.println("3. See the previous History Item.");
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
			HistoryItem.Getters();
			System.out.println();
		}
		else if(choice.equals("4"))
		{
			HistoryItem.Setters();
			System.out.println();
		}
		else;
	}
}
