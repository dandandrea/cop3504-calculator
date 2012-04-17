import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.ListSelectionEvent;


public class inFraction {
	private ArrayList<Irrational> Numerator = new ArrayList<Irrational>();
	private ArrayList<Irrational> Denominator = new ArrayList<Irrational>();
	
	public Fraction (String expression) {
		
		//Split the input string by the "/" character.
		String split[] = expression.split("\\/");
		
		//If we have two elements in split[], that means we have a fraction...
		if (split.length == 2) {
			Numerator.add(new Irrational(split[0]));
			Denominator.add(new Irrational(split[1]));
		} else 
		//Otherwise, we simply have a whole number.
		{
			Numerator.add(new Irrational(expression));
			Denominator.add(new Irrational("1"));
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
		Numerator.add(new Irrational(numerator));
		Denominator.add(new Irrational(denominator));
		this.simplify();
	}
	
	public void setNumerator(String val){
		Numerator.clear();
		Numerator.add(new Irrational(val));
		this.simplify();
	}
	
	public void setNumerator(Irrational val){
		Numerator.clear();
		Numerator.add(val);
		this.simplify();
	}
	
	public void setDenominator(String val){
		Denominator.clear();
		Denominator.add(new Irrational(val));
		this.simplify();
	}
	
	public void setDenominator(Irrational val){
		Numerator.clear();
		Numerator.add(val);
		this.simplify();
	}
	public void multnum(ArrayList<Irrational> arg2){
		int size1 = this.Numerator.size();
		int size2 = arg2.size();
		ArrayList<Irrational> l = new ArrayList<Irrational>();
		Irrational[] placeholder = new Irrational[size1*size2];
		for(int i = 0 ; i<size2; i++){
			
		}
	}
	
	public ArrayList<Irrational> getNumerator(){
		return Numerator;
	}
	
	public ArrayList<Irrational> getDenominator(){
		return Denominator;
	}
	
	private void simplify(){
		if(this.Denominator.size()==1&this.Numerator.size()==1&&isInteger(this.Numerator.get(0).toString())&&isInteger(this.Denominator.get(0).toString())){
			if(this.Denominator.get(0).getNum()!=1){
				EulerSimplify();
			}
		}
	}
	private void EulerSimplify(){
		
		int numer, denom,temp, pastdenom=0;
		Boolean neg = false;
		numer = Integer.parseInt(this.Numerator.get(0).toString());
		denom = Integer.parseInt(this.Denominator.get(0).toString());
		if((numer<0||denom<0)&&!(denom<0&&numer<0)){
			neg = true;
		}
		Double decimal = (double)numer/(double)denom;
		numer = 0;
		denom=1;
		double z = decimal;
		int i = 0;
		
		do{
			if (Math.abs(z -(int) z) < 0.0001) {
				
				numer = ((int) Math.ceil(decimal*denom));
				break;
			}
			z= 1/(z-(int)z);
			temp=denom;
			denom = denom*(int)z +pastdenom;
			pastdenom = temp;
			numer = (int) Math.ceil(decimal*denom);
			i++;
		} while(Math.abs((decimal-((double)numer/(double)denom)))>.001&&z<100&&i<20);
		if(neg){
			numer= -1*numer;
		}
		
		this.Numerator.get(0).setNum(numer);
		this.Denominator.get(0).setNum(denom);
		
	}
	public Boolean isRational(){
		if(this.Numerator.size()==1&&this.Denominator.size()==1&&(this.Denominator.get(0).getEe()==0)&&(this.Denominator.get(0).getPie()==0)&&(this.Numerator.get(0).getEe()==0)&&(this.Numerator.get(0).getPie()==0)&&(this.Numerator.get(0).getSqr().equals("1"))&&(this.Denominator.get(0).getSqr().equals("1"))){
			return true;
		}else{
			return false;
		}
	}
	
	public String toString(){
		this.simplify();
		if (isWholeExpression(this)){
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
	//We should probably put this in another class.
	/**
	 * Returns true if the denominator is equal to (exactly) 1.
	 * @param frac
	 * @return
	 */
	public static boolean isWholeExpression(Fraction frac) { 
		return frac.getDenominator().size()==1&&frac.getDenominator().get(0).toString().equals("1");
	}
}
