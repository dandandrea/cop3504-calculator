
public class HistoryItem 
{
	private String expression;
	private String answer;
	
	public static String Getters()
	{
		return HistoryItem.get(currentHistoryItem);
	}
	
	public static void Setters()
	{
		HistoryItem.add(currentHistoryItem, answer);
	}
	
}
