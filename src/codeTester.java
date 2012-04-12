
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
		System.out.println(SymbolicMath.calculate(new String[]{"6", "3", "pi", "+", "5", "+", "+"}));
	}

}
