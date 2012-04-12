//needs complex toString method
public class Irrational {
	private String Coeff; //A
	private String Const; //B
	private String Symbol; //x
	private String Exp; //e
	
	//Constructor handles strings of format A*x+B^e, or any variation
	//Fractional exponents represented as .e, ie sqrt: would be e = .5
	public Irrational (String expression){
		String full[] = expression.split("\\+");
		if (full.length == 2){ //possible format A*x+B^e
			String aHalf1[] = full[0].split("\\*");
			if(aHalf1.length == 2){ //possible format A*x+B^e
				Coeff = aHalf1[0];
				Symbol = aHalf1[1];
				String aHalf2[] = full[1].split("\\^");
				if(aHalf2.length == 2){ //format is A*x+B^e
					Const = aHalf2[0];
					Exp = aHalf2[1];
					return;
				}//if no ^ operator, format is A*x+B
				Const = aHalf2[0];
				Exp = null;
				return;
			}	
		}
		String full2[] = expression.split("\\*");
		if (full2.length == 2){ //possible format A*x^e
			Coeff = full2[0];
			String bHalf1[] = full2[1].split("\\^");
			if (bHalf1.length == 2) { //format is A*x^e
				Symbol = bHalf1[0];
				Exp = bHalf1[1];
				Const = null;
				return;
			}
			Symbol = full2[1];//format is A*x
			Exp = null;
			Const = null;
			return;
		}
		String full3[] = expression.split("\\^");
		if (full3.length == 2){ //possible format x^e OR A^e
			Exp = full3[1];
			Const = null;
			if (SymbolicMath.isInteger(full3[0]) == true){ //format is A^e
				Coeff = full3[0];
				Symbol = null;
				return;
			}
			Coeff = null; //format is x^e
			Symbol = full3[0];
			return;
		}
		if (SymbolicMath.isInteger(full3[0]) == true){ //format is A
			Coeff = full3[0];
			Symbol = null;
			Exp = null;
			Const = null;
			return;
		}
		Coeff = null; //format is x
		Symbol = full3[0];
		Exp = null;
		Const = null;
		return;
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
		Irrational a = new Irrational("3");
		System.out.println(a.toString() + ", " + a.getCoeff() + ", " + a.getConst() + ", " + a.getSymbol() + ", " + a.getExp());
		Irrational b = new Irrational("pi");
		System.out.println(b.toString() + ", " + b.getCoeff() + ", " + b.getConst() + ", " + b.getSymbol() + ", " + b.getExp());
		Irrational c = new Irrational("5*e");
		System.out.println(c.toString() + ", " + c.getCoeff() + ", " + c.getConst() + ", " + c.getSymbol() + ", " + c.getExp());
		
		//The following expression does not work properly.
		Irrational d = new Irrational("pi+10");
		System.out.println(d.toString() + ", " + d.getCoeff() + ", " + d.getConst() + ", " + d.getSymbol() + ", " + d.getExp());
		
		Irrational e = new Irrational("5*pi+10");
		System.out.println(e.toString() + ", " + e.getCoeff() + ", " + e.getConst() + ", " + e.getSymbol() + ", " + e.getExp());
		Irrational f = new Irrational("pi^2");
		System.out.println(f.toString() + ", " + f.getCoeff() + ", " + f.getConst() + ", " + f.getSymbol() + ", " + f.getExp());
		Irrational g = new Irrational("5^2");
		System.out.println(g.toString() + ", " + g.getCoeff() + ", " + g.getConst() + ", " + g.getSymbol() + ", " + g.getExp());
		Irrational h = new Irrational("3*pi^2");
		System.out.println(h.toString() + ", " + h.getCoeff() + ", " + h.getConst() + ", " + h.getSymbol() + ", " + h.getExp());
		Irrational i = new Irrational("3*pi+5^2");
		System.out.println(i.toString() + ", " + i.getCoeff() + ", " + i.getConst() + ", " + i.getSymbol() + ", " + i.getExp());
		
		//The following expression does not work properly.
		Irrational j = new Irrational("3+pi");
		System.out.println(j.toString() + ", " + j.getCoeff() + ", " + j.getConst() + ", " + j.getSymbol() + ", " + j.getExp());
	}
	
}
