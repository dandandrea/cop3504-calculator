
public class Math {
	
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
