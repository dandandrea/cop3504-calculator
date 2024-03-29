public class Fraction {
	private Irrational Numerator;
	private Irrational Denominator;

	public Fraction(String expression) {

		// Split the input string by the "/" character.
		String split[] = expression.split("\\/");
		// If we have two elements in split[], that means we have a fraction...
		if (split.length == 2) {
			Numerator = new Irrational(split[0]);
			Denominator = new Irrational(split[1]);
		} else
		// Otherwise, we simply have a whole number.
		{
			Numerator = new Irrational(expression);
			Denominator = new Irrational("1");
		}

		this.simplify();
	}

	public Fraction(String numerator, String denominator) {
		Numerator = new Irrational(numerator);
		Denominator = new Irrational(denominator);
		this.simplify();
	}

	public void setNumerator(String val) {
		Numerator = new Irrational(val);
		this.simplify();
	}

	public void setNumerator(Irrational val) {
		Numerator = val;
		this.simplify();
	}

	public void setDenominator(String val) {
		Denominator = new Irrational(val);
		this.simplify();
	}

	public void setDenominator(Irrational val) {
		Denominator = val;
		this.simplify();
	}

	public Irrational getNumerator() {
		return Numerator;
	}

	public Irrational getDenominator() {
		return Denominator;
	}

	private void simplify() {

		//rationalize
		if (!this.Denominator.getSqr().equals("1")) {
			this.Numerator.multiply(this.Denominator);
			this.Denominator.multiply(this.Denominator);
		}
		//simplify e terms
		if (this.Numerator.getEe() >= this.Denominator.getEe()
				&& this.Denominator.getEe() > 0) {
			this.Numerator.setEe(this.Numerator.getEe() - this.Denominator.getEe());
			this.Denominator.setEe(0);
		}else if ((this.Numerator.getEe() < this.Denominator.getEe())
				&& this.Denominator.getEe() > 0) {
			this.Denominator.setEe(this.Denominator.getEe()-this.Numerator.getEe());
			this.Numerator.setEe(0);
		}
		
		//simplify pi terms
		if (this.Numerator.getPie() >= this.Denominator.getPie()
				&& this.Denominator.getPie() > 0) {
			
			this.Numerator.setPie(this.Numerator.getPie() - this.Denominator.getPie());
			this.Denominator.setPie(0);
		}else if(this.Numerator.getPie() >= this.Denominator.getPie()
				&& this.Denominator.getPie() > 0) {
			
			this.Denominator.setPie(this.Denominator.getPie()-this.Numerator.getPie());
			this.Numerator.setPie(0);
		}
		

			//simplifies rational part of fraction
		if(this.Denominator.getNum()!=1){//takes care of simplifying numbers
			boolean neg = false;
			if ((this.Denominator.getNum()<0) && (this.Numerator.getNum()<0)){
				this.Numerator.setNum(-1*this.Numerator.getNum());
				this.Denominator.setNum(-1*this.Denominator.getNum());
			}
			if ((this.Denominator.getNum()<0) && (this.Numerator.getNum()>0)){
				this.Denominator.setNum(-1*this.Denominator.getNum());
				neg = true;
			}
			if ((this.Denominator.getNum()>0) && (this.Numerator.getNum()<0)){
				this.Numerator.setNum(-1*this.Numerator.getNum());
				neg = true;
			}
			EulerSimplify();
			if (neg)
				this.Numerator.setNum(-1*this.Numerator.getNum());
		}


	}

	private void EulerSimplify() {
		int numer, denom, temp, pastdenom = 0;
		numer = this.Numerator.getNum();
		denom = this.Denominator.getNum();
		Double decimal = (double) numer / (double) denom;
		numer = 0;
		denom = 1;
		double z = decimal;
		int i = 0;

		do {
			if (Math.abs(z - (int) z) < 0.0001) {

				numer = ((int) Math.ceil(decimal * denom));
				break;
			}
			z = 1 / (z - (int) z);
			temp = denom;
			denom = denom * (int) z + pastdenom;
			pastdenom = temp;
			numer = (int) Math.ceil(decimal * denom);
			i++;
		} while (Math.abs((decimal - ((double) numer / (double) denom))) > .001
				&& z < 100 && i < 20);
		
		this.Numerator.setNum(numer);
		this.Denominator.setNum(denom);

	}

	public Boolean isRational() {
		if ((this.Denominator.getEe() == 0) && (this.Denominator.getPie() == 0)
				&& (this.Numerator.getEe() == 0)
				&& (this.Numerator.getPie() == 0)
				&& (this.Numerator.getSqr().equals("1"))
				&& (this.Denominator.getSqr().equals("1"))) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		this.simplify();
		if (Denominator.toString().equals("1")) {
			return Numerator.toString();
		} else {
			return (Numerator.toString() + "/" + Denominator.toString());
		}
	}

	private boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns true if the denominator is equal to (exactly) 1.
	 * 
	 * @param frac
	 * @return
	 */
	public static boolean isWholeExpression(Fraction frac) {
		return frac.getDenominator().toString().equals("1");
	}
}
