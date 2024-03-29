
public class Irrational {
	
	// Worst case format => 3  * pi^3 * e^3 * sqrt:2, modeled as Num*pi^Pie*e^Ee*sqrt:Sqr, where Sqr can be integer or irrational (ie 2*pi)
	private int Num = 0; //Integer value
	private int Pie = 0; //Exponent of pi
	private int Ee = 0; //Exponent of e
	private String Sqr = "1"; //Value x in sqrt:x
	
	//Constructor to handle a string input of any variation of format 'Num*pi^Pie*e^Ee*sqrt:Sqr'
	public Irrational (String expression){
		//check if theres a negative in expression
		if (quickTest(expression) == true){ // initial check if string is just an integer
			return;
		}
		String[] piTest = expression.split("pi"); // if pi is in expression piTest.length == 2
		String[] eTest = expression.split("e"); // if e is in expression eTest.length == 2
		String[] sqrtTest = expression.split("sqrt:"); // if sqrt: is in expression sqrtTest.length == 2
		String[] numTest = expression.split("\\*"); // tests for multiple parts to expression
		
		//check for integer at beginning of expression
		expression = integerTest(expression, numTest); //assigns Num value, returns expression without Num in it (if it's there)
		numTest = expression.split("\\*");
		String[] ans = null;
		ans = pi_eTest(expression, numTest, piTest, "pi"); //assigns Pie value, returns expression without pi in it (if it's there)
		Pie = Integer.parseInt(ans[0]);
		expression = ans[1];
		numTest = expression.split("\\*");
		ans = pi_eTest(expression, numTest, eTest, "e");
		Ee = Integer.parseInt(ans[0]);
		if (sqrtTest.length == 2)
			Sqr = sqrtTest[1];
		else
			Sqr = "1";
		
	}
	
	private String[] pi_eTest(String expression, String[] numTest, String[] piTest, String test){
		String[] ans = {"0",expression}; // ans[0] is the exponent, ans[1] is the expression
		if ((piTest.length > 1) || ans[1].equals(test)){ //triggers if pi is in expression
			piTest = numTest[0].split("\\^");
			if (numTest.length == 1) { //triggers if pi or pi^x is only form in expression
				if (ans[1].equals(test)){ //expression is pi
					ans[0] = "1";
				} else { //expression is pi^x
					ans[0] = piTest[1];
				}
			} else { //catches the final possibility, forms pi*... or pi^x*...
				if (numTest[0].equals(test)){ //expression is pi*...
					ans[0] = "1";
				} else { //expression is pi^x*...
					ans[0] = piTest[1];
				}
				ans[1] = numTest[1];//rebuild expression
				for (int i = 2; i < numTest.length; i++)
					ans[1] = ans[1] + "*" + numTest[i];
			}
		} else {
			ans[0] = "0"; // pi is not in expression
			ans[1] = expression;
		}
		return ans;
	}
	
	private String integerTest(String expression, String[] numTest){
		if (numTest.length > 0){
			try { // assumes the first division by '*' is an integer
				Num = Integer.parseInt(numTest[0]); // if successful, need to alter expression
				//expression altered to not include integer
				expression = numTest[1];
				for (int i = 2; i < numTest.length; i++)
					expression = expression + "*" + numTest[i];
			} catch (Exception e) {
				Num = 1;
			}
		}
		return expression;
	}
	
	private boolean quickTest(String expression){ // Form: Num (integer)
		if (SymbolicMath.isInteger(expression)){
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

	public int getNum(){
		return Num;
	}
	
	public String getSqr(){
		return Sqr;
	}
	
	public int getPie(){
		return Pie;
	}
	
	public int getEe(){
		return Ee;
	}
	
	public String toString(){
		String toString = "";
		if (Num == 0)
			return toString = "0";
		if (((Num == 1) || (Num == -1)) && (Sqr.equals("1")) && (Pie == 0) && (Ee == 0)){
			if (Num == 1)
				return toString = "1";
			else
				return toString = "-1";
		}

		if ((Num > 1) || (Num < 0)){
			if (Num == -1)
				toString = "-";
			else
				toString = Integer.toString(Num);
		}
		if (Pie != 0){
			if ((Pie == 1) && (toString == ""||toString == "-"))
				toString = toString + "pi";
			else if ((Pie == 1) && (toString != "pi"))
				toString = toString + "*" + "pi";
			else if ((Pie > 1) && (toString == ""))
				toString = "pi^" + Integer.toString(Pie);
			else
				toString = toString + "*" + "pi" + "^" + Integer.toString(Pie);
		}
		if (Ee != 0){
			if ((Ee == 1) && (toString == ""))
				toString = "e";
			else if ((Ee == 1) && (toString != "e"))
				toString = toString + "*" + "e";
			else if ((Ee > 1) && (toString == ""))
				toString = "e^" + Integer.toString(Ee);
			else
				toString = toString + "*" + "e" + "^" + Integer.toString(Ee);
		}	
		if (!Sqr.equals("1")){
			if (toString == "")
				toString = "sqrt:" + Sqr;
			else
				toString = toString + "*" + "sqrt:" + Sqr;
		}
		return toString;
	}
	public boolean add(Irrational num2){
		if(this.Ee==num2.getEe()&&this.Pie==num2.getPie()&&this.Sqr==this.getSqr()){
			this.Num = num2.getNum() + this.Num;
			return true;
		}else{
			/*
			if (!SymbolicMath.isInteger(this.toString()))
				SymbolicMath.addIrrationalItem(this);
			if (!SymbolicMath.isInteger(num2.toString()))
			{
				this.Num = num2.getNum();
				this.Ee = num2.getEe();
				this.Pie = num2.getPie();
				this.Sqr = num2.getSqr();
				SymbolicMath.addIrrationalItem(num2);
			}
			*/
			throw new IllegalStateException("TOO COMPLICATED :(");
		}
	}
	public boolean subtract(Irrational num2){
		if(this.Ee==num2.getEe()&&this.Pie==num2.getPie()&&this.Sqr==this.getSqr()){
			this.Num = num2.getNum() - this.Num;
			return true;
		}else{
			num2.setNum(-1*num2.getNum());
			return false;
		}
	}
	public void multiply(Irrational num2){
		this.Num = num2.getNum()*this.Num;
		this.Ee = this.Ee+num2.getEe();
		this.Pie = this.Pie+num2.getPie();
		//sqrt handling

		if (this.Sqr.equals("1")&&!num2.getSqr().equals("1")){
			this.Sqr = num2.getSqr();
		}
		else if (this.Sqr.equals(num2.getSqr())&&!this.Sqr.equals("1")){
			Irrational sqrt = new Irrational(this.getSqr());
			
			this.Num = sqrt.getNum()*this.Num;
			this.Ee = this.Ee+sqrt.getEe();
			this.Pie = this.Pie+sqrt.getPie();
			this.Sqr = "1";
		}else if(this.Sqr.equals("1")&&num2.getSqr().equals("1")){
			
		}else{
			Irrational sqrt = new Irrational(this.getSqr());
			Irrational sqrt2 = new Irrational(num2.getSqr());
			sqrt.multiply(sqrt2);
			this.Sqr = sqrt.toString();
			
		}
	}
	public void exponentiate(Irrational num2){
		this.Ee = num2.getNum()*this.Ee;
		this.Pie = num2.getNum()*this.Pie;
		this.Num = (int) Math.pow(this.Num, num2.getNum());
		Irrational sqrt = new Irrational(this.Sqr);
		sqrt.Ee = num2.getNum()*sqrt.Ee;
		sqrt.Pie = num2.getNum()*sqrt.Pie;
		sqrt.Num = (int) Math.pow(sqrt.Num, num2.getNum());
		this.Sqr = sqrt.toString();
				
	}

	public void setNum(int num){
		this.Num = num;
	}

	public void setPie(int pie){
		this.Pie = pie;
	}
	public void setEe(int ee){
		this.Ee = ee;
	}
	public void setSqr(String sqr){
		this.Sqr = sqr;
	}

}
