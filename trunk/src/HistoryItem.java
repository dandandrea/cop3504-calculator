
public class HistoryItem 
{

	private String expression;
	private String answer;
	
	public HistoryItem(String expression, String answer)
	{
	this.expression = expression;
	this.answer = answer;
	}
	
	public String getExpression()
	{
		return expression;
	}
	
	public String getAnswer()
	{
		return answer;
	}
}
