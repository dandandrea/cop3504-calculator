
public class Fraction {
	private Irrational Numerator;
	private Irrational Denominator;
	
	public Fraction (String expression){
		String split[] = expression.split("/");
		if (split.length == 2){
			Numerator = new Irrational(split[0]);
			Denominator = new Irrational(split[1]);
		}
		else {
			Numerator = new Irrational(expression);
			Denominator = new Irrational("1");
		}
		
		
		
//		if (Math.isInteger(expression) == true){//case when only integer is entered
//			Numerator = expression;
//			Denominator = "1";
//		}
//		else if (split.length == 2){//case when fraction string is entered
//			Numerator = split[0];
//			Denominator = split[1];
//		}
		
		//case when irrational is entered (e,pi)
		//case when sqrt or ^ is used?
		//else?
		this.simplify();
	}
	
	public Fraction (String numerator, String denominator){
		Numerator = new Irrational(numerator);
		Denominator = new Irrational(denominator);
		this.simplify();
	}
	
	public void setNumerator(String val){
		Numerator = new Irrational(val);
		this.simplify();
	}
	
	public void setDenominator(String val){
		Denominator = new Irrational(val);
		this.simplify();
	}
	
	public String getNumerator(){
		return Numerator.toString();
	}
	
	//eclipse said to make this 'visible', removed 'public' from method? potential for errors?
	String getDenominator(){
		return Denominator.toString();
	}
	
	private void simplify(){
		
	}
	
	public String toString(){
		if (Denominator.toString() == "1"){
			return Numerator.toString();
		}
		else {
			return (Numerator.toString() + "/" + Denominator.toString());
		}
	}
	
}
