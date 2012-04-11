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
	
	//eclipse said to make this 'visible', removed 'public' from method? potential for errors?
	static boolean isInteger(String input){
		boolean val = false;
		try {
			int x = Integer.parseInt(input); // tries to make the string an integer
			val = true;
		} catch (NumberFormatException e){
			val = false;
		} finally {
			return val;
		}
	}
	
	/**
	 * Master add method called by calculate method
	 * @param String input 1 from the calculate stack
	 * @param String input 2 from the calculate stack
	 * @return Returns string to be pushed on 
	 */
	private static String add(String arg1, String arg2){
		Fraction add1 = new Fraction(arg1);
		Fraction add2 = new Fraction(arg2);
		Fraction ans = addFrac(add1,add2);
		return ans.toString();
	}
	
	private static Fraction addFrac(Fraction f1, Fraction f2){
		// First case addresses straight integer addition
		if ((f1.getDenominator() == "1") && (f2.getDenominator() == "1")){
			f1.setNumerator(Integer.toString(Integer.parseInt(f1.getNumerator())+Integer.parseInt(f2.getNumerator())));
			return f1;
		}
		else if ((f1.allInt == true) && (f2.allInt == true)){
			
		}
		else{
			return f2;
		}
	}
	
	
	//For testing purposes
	public static void main(String[] args) {
		String arg1 = "2233";
		String arg2 = "44/55";
		String[] split,split2;
		split = arg1.split("/");
		split2 = arg2.split("/");
		System.out.println(split.length);
		System.out.println(split2.length);
			        
	}
}
