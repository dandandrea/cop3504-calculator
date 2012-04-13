import java.util.List;
import java.util.ArrayList;

public class Calculator {
	private final int maxHistoryItem = 3;
	private int currentHistoryItem;
	private List<HistoryItem> history;
	private String answer;

	public Calculator() {
		history = new ArrayList<HistoryItem>();
	}

	public void addHistoryItem(HistoryItem input) {

		if (history.size() < maxHistoryItem)
			history.add(input);
		else if (history.size() >= maxHistoryItem) {
			history.remove(0);
			history.add(input);
		}
	}

	public List<HistoryItem> getHistory() {
		return history;
	}

	public void setCurrentHistoryItem(int choiceNum) {
		currentHistoryItem = choiceNum;
	}

	public String calculate(String expression, Boolean approximate) {
		String[] post;
		// replace ans with actual answer
		try {
			if (expression.contains("ans")) {
				if (ApproximateMath.isNumber((history.get(currentHistoryItem)
						.getAnswer()))) {
					return "Expressions containing decimals are not supported.";
				} else {
					expression.replace("ans", history.get(currentHistoryItem)
							.getAnswer());
				}
			}

		} catch (NullPointerException e) {
			System.out.println("ans is not set; please visit History menu");
		}

		System.out.println("Answer is always 42.");
		// Postfix conversion
		try {
			post = Postfix.InfixtoPostfix(expression);
		} catch (Exception e) {
			return e.getMessage();
		}

		// Approximate calculation
		if (approximate) {
			try {
				answer = ApproximateMath.Approximate(post);
				addHistoryItem(new HistoryItem(expression, answer));
				return answer;

			} catch (Exception e) {
				return e.getMessage();
			}
		}
		// Exact Calculation
		else {
			answer = SymbolicMath.calculate(post);
			return answer;

		}
	}

}
