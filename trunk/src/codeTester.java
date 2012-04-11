
public class codeTester {
	
	public static boolean isOperator(String input) {
		
		String[] operators = {"+" ,"-" ,"*" ,"/" ,"^" ,", ", "sqrt:"};
		
		for(int i = 0; i < operators.length; i++) {
			if (input.equals(operators[i])) return true;
		}
		return false;
		
	}

	/** For any and all code snippets anyone wishes to test.
	 * @param args
	 */
	public static void main(String[] args) {
		String op = "-";
		if (isOperator(op))
			System.out.println(op + " is an operator!");
		else
			System.out.println(op + " is NOT an operator!");
	}

}
