import java.util.*;

public class Postfix {

	// output queue for shunting-yard algorithm
	private static ArrayList<String> postfix = new ArrayList<String>();
	// stack used for shunting yard algorithm
	private static LinkedList<String> stack = new LinkedList<String>();
	// Associativity of operators
	private static final int LEFT_ASSOC = 0, RIGHT_ASSOC = 1;
	// Map to hold operator properties
	private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
	// contains the precedence and associativity of the operators.
	static {
		// Map<"token", []{precedence, associativity}>
		OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
		OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
		OPERATORS.put("*", new int[] { 5, LEFT_ASSOC });
		OPERATORS.put("/", new int[] { 5, LEFT_ASSOC });
		OPERATORS.put("%", new int[] { 5, LEFT_ASSOC });
		OPERATORS.put("^", new int[] { 10, RIGHT_ASSOC });
		OPERATORS.put("sqrt:", new int[] { 10, LEFT_ASSOC });
	}

	/**
	 * Converts a String Array that is in infix form to a String Array that in
	 * in postfix order.
	 * 
	 * @param infix
	 *            - String Array that is in infix.
	 * @return String Array that is in postfix.
	 */
	public static String[] InfixtoPostfix(String expression) {
		//pre-Postfix conversion processing
		String[] infix = new String[Tokenizer.tokenizeExpression(expression).size()];
		Tokenizer.tokenizeExpression(expression).toArray(infix);
		String[] nfix = NegativeParse(infix);
		ExpressionCheck(nfix);
		//to Postfix
		return convert(nfix);
		
	}

	/**
	 * Discerns the negative sign from the minus sign in the infix expression
	 * 
	 * @param infix
	 * @return infix expression that has been processed.
	 */
	public static String[] NegativeParse(String[] infix) {
		// ArrayList to hold the parsed expression
		ArrayList<String> parseholder = new ArrayList<String>();
		Boolean matchLeftParen = false;

		// checks to see if a minus sign occurs in the expression, then
		// determines whether if it is a minus or a negative sign.
		for (int i = 0; i < infix.length; i++) {
			if (isNumber(infix[i]) || isLeftParenthesis(infix[i])
					|| isRightParenthesis(infix[i])) {

				parseholder.add(infix[i]);
				// matches parenthesis
				if (matchLeftParen) {
					parseholder.add(")");
					matchLeftParen = false;
				}

			} else if (isOperator(infix[i])) {

				if (infix[i].equals("-")) {

					if (i > 0) {
						if (isOperator(infix[i - 1])
								|| isLeftParenthesis(infix[i - 1])) {

							parseholder.add("(");
							parseholder.add("-1");
							parseholder.add("*");
							matchLeftParen = true;

						} else {
							parseholder.add(infix[i]);
						}

					} else {

						parseholder.add("(");
						parseholder.add("-1");
						parseholder.add("*");
						matchLeftParen = true;

					}
				} else {
					parseholder.add(infix[i]);
					// matches parenthesis
					if (matchLeftParen) {
						parseholder.add(")");
						matchLeftParen = false;
					}
				}
			}
		}

		String[] NegativeParsed = new String[parseholder.size()];
		parseholder.toArray(NegativeParsed);
		parseholder.clear();
		return NegativeParsed;
	}

	/**
	 * Determines whether the expression is mal-formed or not. must be performed
	 * after the method NegativeParse().
	 * 
	 * @param expression
	 */
	private static void ExpressionCheck(String[] expression) {
		// counters to make sure parenthesis are matching.
		int rightParenMatch = 0;
		boolean leftParenMatch = true;
		for (int i = 0; i < expression.length; i++) {
			// parenthesis matching.
			if(isLeftParenthesis(expression[i]))
			if (isLeftParenthesis(expression[i])) {

				leftParenMatch = false;
				rightParenMatch++;
			}
			if (isRightParenthesis(expression[i])) {

				leftParenMatch = true;
				rightParenMatch--;
			}

			if (i == 0) {
				// first token of a well-formed expression must not be an
				// operator.
				if (beginOperator(expression[0])) { //changed so an expression can begin with sqrt:
					throw new IllegalStateException("Expression is mal-formed: can't begin with "+expression[0]);
				}
				// first token cannot be a right parenthesis if expression is
				// wel-formed.
				if (isRightParenthesis(expression[0])) {

					throw new IllegalStateException("Expression is mal-formed: can't begin with "+expression[0]);
				}
			}
			if (i > 0 && i < expression.length - 1) {
				if (beginOperator(expression[i])) { //changed because 3*sqrt:5 was throwing an error
					// expression cannot have to operators adjacent to each
					// other
					if ((beginOperator(expression[i - 1]) 
							|| beginOperator(expression[i + 1]))) {
						throw new IllegalStateException(
								"Expression is mal-formed: adjacent operators");
					}
				}
				if (expression[i].equals("sqrt:")){
					if (expression[i - 1].equals("sqrt:") || (expression[i + 1].equals("sqrt:"))){
						throw new IllegalStateException("Expression is mal-formed: adjacent operators");
					}
				}
				// two numbers cannot be adjacent to each other in a wellformed
				// expression.
				if (isNumber((expression[i]))) {
					if (isNumber(expression[i - 1])
							|| isNumber(expression[i + 1])) {

						throw new IllegalStateException(
								"Expression is mal-formed: adjacent numbers");
					}
				}
			}
			//test for the last entry of the expression, if the expression has more than 1 token.
			if (i == expression.length - 1 && expression.length > 1) {
				if (isOperator(expression[i])) {
					// expression cannot have to operators adjacent to each
					// other
					if (isOperator(expression[i - 1])) {

						throw new IllegalStateException(
								"Expression is mal-formed: adjacent operators");
					}
				}
				// two numbers cannot be adjacent to each other in a wellformed
				// expression.
				if (isNumber((expression[i]))) {
					if (isNumber(expression[i - 1])) {

						throw new IllegalStateException(
								"Expression is mal-formed: adjacent numbers");
					}
				}
			}
		}
		// Every parenthesis must be matched.
		if (!(leftParenMatch && rightParenMatch == 0)) {

			throw new IllegalStateException("Expression is mal-formed: unmatched parenthesis");
		}
	}

	/**
	 * Shunting-Yard algorithm for converting infix to postfix expressions.
	 * 
	 * @param infix
	 *            - String Array in infix order.
	 * @return String Array in postfix order.
	 * @throws Exception
	 */
	private static String[] convert(String[] infix) {
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
					// this shouldn't happen do to ExpressionCheck() method
					throw new IllegalStateException("no matching parenthesis");
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
		postfix.clear();
		stack.clear();
		return postfixA;
	}

	// checks whether the token is right parenthesis
	private static boolean isRightParenthesis(String string) {
		if (string.equals(")")) {
			return true;
		} else {
			return false;
		}
	}

	// checks whether the token is a left parenthesis.
	private static boolean isLeftParenthesis(String string) {

		if (string.equals("(")) {
			return true;
		} else {
			return false;
		}
	}
	
	

	// checks whether the token is an operator.
	private static boolean isOperator(String string) {
		if (string.equals("+") || string.equals("-") || string.equals("sqrt:")
				|| string.equals("*") || string.equals("^")
				|| string.equals("/") || string.equals("sqrt:")) {
			return true;
		} else {
			return false;
		}
	}
	// similar to isOperator but omits sqrt:, as expression can begin with sqrt:
	private static boolean beginOperator(String string){
		if (string.equals("+") || string.equals("-") 
				|| string.equals("*") || string.equals("^")
				|| string.equals("/")) {
			return true;
		} else {
			return false;
		}
	}
	
	// checks whether the operator is associative.
	private static boolean isAssociative(String token, int type) {
		if (!isOperator(token)) {
			throw new IllegalStateException("Mal-formed expression");
		}
		if (OPERATORS.get(token)[1] == type) {
			return true;
		}
		return false;
	}

	// compares the precedence of the operators for postfix conversion
	private static final int comparePrecedence(String token1, String token2) {
		if (!isOperator(token1) || !isOperator(token2)) {
			throw new IllegalStateException("Mal-formed expression");
		}
		return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
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

		} catch (Exception e) {
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
