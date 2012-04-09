
public class postfixdriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] infix = {"3","*","(","2","+","sqrt:","4",")"};
		
		
		String[] pfix= Postfix.convert(infix);
		System.out.print("[");
		for(int i = 0; i<pfix.length;i++){
			System.out.print(pfix[i]+", ");
		}
		System.out.print("]");
		
		

	}

}
