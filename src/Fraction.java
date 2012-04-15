
public class Fraction {
	private Irrational Numerator;
	private Irrational Denominator;
	
	public Fraction (String expression) {
		
		//Split the input string by the "/" character.
		String split[] = expression.split("\\/");
		
		//If we have two elements in split[], that means we have a fraction...
		if (split.length == 2) {
			Numerator = new Irrational(split[0]);
			Denominator = new Irrational(split[1]);
		} else 
		//Otherwise, we simply have a whole number.
		{
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
	
	public Irrational getNumerator(){
		return Numerator;
	}
	
	//eclipse said to make this 'visible', removed 'public' from method? potential for errors?
	public Irrational getDenominator(){
		return Denominator;
	}
	
	private void simplify(){
		if(isInteger(this.Numerator.toString())&&isInteger(this.Denominator.toString())){
			EulerSimplify();
		}
	}
	private void EulerSimplify(){
		int numer, denom,temp, pastdenom=0;
		Boolean neg = false;
		numer = Integer.parseInt(this.Numerator.toString());
		denom = Integer.parseInt(this.Denominator.toString());
		if((numer<0||denom<0)&&!(denom<0&&numer<0)){
			neg = true;
		}
		Double decimal = (double)numer/(double)denom;
		numer = 0;
		denom=1;
		double z = decimal;
		while(Math.abs((decimal-(double)numer/(double)denom))<100||z==(int)z||z>1000){
			z= 1/(z-(int)z);
			temp=denom;
			denom = denom*(int)z +pastdenom;
			pastdenom = temp;
			numer = (int) Math.ceil(decimal*denom);
			
		}
		if(neg){
			numer= -1*numer;
		}
		
		this.Numerator.setNum(numer);
		this.Denominator.setNum(denom);
		
	}
	
	public String toString(){
		if (isWholeNumber(this)){
			return Numerator.toString();
		}
		else {
			return (Numerator.toString() + "/" + Denominator.toString());
		}
	}
	private boolean isInteger(String string){
		try{
			Integer.parseInt(string);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Returns true if the denominator is equal to (exactly) 1.
	 * @param frac
	 * @return
	 */
	public static boolean isWholeNumber(Fraction frac) {
		return (frac.getDenominator().toString() == "1");
	}
	
}
