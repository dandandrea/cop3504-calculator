import java.util.*;

public class Math {
	
	/**
	 * High-level evaluation of a mathematical expression.
	 * @param expression - String array of expression components (Must be in postfix!)
	 * @return
	 */
	public String calculate(String[] expression) {
		
		//Result is the final expression as a string.
		String result = null;
		
		//Gets length of our String[]
		int length = expression.length;
		
		//Initializes our "Stack"
		LinkedList<String> operationStack = new LinkedList<String>();
		
		//Iterates through each element in our string array.
		for(int i = 0; i < length; i++) {
			//Push item [i] into the stack.
			operationStack.addFirst(expression[i]);
			
			if (isOperator(operationStack.peek())) {
				//Pop the operator from the stack.
				String thisElement = operationStack.remove();
				
				//Java can't use switch() with strings, so... :/
				if(thisElement.equals("+")) {
					//Pushes result of add() into stack, while popping two previous elements.
					operationStack.addFirst(add(operationStack.remove(), operationStack.remove()));
				}
				//Etc.
				if(thisElement.equals("-")) {
					
				}
				if(thisElement.equals("*")) {
					
				}
				if(thisElement.equals("/")) {
					
				}
				if(thisElement.equals("^")) {
					
				}
				if(thisElement.equals("sqrt:")) {
					
				}
			}
		}
		
		return result;
		
	}
	
	/**
	 * Checks if the inputted string is an operator (i.e. +,-,*,/,^,sqrt:)
	 * @param input
	 */
	public static boolean isOperator(String input) {
		
		String[] operators = {"+" ,"-" ,"*" ,"/" ,"^" ,", ", "sqrt:"};
		
		for(int i = 0; i < operators.length; i++) {
			if (input.equals(operators[i])) return true;
		}
		return false;
		
	}
	
	private String add(String arg1, String arg2){
		// need to determine what strings are, then pass to more specific
		// everything can be represented as a fraction
		// send to method returnFrac, will take the input string and return a fraction type, 
		// then can add using fraction method for addition
		// then detect if denominator is 1 for return string, if so, just return numerator as string
		Fraction arg1 = returnFrac()
		
	}
	
	private Fraction returnFrac(String input){
		// need to account for different cases -- just integer, irrationals (sqrts:), actual fractions
		// first case, integers
		if (isInteger(input) == true){
			Fraction output = new Fraction(input,"1");
		}
	}
	
	private boolean isInteger(String input){
		boolean val = false;
		try {
			int x = Integer.parseInt(input);
			val = true;
		} catch (NumberFormatException e){
			val = false;
		} finally {
			return val;
		}
	}
//	private static Fraction add(Irrational a1, Fraction a2){
		
//	}
}
