import java.util.List;
import java.util.ArrayList;

public class Calculator {
	private final int maxHistoryItem = 10;
	private int currentHistoryItem;
	private List<HistoryItem> history;
	private String answer;
	
	//creates an ArrayList of history items
	public Calculator() 
	{
		history = new ArrayList<HistoryItem>();
	}
	
	//adds a HistoryItem to the list of history items
	public void addHistoryItem(HistoryItem input) 
	{

		if (history.size() < maxHistoryItem)
			history.add(input); //adds the new expression and answer to the end of the list
		else if (history.size() >= maxHistoryItem) {
			history.remove(0);
			history.add(input); //removes the very first item and adds the new item to the end of the list
		}
	}

	//returns the list of history items
	public List<HistoryItem> getHistory() 
	{
		return history;
	}

	//setter method for the current history item
	public void setCurrentHistoryItem(int choiceNum) 
	{
		currentHistoryItem = choiceNum;
	}

	//calculates the approximate answer or the exact answer based on the given expression
	public String calculate(String expression, Boolean approximate) {
		String[] post;
		// replace ans with actual answer
		try {
			if (expression.contains("ans")) {
				if (history.get(currentHistoryItem).getAnswer() != null && history.get(currentHistoryItem).getAnswer().indexOf(".") >= 0) {
					return "Expressions containing decimals are not supported.";
				} else {
					expression = expression.replace("ans", history.get(currentHistoryItem).getAnswer());
				}
			}

		} catch (NullPointerException e) {
			return "ans is not set; please visit History menu";
		} catch (IndexOutOfBoundsException e) {
			return "ans is not set; please visit History menu";
		}

		// Postfix conversion
		try {
			
			post = Postfix.InfixtoPostfix(expression);
//			System.out.print("postfix: "+post[0]); //for troubleshooting purposes
//			for(int i = 1; i < post.length; i++)
//				System.out.print(", "+ post[i]);
//			System.out.println(); //remove for final
			
		} catch (Exception e) {
			return e.getMessage();
		}

		// Approximate calculation
		if (approximate) {
			try {
				
				answer = ApproximateMath.Approximate(post);
	
				// Only add the answer if it was not null
				if (answer != null && answer.trim().equals("") == false) {
					addHistoryItem(new HistoryItem(expression, answer)); //adds the expression and the corresponding answer to the list of history item
					setCurrentHistoryItem(history.size()-1); //sets the expression and the corresponding answer to the current history item.
				}
					
				return answer;

			} catch (Exception e) {
				return e.getMessage();
			}
		}
		// Exact Calculation
		else 
		{
			try {
				answer = SymbolicMath.calculate(post);
				addHistoryItem(new HistoryItem(expression, answer)); //adds the expression and the corresponding answer to the list of history item
				setCurrentHistoryItem(history.size()-1); //sets the expression and the corresponding answer to the current history item.
				
				if(answer.equals("42")){
					answer = "Answer to the Ultimate Question of Life,the Universe, and Everything   (42)";
				}else if(answer.equals("73")){
					answer = "73, a palindrome in binary (1001001)";
				}else if(answer.equals("88")){
					answer = "time travel requires 1.21 gigawatts  (88)";
				}
				
				return answer;
			} catch (Exception e) {
				if (e.getMessage() != null && e.getMessage().equals("TOO COMPLICATED :(")) {
					return expression;
				}
				return e.getMessage();
			}

		}
	}
}
