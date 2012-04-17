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
		return multiply(f1, f2);
	}
	
	public static Fraction raise(String arg1, String arg2){
		Fraction f1 = new Fraction(arg2);
		Fraction f2 = new Fraction(arg1); // now in form f1^f2
		if (f2.toString().equals("1/2")){ //special case of raising to 1/2 == sqrt:
			f1 = sqrt(f1.toString());
		}
		else if (f2.getDenominator().toString().equals("1")&&f2.getNumerator().getPie()==0&&f2.getNumerator().getEe()==0
				&&f2.getNumerator().getSqr().equals("1")){ //makes sure not raising to an irrational
			f1.getNumerator().exponentiate(f2.getNumerator());
		}
		
		
		return f1;
	}
	
	public static Fraction sqrt(String arg1){
		Calculator c = new Calculator();
		float nTest = Float.valueOf(c.calculate(arg1, true));
		if (nTest < 0)
			throw new IllegalArgumentException("Cannot find square root of negative number");
		
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
	
	public static void addIrrationalItem(Irrational new_item){
		irrationalarray.add(new_item);
	}
	public Irrational getIrrationalItem(int i){
		return irrationalarray.get(i);
	}
	
}
