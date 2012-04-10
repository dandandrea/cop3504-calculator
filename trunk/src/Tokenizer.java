import java.util.*;
import java.util.regex.*;

public class Tokenizer 
{
	//This solution makes heavy use of "regular expressions."
	//Regular expressions are a tool designed for matching sequences
	//of characters in strings, so they're a pretty good fit...
	//just complicated.
	
	//How to read this:
	//'\\'  - precedes any operator character which usually has an
	//        important function within regular expressions.
	//
	//        This indicates that the character itself is to be matched,
	//        rather than treating it as a "regex" operator.
	//
	//
	//[...] - indicates that any character within the brackets may be
	//        matched.  (Technically, it establishes a "character class."
	//
	//        Remember that the '\\'s are there to cancel out the "regex"
	//        functions of some of the math operators.
	//
	//
	// '|'  - indicates that there are two possibilities for generating
	//        a match:  the pattern on the left may be matched, OR the
	//        pattern on the right.
	//
	//        Note that the left pattern consists of single-character
	//        operators, while the right pattern is multi-character.
			
	//                                            "[  +  *  ^  (  )  -=/]|sqrt:   d+rt"
	private static String operatorPatternString = "[\\+\\*\\^\\(\\)\\-=/]|sqrt:|\\d+rt:";
	// Operators: +, *, ^, (, ), -, =, /, sqrt:
		
	// Has the ability to recognize the mathematical constants e and pi.
	
	private static String constantsPatternString = "e|pi";
	
	// Tacks on the ability to recognize integers WITHOUT needing a
	// separating space.  
	//
	// The Scanner class seems to require having spaces between any
	// string elements to be separately recognized.
	
	private static String totalPatternString = operatorPatternString + "|\\d+" + "|" + constantsPatternString;
	
	private static Pattern operatorPattern = Pattern.compile(operatorPatternString);
	
	private static Pattern constantsPattern = Pattern.compile(constantsPatternString);
	
	// Compiles the regex pattern for actual use.
	private static Pattern totalPattern = Pattern.compile(totalPatternString);
	
	
	/**
	 * Uses regular expressions to parse an input expression into 
	 * numbers and operators for the project specifications.  This
	 * implementation does <i>not</i> require spaces between elements
	 * of the expression being parsed!
	 * <p>
	 * Note:  this function simply searches for perfect matches
	 * across the string and does <i>not</i> track unmatched elements.
	 * Thus, it cannot provide you with error information as presently
	 * implemented.
	 * <p>
	 * Secondly, this function does not verify that the parsed expression
	 * is well-formed - that is, that the expression has valid mathematical
	 * syntax.  Example:  "2 * + / 3" would parse well, but is not a valid
	 * expression.
	 * 
	 * 
	 * @param expr - the expression representation to be parsed.
	 * @return a List of Strings, where each String represents a single element
	 * of the expression - number or operator.
	 */
	public static List<String> tokenizeExpression(String expr)
	{
		List<String> tokens = new ArrayList<String>();
		
		// Sets up a pattern matcher for the provided expression.
		Matcher totalMatcher = totalPattern.matcher(expr);
		
		while(totalMatcher.find())
		{
			tokens.add(totalMatcher.group());
		}
		
		return tokens;
	}
	
	
	/**
	 * Uses regular expressions to parse an input expression into 
	 * numbers and operators for the project specifications.  This
	 * implementation <i>requires</i> spaces between elements
	 * of the expression being parsed!
	 * <p>
	 * Since this implementation exposes the mechanism by which the String
	 * is being parsed, it is easily possible to identify foreign, unrecognizable
	 * elements in the input.  The problem:  the Scanner class requires at least one
	 * space to exist between any elements which should end up being separate - valid 
	 * elements may not be parsed correctly without spaces.
	 * <p>
	 * Secondly, this function does not verify that the parsed expression
	 * is well-formed - that is, that the expression has valid mathematical
	 * syntax.  Example:  "2 * + / 3" would parse well, but is not a valid
	 * expression.
	 * 
	 * @param expr - the expression representation to be parsed.
	 * @return a List of Strings, where each String represents a single element
	 * of the expression - number or operator.
	 */
	public static List<String> tokenizeExpressionWithScanner(String expr)
	{
		List<String> tokens = new ArrayList<String>();
		
		Scanner in = new Scanner(expr);
		
		while(in.hasNext())
		{
			if(in.hasNext(operatorPattern))
			{
				tokens.add(in.next(operatorPattern));
			}
			else if(in.hasNextInt())
			{
				tokens.add(in.nextInt() + "");
			}
			else if(in.hasNext(constantsPattern))
			{
				tokens.add(in.next(constantsPattern));
			}
			else
			{
				throw new IllegalStateException("Invalid input token detected in expression:  " + in.next());
			}
		}
		
		return tokens;
	}
}
