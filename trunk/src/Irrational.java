import java.util.Arrays;
import java.util.List;


public class Irrational {
	// Worst case format => 3 * sqrt:2 * pi^3 * e^3, modeled as Num*Sqr*Pi^Pi_e*E^e
	private int Num; //Integer value
	private String Sqr; //Value x in sqrt:x
	private int Pie; //Exponent of pi
	private int Ee; //Exponent of e
	
	//Constructor to handle a string input
	public Irrational (String expression){
		if (testForm1(expression) == true){ // initial check if string is an integer
			return;
		}
		String[] full = expression.split("\\*");
		if (full.length > 3){ //3 instances of '*', must be worst case format
			testForm2(full);
			return;
		}
	}
	
	private void testForm2(String[] full){ // Form: Num*Sqr*Pi^Pi_e*E^e
		Num = Integer.parseInt(full[0]);   // assumes for sqrt:x, x contains no '*' symbol
		Sqr = full[1];
		String[] split = full[2].split("\\^");
		Pie = 1;
		if (split.length == 2){
			Pie = Integer.parseInt(split[1]);
		}
		String[] split2 = full[3].split("\\^");
		Ee = 1;
		if (split2.length == 2){
			Ee = Integer.parseInt(split2[1]);
		}
		//probably works...
	}
	
	private boolean testForm1(String expression){ // Form: Num (integer)
		if (SymbolicMath.isInteger(expression) == true){
			Num = Integer.parseInt(expression);
			Sqr = "1";
			Pie = 0;
			Ee = 0;
			return true;
		}
		return false;
	}
	
	
	public Irrational (int num, String sqr, int pie, int ee){
		Num = num;
		Sqr = sqr;
		Pie = pie;
		Ee = ee;
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
