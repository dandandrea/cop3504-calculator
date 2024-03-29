import java.util.*;

public class SymbolicMath {
	static ArrayList<Irrational> irrationalarray = new ArrayList<Irrational>();
	
	/**
	 * High-level evaluation of a mathematical expression.
	 * @param expression - String array of expression components (Must be in postfix!)
	 * @return
	 */
	public static String calculate(String[] expression) {
		
		//Gets length of our String[]
		int length = expression.length;
		if (length == 0) return "";
		
		irrationalarray.clear();

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
				if(thisElement.equals("-")) {
					operationStack.addFirst(subtract(operationStack.pop(), operationStack.pop()).toString());
				}
				if(thisElement.equals("*")) {
					operationStack.addFirst(multiply(operationStack.pop(), operationStack.pop()).toString());
				}
				if(thisElement.equals("/")) {
					operationStack.addFirst(divide(operationStack.pop(), operationStack.pop()).toString());
				}
				if(thisElement.equals("^")) {
					operationStack.addFirst(raise(operationStack.pop(), operationStack.pop()).toString());
				}
				if(thisElement.equals("sqrt:")) {
					operationStack.addFirst(sqrt(operationStack.pop()).toString());
				}
			}
		}
		irrationalarray.clear();
		if (length == 0) return "";
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
	 * @param arg2
	 * @return
	 */
	public static Fraction add(String arg1, String arg2){
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
	 * @throws MarshalException 
	 */
	public static Fraction add(Fraction arg1, Fraction arg2){
		
		//General, polymorphic-type stuff
		arg1.getNumerator().multiply(arg2.getDenominator());
		arg2.getNumerator().multiply(arg1.getDenominator());
		arg1.getNumerator().add(arg2.getNumerator());
		arg1.getDenominator().multiply(arg2.getDenominator());
		return arg1;
	}
	
	/**
	 * High-level subtract method (which just adds a negative number).
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static Fraction subtract(String arg1, String arg2){
		Fraction f1 = new Fraction(arg2);
		Fraction f2 = new Fraction(arg1);
		f2.getNumerator().multiply(new Irrational("-1"));
		return add(f1, f2);
	}	
	
	/**
	 * High-level multiply method (using strings).
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static Fraction multiply(String arg1, String arg2){
		Fraction f1 = new Fraction(arg1);
		Fraction f2 = new Fraction(arg2);
		
		return multiply(f1, f2);
	}
	
	public static Fraction multiply(Fraction arg1, Fraction arg2){
		arg1.getNumerator().multiply(arg2.getNumerator());
		arg1.getDenominator().multiply(arg2.getDenominator());
		
		return arg1;
	}
	
	/**
	 * High-level divide method (using strings).
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static Fraction divide(String arg1, String arg2){
		Fraction f1 = new Fraction(arg2);
		Fraction f2 = new Fraction(arg1);
		f2 = new Fraction(f2.getDenominator().toString(), f2.getNumerator().toString());
		if(f2.getDenominator().getNum() == 0)
		{
			throw new ArithmeticException("Calculation error: cannot divide by zero");
		}
		return multiply(f1, f2);
	}
	
	public static Fraction raise(String arg1, String arg2){
		Fraction f1 = new Fraction(arg2);
		Fraction f2 = new Fraction(arg1); // now in form f1^f2
		Boolean  neg = false;
		if(f2.getNumerator().getNum()<0){
		
			f2.getNumerator().setNum(f2.getNumerator().getNum()*-1);
			neg = true;
		}
		
		if (f2 != null && f2.getDenominator() != null && f2.getDenominator().getNum() > 2) {
			// Are we raising to an odd root or an even root?
			// Throw an exception if raising to an odd root
			if (f2.getDenominator().getNum() % 2 == 1) {
				throw new RuntimeException("Can only raise to even roots (1/2, 1/4, 1/6, etc)");
			} else {
				// Is the numerator of the fraction that we are raising to greater than one?
				// If so then we'll apply that power to the numerator and denominator before taking the square roots
				f1.getNumerator().exponentiate(f2.getNumerator());
				f1.getDenominator().exponentiate(f2.getNumerator());

				// We're going to take a number of successive square roots based on the denominator
				// i.e. raise to 1/4th power will take 2 square roots
				//      raise to 1/6th power will take 3 square roots
				//      ...
				int numSquareRoots = f2.getDenominator().getNum() / 2;

				// Now apply the square roots
				for (int i = 0; i < numSquareRoots; i++) {
					f1.setNumerator(raise("1/2", new Integer(f1.getNumerator().getNum()).toString()).getNumerator());
					f1.setDenominator(raise("1/2", new Integer(f1.getDenominator().getNum()).toString()).getNumerator());
				}
			}
		}
		else if (f2.getDenominator().toString().equals("2")){ //special case of raising to 1/2 == sqrt:
			f1.getNumerator().exponentiate(f2.getNumerator());
			f1.getDenominator().exponentiate(f2.getNumerator());
			f1 = sqrt(f1.toString());
		}
		else if (f2.getDenominator().toString().equals("1")&&f2.getNumerator().getPie()==0&&f2.getNumerator().getEe()==0
				&&f2.getNumerator().getSqr().equals("1")){ //makes sure not raising to an irrational
			f1.getNumerator().exponentiate(f2.getNumerator());
		}
		if(neg){
			Irrational temp = new Irrational(f1.getNumerator().toString());
			f1.setNumerator(f1.getDenominator());
			f1.setDenominator(temp);
		}
		
		return f1;
	}
	
	public static Fraction sqrt(String arg1){
		Calculator c = new Calculator();
		float nTest = Float.valueOf(c.calculate(arg1, true));
		if (nTest < 0)
			throw new IllegalArgumentException("Calculation error: negative sqrt not allowed");
		
		Fraction f1 = new Fraction(arg1);
		if (isInteger(f1.getNumerator().toString())
				&&isInteger(f1.getDenominator().toString())){//case to handle integers and integer fractions
			f1.setNumerator(perfectSquare(f1.getNumerator()));
			f1.setDenominator(perfectSquare(f1.getDenominator()));
		}
		
		return f1;
	}
	private static Irrational perfectSquare(Irrational num){ // 
		Irrational ans = new Irrational("sqrt:"+num.toString());
		for(int i=num.getNum()/2; i > 0; i--){
			if(num.getNum()%(i*i)==0){
				ans.setNum(i);
				ans.setSqr(Integer.toString(num.getNum()/(i*i)));
				break;
			}
		}
		
		return ans;
	}
	

}
