
public class codeTester {
	
	public static boolean isOperator(String input) {
		
		String[] operators = {"+" ,"-" ,"*" ,"/" ,"^" ,", ", "sqrt:"};
		
		for(int i = 0; i < operators.length; i++) {
			if (input.equals(operators[i])) return true;
		}
		return false;
		
	}
	
	public static String sqrtify(Integer input) {
		for (int i = 1; i <= input; i++) {
			int perfectRoot = (int) Math.pow(2.0, i);
			if (input % perfectRoot == 0) {
				return (perfectRoot + "*sqrt:" + (input/perfectRoot));
			}
		}
		return ("sqrt:" + input);
	}

	/** For any and all code snippets anyone wishes to test.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(SymbolicMath.add("3/8", "3/8").toString());
		System.out.println(SymbolicMath.subtract("1", "-3").toString());
		
		//This doesn't work.
		System.out.println(SymbolicMath.multiply("-1", "pi").toString());
		
		System.out.println(SymbolicMath.divide("3/2", "1/4").toString());
		//System.out.println(SymbolicMath.calculate(new String[]{"6", "3", "pi", "+", "5", "+", "+"}));
	}

}
