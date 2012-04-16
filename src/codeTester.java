


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
	
		String[] testEquations = {"sqrt:3","sqrt:4","sqrt:4/2","sqrt:(4/2)", "sqrt:(5/2)","sqrt:8",//testing sqrt with numbers only
				"2^2","5^3","pi^3","e^10","2^1"};
		
		String[] actualAnswers = {"sqrt:3", "2","1","sqrt:2","sqrt:5/sqrt:2","2*sqrt:2",//correspond to line 1 of testEquations
				"4","125","pi^3","e^10","2"};
		
		Boolean approximate = false;
		Calculator c = new Calculator();
		String testAnswer = "";
		for (int i=0; i<testEquations.length; i++){
			testAnswer = c.calculate(testEquations[i],approximate);
			if(testAnswer.equals(actualAnswers[i]))
				System.out.println("CORRECT!	For equation "+testEquations[i]+" ; test answer "+testAnswer+" = "+actualAnswers[i]);
			else
				System.out.println("WRONG!		For equation "+testEquations[i]+" ; test answer "+testAnswer+" != "+actualAnswers[i]);
			
		}
	}

}
