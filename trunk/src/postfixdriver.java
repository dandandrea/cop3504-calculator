

public class postfixdriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String[] infix = {"3","*","(","2","+","sqrt:","4",")"};
//		String[] nfix = Postfix.NegativeParse(infix);
//		System.out.print("[");
//		for(int i = 0; i<nfix.length;i++){
//			System.out.print(nfix[i]+", ");
//		}
//		System.out.print("]");
		String[] infix = new String[Tokenizer.tokenizeExpression("-3 * (2 - sqrt:4)").size()];
		Tokenizer.tokenizeExpression("-3 * (2 - sqrt:4)").toArray(infix);
		
//		String[] nfix = Postfix.NegativeParse(infix);
//		System.out.print("[");
//		for(int i = 0; i<nfix.length;i++){
//			System.out.print(nfix[i]+", ");
//		}
//		System.out.print("]");	
		
		String[] pfix= Postfix.InfixtoPostfix(infix);
		System.out.print("[");
		for(int i = 0; i<pfix.length;i++){
			System.out.print(pfix[i]+", ");
		}
		System.out.print("]");
		
		

	}

}
