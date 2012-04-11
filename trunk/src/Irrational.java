//needs complex toString method
public class Irrational {
	private String Coeff; //A
	private String Const; //B
	private String Symbol; //x
	private String Exp; //e
	
	//Constructor handles strings of format A*x+B^e, or any variation
	//Fractional exponents represented as .e, ie sqrt: would be e = .5
	public Irrational (String expression){
		String full[] = expression.split("+");
		if (full.length == 2){ //possible format A*x+B^e
			String aHalf1[] = full[0].split("*");
			if(aHalf1.length == 2){ //possible format A*x+B^e
				Coeff = aHalf1[0];
				Symbol = aHalf1[1];
				String aHalf2[] = full[1].split("^");
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
		full = expression.split("*");
		
	}
	public Irrational (String coeff, String constant, String symbol, String exp){
		Coeff = coeff;
		Const = constant;
		Symbol = symbol;
		Exp = exp;
	}
	
	public String toString(){
		
	}
	
	//testing purposes only
	public static void main(String[] args) {
			        
	}
	
}
