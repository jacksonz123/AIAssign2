package engine;

import java.util.ArrayList;

public abstract class Method {
	// Full name of method
	public String name;
	// Knowledge Base
	public ArrayList<String> knowledgeBase;
	// Symbols in KB
	public ArrayList<String> symbols;
	// Query Symbol
	public String query;
	
	public abstract void check();
	
	public String[] getSentenceOperators(String sentence) {
		// Get sentence Symbols
		String[] sentenceSymbols = getSentenceSymbols(sentence);
		if (sentenceSymbols.length <= 1) {
			// No operator for single symbol sentences
			return null;
		}
		// strip symbols to extract operator
		String temp = sentence.replaceFirst(sentenceSymbols[0], "");
		// remove any spaces
		temp = temp.replaceAll("\\s", "");
		for (int i = 1; i < sentenceSymbols.length; i++) {
			temp = temp.replace(sentenceSymbols[i],  " ");
		}
		String[] operators = temp.split(" ");
		return operators;
	}
	
	public String[] getSentenceSymbols(String sentence) {
		// Split by Horn Cause operators
		return sentence.split(";|=>|&");
	}
}
