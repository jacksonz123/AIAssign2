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
}
