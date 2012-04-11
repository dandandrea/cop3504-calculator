import java.util.*;

public class Postfix {

	// output queue for shunting-yard algorithm
	private static ArrayList<String> postfix = new ArrayList<String>();
	// stack used for shunting yard algorithm
	private static Stack<String> stack = new Stack<String>();
	// Associativity of operators
	private static final int LEFT_ASSOC = 0, RIGHT_ASSOC = 1, UNIARY = 1,
			BINARY = 0;
	// Map to hold operator properties
	private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
	// contains the precedence and associativity of the operators.
	static {
		// Map<"token", []{precendence, associativity}>
		OPERATORS.put("+", new int[] { 0, LEFT_ASSOC, BINARY });
		OPERATORS.put("-", new int[] { 0, LEFT_ASSOC, BINARY });
		OPERATORS.put("*", new int[] { 5, LEFT_ASSOC, BINARY });
		OPERATORS.put("/", new int[] { 5, LEFT_ASSOC, BINARY });
		OPERATORS.put("%", new int[] { 5, LEFT_ASSOC, BINARY });
		OPERATORS.put("^", new int[] { 10, RIGHT_ASSOC, BINARY });
		OPERATORS.put("sqrt:", new int[] { 15, LEFT_ASSOC, UNIARY });
	}

	/**
	 * Converts a String Array that is in infix form to a String Array that in
	 * in postfix order.
	 * 
	 * @param infix
	 *            - String Array that is in infix.
	 * @return String Array that is in postfix.
	 */
	public static String[] InfixtoPostfix(String[] infix) {
		return convert(infix);
	}

	/**
	 * Shunting-Yard algorithm for converting infix to postfix expressions.
	 * 
	 * @param infix
	 *            - String Array in infix order.
	 * @return String Array in postfix order.
	 * @throws Exception
	 */
	public static String[] convert(String[] infix) {
		for (int i = 0; i < infix.length; i++) {
			// check to see if token is a number, adds the token to postfix
			// array if it is.
			if (isNumber(infix[i])) {
				postfix.add(infix[i]);
				// check to see if token is an operator
			} else if (isOperator(infix[i])) {
				// If token is an operator
				while (!stack.isEmpty() && isOperator(stack.peek())) {

					if ((isAssociative(infix[i], LEFT_ASSOC) && comparePrecedence(
							infix[i], stack.peek()) <= 0)
							|| (isAssociative(infix[i], RIGHT_ASSOC) && comparePrecedence(
									infix[i], stack.peek()) < 0)) {
						postfix.add(stack.pop());
						continue;
					}
					break;
				}
				// Push the new operator on the stack
				stack.push(infix[i]);

			} else if (isLeftParenthesis(infix[i])) {
				stack.push(infix[i]);
			} else if (isRightParenthesis(infix[i])) {
				// exception: missing parenthesis
				if (!stack.contains("(")) {
					System.out.println("missing matching parenthesis");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.exit(0);
				}
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					postfix.add(stack.pop());
				}
				stack.pop();
			} else {
				System.out.println("invalid input");
			}

		}
		while (!stack.isEmpty()) {
			postfix.add(stack.pop()); // [S13]
		}
		String[] postfixA = new String[postfix.size()];
		postfix.toArray(postfixA);
		return postfixA;
	}

	private static boolean isRightParenthesis(String string) {
		if (string.equals(")")) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isLeftParenthesis(String string) {

		if (string.equals("(")) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isOperator(String string) {
		if (string.equals("+") || string.equals("-") || string.equals("sqrt:")
				|| string.equals("*") || string.equals("^")
				|| string.equals("/") || string.equals("sqrt:")) {
			return true;
		} else {
			return false;
		}
	}

	// checks whether the operator is associative.
	private static boolean isAssociative(String token, int type) {
		if (!isOperator(token)) {
			throw new IllegalArgumentException("Invalid token: " + token);
		}
		if (OPERATORS.get(token)[1] == type) {
			return true;
		}
		return false;
	}

	// compares the precedence of the operators for postfix conversion
	private static final int comparePrecedence(String token1, String token2) {
		if (!isOperator(token1) || !isOperator(token2)) {
			throw new IllegalArgumentException("Invalied tokens: " + token1
					+ " " + token2);
		}
		return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
	}

	// check to see if the operator is binary or uniary
	private static boolean isUniary(String operator) {
		if (OPERATORS.get(operator)[3] == 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * checks whether the string is a number
	 * 
	 * @param string
	 *            - input string
	 * @return Boolean value indicating whether the string is a number or not.
	 */
	private static boolean isNumber(String string) {
		Boolean number = true;
		try {
			Integer.parseInt(string);
		}catch (Exception e) {
			number = false;
		}

		if (string.equals("e") || string.equals("pi")) {
			return true;
		} else if (number) {
			return true;
		} else {
			return false;
		}
	}

}