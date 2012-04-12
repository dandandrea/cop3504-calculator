import java.util.Arrays;
import java.util.List;


public class Irrational {
	// Worst case format => 3 * sqrt:2 * pi^3 * e^3, modeled as Num*Sqr*Pi^Pi_e*E^e
	private String Num; //Integer value
	private String Sqr; //Value x in sqrt:x
	private String Pi_e; //Exponent of pi
	private String E_e; //Exponent of e
	
//*********************everything below this point is bullshit****************************
	
	//Constructor handles strings of format A*x+B^e, or any variation
	//Fractional exponents represented as .e, ie sqrt: would be e = .2
	public Irrational (String expression){
		if (testForm1(expression) == true)
			return;
		else if (testForm2(expression) == true)
			return;
		else if (testForm3(expression) == true)
			return;
		else if (testForm4(expression) == true)
			return;
		else if (testForm5(expression) == true)
			return;
		else if (testForm6(expression) == true)
			return;
		else if (testForm7(expression) == true)
			return;
		else if (testForm8(expression) == true)
			return;
		else {
			testForm9(expression);
			return;
		}
	}
	
	private boolean testForm2(String expression){ // Form: A*x (symbol, pi or e)
		
	}
	
	private boolean testForm1(String expression){ // Form: A (integer)
		if (SymbolicMath.isInteger(expression) == true){
			Coeff = expression;
			return true;
		}
		return false;
	}
	
	
	public Irrational (String coeff, String constant, String symbol, String exp){
		Coeff = coeff;
		Const = constant;
		Symbol = symbol;
		Exp = exp;
	}
	
		/**
	 * 
	 * @return coefficient of irrational object.
	 */
	public String getCoeff() {
		return this.Coeff;
	}
	
	/**
	 * 
	 * @return constant of irrational object.
	 */
	public String getConst() {
		return this.Const;
	}
	
	/**
	 * 
	 * @return symbol (i.e. pi, e) of irrational object.
	 */
	public String getSymbol() {
		return this.Symbol;
	}
	
	/**
	 * 
	 * @return exponential of irrational object.
	 */
	public String getExp() {
		return this.Exp;
	}
	
	public void setCoeff(String s){
		Coeff = s;
	}
	
	public void setSymbol(String s){
		Symbol = s;
	}
	
	public void setConst(String s){
		Const = s;
	}
	
	public void setExp(String s){
		Exp = s;
	}
	
	public String canFactor(){
		String s = null;
		if ((Const == null) && (Symbol == null) && (Exp == null))
			return Coeff;
		
		else if (Const == null){ //A*x can factor out A, A*x^e can factor out A^e
			if (Exp == null){ //Factor out the A in A*x
				s = Coeff;
			}
			else{ //Factor out A^e in A*x^e
				String[] a = {Coeff,Exp,"^"};
				s = SymbolicMath.calculate(a);
			}
		}
		else if (Exp == null){ //Checks if format A*x+B can factor out an integer
			int multiple = 1;
			int coeff = Integer.parseInt(Coeff);
			int constant = Integer.parseInt(Const);
			
			if ((coeff%constant) == 0) //initial check to see if A/B is an integer, if so B is greatest factor
				return Integer.toString(constant);
			if ((constant%coeff) == 0) //initial check to see if B/A is an integer, if so A is greatest factor
				return Integer.toString(coeff);
				
			if (coeff > constant){ // pick which is lower, Coeff or Const, and divide by two to start at highest possible factor
				multiple = constant/2;
				for(int i = multiple; i != 0; i--){
					if ((coeff%i == 0) && (constant%i == 0)){ //if integer i divides both A and B without a remainder
						if (i == 1)
							return null; //if greatest common factor found is 1, it returns no common factor
						return Integer.toString(i); //else greatest common factor found is returned
					}
				}
			}
			else {
				multiple = coeff/2;
				for(int i = multiple; i != 0; i--){
					if ((coeff%i == 0) && (constant%i == 0)){ //if integer i divides both A and B without a remainder
						if (i == 1)
							return null; //if greatest common factor found is 1, it returns no common factor
						return Integer.toString(i); //else greatest common factor found is returned
					}
				}
			}
		}
		else {
			s = null;
		}
		return s;
	}
	
	public String toString(){
		String toString;
		if ((Const == null) && (Symbol == null) && (Exp == null)){ // format "A", used for integers
			toString = Coeff;
		}
		else if ((Coeff == null) && (Const == null) && (Exp == null)){ // format "x", used for "pi" and "e"
			toString = Symbol;
		}
		else if ((Const == null) && (Exp == null)){ // format "A*x"
			toString = Coeff + "*" + Symbol;
		}
		else if ((Coeff == null) && (Exp == null)){ // format "x+B"
			toString = Symbol + "+" + Const;
		}
		else if (Exp == null){ // format "A*x+B"
			toString = Coeff + "*" + Symbol + "+" + Const;
		}
		else if ((Coeff == null) && (Const == null)){ // format "x^e"
			toString = Symbol + "^" + Exp;
		}
		else if ((Const == null) && (Symbol == null)){ // format "A^e" ***************probably unnecessary, this is equivalent to 2^2
			toString = Coeff + "^" + Exp;
		}
		else if (Const == null){ // format "A*x^e"
			toString = Coeff + "*" + Symbol + "^" + Exp;
		}
		else{ // format "A*x+B^e", any errors should return null in the string
			toString = Coeff + "*" + Symbol + "+" + Const + "^" + Exp;
		}
		return toString;
	}
	
	//testing purposes only
	public static void main(String[] args) {
		//Irrational a = new Irrational("3");
		//System.out.println(a.toString());
		//Irrational b = new Irrational("pi");
		//System.out.println(b.toString());
		//Irrational c = new Irrational("5*e");
		//System.out.println(c.toString());
		//Irrational j = new Irrational("pi+10");
		//System.out.println(d.toString());
		//Irrational e = new Irrational("5*pi+10");
		//System.out.println(e.toString());
		//Irrational f = new Irrational("pi^2");
		//System.out.println(f.toString());
		//Irrational g = new Irrational("5^2");
		//System.out.println(g.toString());
		//Irrational h = new Irrational("3*pi^.2");
		//System.out.println(h.toString());
		//Irrational i = new Irrational("3*pi+5^2");
		//System.out.println(i.toString());
		//Irrational orielly = new Irrational("pi+10");
		//System.out.println(orielly.canFactor());
		//The following expression does not work properly.
//		Irrational j = new Irrational("3+pi");
		//System.out.println(j.toString() + ", " + j.getCoeff() + ", " + j.getConst() + ", " + j.getSymbol() + ", " + j.getExp());
		List<String> test = Tokenizer.tokenizeExpression("pi+3");
		System.out.println(test);
		System.out.println(test.contains("+"));
	}
	
}
