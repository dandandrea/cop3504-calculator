import java.util.*;

public class SymbolicMath {
	
	/**
	 * High-level evaluation of a mathematical expression.
	 * @param expression - String array of expression components (Must be in postfix!)
	 * @return
	 */
	public String calculate(String[] expression) {
		
		//Gets length of our String[]
		int length = expression.length;
		
		//Initializes our "Stack"
		LinkedList<String> operationStack = new LinkedList<String>();
		
		//Iterates through each element in our string array.
		for(int i = 0; i < length; i++) {
			//Push item [i] into the stack.
			operationStack.push(expression[i]);
			
			if (isOperator(operationStack.peek())) {
				//Pop the operator from the stack.
				String thisElement = operationStack.pop();
				
				//Java can't use switch() with strings, so... :/
				if(thisElement.equals("+")) {
					//Pushes result of add() into stack, while popping two previous elements.
					operationStack.addFirst(add(operationStack.pop(), operationStack.pop()).toString());
				}
				//Etc.
				else if(thisElement.equals("-")) {
					
				}
				else if(thisElement.equals("*")) {
					
				}
				else if(thisElement.equals("/")) {
					
				}
				else if(thisElement.equals("^")) {
					
				}
				else if(thisElement.equals("sqrt:")) {
					//REMEMBER: Only pops one number off.
				}
			}
		}
		
		return operationStack.peek();
		
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
	
	/**
	 * High-level add method (using strings).
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public Fraction add(String arg1, String arg2){
		Fraction f1 = new Fraction(arg1);
		Fraction f2 = new Fraction(arg2);
		return add(f1, f2);
	}
	
	/**
	 * Checks whether the string is an integer.
	 * @param string - input string
	 * @return Boolean value indicating whether the string is a number or not.
	 */
	public static boolean isInteger(String string) { //Should eventually add this method (and others like it) to a separate class.
		Boolean number = true;
		try {
			Integer.parseInt(string);
		} catch (Exception e) {
			number = false;
		}
		
		return number;
	}
	
	/**
	 * High-level add method (using Fractions).
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public Fraction add(Fraction arg1, Fraction arg2){
		
		//If both fractions are whole numbers... (adding is trivial :D)
		if (Fraction.isWholeNumber(arg1) && Fraction.isWholeNumber(arg2)) {
			
			//Create strings to manipulate
			String num1 = arg1.getNumerator().toString();
			String num2 = arg2.getNumerator().toString();
			
			//If both strings are actual numbers... (then adding is even trivial-er...!)
			if (isInteger(num1) && isInteger(num2)) {
				int int1 = Integer.parseInt(num1);
				int int2 = Integer.parseInt(num2);
				Integer int_return = int1 + int2;
				return new Fraction(int_return.toString());
			} else 
			//Otherwise, we have irrationals.
			{
				
			}
		}
	}
	
	/**
	 * High-level subtract method (which just adds a negative number).
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public Fraction subtract(String arg1, String arg2){
		//Create fraction variables to work with.
		Fraction f1 = new Fraction(arg1);
		Fraction f2 = multiply(new Fraction(arg2);
		Fraction f_result = null;
		
		
		
		return f_result.toString();
	}
	
	public Fraction multiply(String arg1, String arg2){
		//Create fraction variables to work with.
		Fraction f1 = new Fraction(arg1);
		Fraction f2 = new Fraction(arg2);
		Fraction f_result = null;
		
		
		
		return f_result.toString();
	}
	
	public Fraction divide(String arg1, String arg2){
		//Create fraction variables to work with.
		Fraction f1 = new Fraction(arg1);
		Fraction f2 = new Fraction(arg2);
		Fraction f_result = null;
		
		
		
		return f_result.toString();
	}
	
	public Fraction raise(String arg1, String arg2){
		//Create fraction variables to work with.
		Fraction f1 = new Fraction(arg1);
		Fraction f2 = new Fraction(arg2);
		Fraction f_result = null;
		
		
		
		return f_result.toString();
	}
	
	public Fraction sqrt(String arg1, String arg2){
		//Create fraction variables to work with.
		Fraction f1 = new Fraction(arg1);
		Fraction f2 = new Fraction(arg2);
		Fraction f_result = null;
		
		
		
		return f_result.toString();
	}
	
}
