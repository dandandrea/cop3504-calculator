import java.util.Scanner;



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
		Scanner buddy = new Scanner(System.in);
		String sting = buddy.nextLine();
		String[] infix = new String[Tokenizer.tokenizeExpression(sting).size()];
		Tokenizer.tokenizeExpression(sting).toArray(infix);
		
//		String[] nfix = Postfix.NegativeParse(infix);
//		System.out.print("[");
//		for(int i = 0; i<nfix.length;i++){
//			System.out.print(nfix[i]+", ");
//		}
//		System.out.print("]");	
		
		String[] pfix= Postfix.InfixtoPostfix(infix);
//		System.out.print("[");
//		for(int i = 0; i<pfix.length;i++){
//			System.out.print(pfix[i]+", ");
//		}
//		System.out.print("]");	
		
		
		System.out.println(ApproximateMath.Approximate(pfix));
		
		

	}

}
