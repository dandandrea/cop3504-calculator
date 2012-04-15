//needs complex toString method
public class Irrational_Ex {
	
	//Format is api^b + ce^d + epi^fe^g + h
	private Integer Int = 0;
	private Integer Sqr = 0;
	private Integer Pi_e = 0;
	private Integer E_e = 0;
	
	//Constructor handles strings of format A*x+B^e, or any variation
	//Fractional exponents represented as .e, ie sqrt: would be e = .5
	public Irrational_Ex (String expression){
		
		return;
	}
	
	public Irrational_Ex (String coeff, String constant, String symbol, String exp){
		
	}
	
	//needs simplifier
	
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
		Irrational_Ex a = new Irrational_Ex("3");
		System.out.println(a.toString() + ", " + a.getCoeff() + ", " + a.getConst() + ", " + a.getSymbol() + ", " + a.getExp());
		Irrational_Ex b = new Irrational_Ex("pi");
		System.out.println(b.toString() + ", " + b.getCoeff() + ", " + b.getConst() + ", " + b.getSymbol() + ", " + b.getExp());
		Irrational_Ex c = new Irrational_Ex("5*e");
		System.out.println(c.toString() + ", " + c.getCoeff() + ", " + c.getConst() + ", " + c.getSymbol() + ", " + c.getExp());
		
		//The following expression does not work properly.
		Irrational_Ex d = new Irrational_Ex("pi+10");
		System.out.println(d.toString() + ", " + d.getCoeff() + ", " + d.getConst() + ", " + d.getSymbol() + ", " + d.getExp());
		
		Irrational_Ex e = new Irrational_Ex("5*pi+10");
		System.out.println(e.toString() + ", " + e.getCoeff() + ", " + e.getConst() + ", " + e.getSymbol() + ", " + e.getExp());
		Irrational_Ex f = new Irrational_Ex("pi^2");
		System.out.println(f.toString() + ", " + f.getCoeff() + ", " + f.getConst() + ", " + f.getSymbol() + ", " + f.getExp());
		Irrational_Ex g = new Irrational_Ex("5^2");
		System.out.println(g.toString() + ", " + g.getCoeff() + ", " + g.getConst() + ", " + g.getSymbol() + ", " + g.getExp());
		Irrational_Ex h = new Irrational_Ex("3*pi^2");
		System.out.println(h.toString() + ", " + h.getCoeff() + ", " + h.getConst() + ", " + h.getSymbol() + ", " + h.getExp());
		Irrational_Ex i = new Irrational_Ex("3*pi+5^2");
		System.out.println(i.toString() + ", " + i.getCoeff() + ", " + i.getConst() + ", " + i.getSymbol() + ", " + i.getExp());
		
		//The following expression does not work properly.
		Irrational_Ex j = new Irrational_Ex("3+pi");
		System.out.println(j.toString() + ", " + j.getCoeff() + ", " + j.getConst() + ", " + j.getSymbol() + ", " + j.getExp());
	}
	
}
