import java.util.List;
import java.util.ArrayList;


public class Calculator 
{
	private final int maxHistoryItem = 3;
	private int currentHistoryItem;
	private List<HistoryItem> history;
	private String answer;
	
	public Calculator()
	{
		history = new ArrayList<HistoryItem>();
	}

	
	public void addHistoryItem(HistoryItem input)
	{
		
		if(history.size() < maxHistoryItem)
			history.add(input);
		else if(history.size() >= maxHistoryItem)
		{
			history.remove(0);
			history.add(input);
		}
	}
	
	public List<HistoryItem> getHistory()
	{
		return history;
	}
	
	public void setCurrentHistoryItem(int choiceNum)
	{
		currentHistoryItem = choiceNum;
	}

	public String calculate(String expression, Boolean approximate)
	{
		System.out.println("Answer is always 42.");
		
		String[] post = Postfix.InfixtoPostfix(expression);
		
		if(approximate == true)
		{
			answer = ApproximateMath.Approximate(post);
			return answer;
		}
		else
		{
			answer = SymbolicMath.calculate(post);
			return answer;
			
		}
	}	
	
}
