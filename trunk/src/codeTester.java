


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
	
		String[] testEquations = {//testing multiply with numbers and irrationals
				"4*2","2*pi","pi*pi","pi*e","3*sqrt:2","e*3","sqrt:2*e*e*e*pi*4*pi*6*sqrt:2","2*sqrt:sqrt:5","sqrt:2*sqrt:2"
				//testing divide and simplify with numbers and irrationals
				,"1/2","2/6","16/8","2/pi","e/2","e/pi","(2*pi)/(2*e)","(2*sqrt:3)/(2*sqrt:3)","pi^3/pi^2","e^2/e^3"
				//testing sqrt with numbers only
				,"sqrt:3","sqrt:4","sqrt:4/2","sqrt:(4/2)", "sqrt:(5/2)","sqrt:80"
				//testing exponents with whole numbers and whole fractions only
				,"2^2","5^3","2^1","2^(1/2)","25^(1/2)","(1/2)^2"
				};
		
		String[] actualAnswers = {//answers correspond by line and position
				"8","2*pi","pi^2","pi*e","3*sqrt:2","3*e","48*pi^2*e^3*sqrt:2","Expression is mal-formed","2"
				//
				,"1/2","1/3","2","2/pi","e/2","e/pi","pi/e","1","pi","1/e"
				//
				,"sqrt:3", "2","1","sqrt:2","sqrt:5/sqrt:2","4*sqrt:5"
				//
				,"4","125","2","sqrt:2","5","1/4"
				};
		
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
