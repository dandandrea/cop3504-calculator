import java.util.ArrayList;
import java.util.LinkedList;

public class ApproximateMath {
	/**
	 * Approximately evaluates your mathematical expression.
	 * @param expression - mathematical expression to be evaluated in an approximate form
	 * @return approximate answer to your expression
	 */
	public static String Approximate(String[] expression) {
		
		return Double.toString(EvaluteExpression(ApproximateParser(expression)));
		
	}
	/**
	 * Converts String Array to String ArrayList, and converts e and pi to their double values.
	 * @param expression
	 * @return parsedexpression - An ArrayList that has the elements of the original string array. 
	 * e and pi are converted to their double value.
	 */
	private static ArrayList<String> ApproximateParser(String[] expression) {
		
		ArrayList<String> parsedexpression = new ArrayList<String>();
		//changes all references of e and pi to their double values
		for (int i = 0; i < expression.length; i++) {
			
			if (expression[i].equals("e")) {
				
				parsedexpression.add(Double.toString(Math.E));
				
			} else if (expression[i].equals("pi")) {
			
				parsedexpression.add(Double.toString(Math.PI));
				
				
			} else {
				
				parsedexpression.add(expression[i]);
			}
		}
		return parsedexpression;
	}
	/**
	 * Evaluates expression using postfix evaluation methods. adds numbers to a linked list and evalutes them once an operator is reached.
	 * @param expression - String ArrayList containing expression to be evaluated.
	 * @return ans - answer to expression, a double.
	 */
	private static double EvaluteExpression(ArrayList<String> expression) {
		double ans, b, a;
		LinkedList<Double> stack = new LinkedList<Double>();

		// evaluation algorithm for postfix expression
		for (int i = 0; i < expression.size(); i++) {

			if (isNumber(expression.get(i))) {

				stack.push(Double.parseDouble(expression.get(i)));

			} else {

				if (expression.get(i).equals("*")) {

					stack.push(stack.pop() * stack.pop());
					

				} else if (expression.get(i).equals("/")) {

					b = stack.pop();
					a = stack.pop();
					stack.push(a / b);

				} else if (expression.get(i).equals("+")) {
					
					stack.push(stack.pop() + stack.pop());

				} else if (expression.get(i).equals("-")) {

					b = stack.pop();
					a = stack.pop();
					stack.push(a - b);

				} else if (expression.get(i).equals("^")) {

					b = stack.pop();
					a = stack.pop();
					stack.push(Math.pow(a, b));

				} else if (expression.get(i).equals("sqrt:")) {

					stack.push(Math.sqrt(stack.pop()));
				}
			}
		}

		ans = stack.pop();
		
		return ans;
	}

	/**
	 * 
	 * @param string
	 *            - string argument that will be checked to see if it is a
	 *            number
	 * @return False if the string input is not a number, true if it is.
	 */
	private static boolean isNumber(String string) {
		Boolean number = true;
		try {

			Double.parseDouble(string);

		} catch (Exception e) {
			number = false;
		}

		if (number) {
			return true;
		} else {
			return false;
		}
	}

}
