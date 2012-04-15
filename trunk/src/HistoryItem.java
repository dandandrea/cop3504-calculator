
public class HistoryItem 
{

	private String expression;
	private String answer;
	
	public HistoryItem(String expression, String answer)
	{
	this.expression = expression;
	this.answer = answer;
	}
	
	//getters method for the expression
	public String getExpression()
	{
		return expression;
	}
	
	//getters method for the answer
	public String getAnswer()
	{
		return answer;
	}
}
