package engine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MethodTest {

	@Test
	void testTTConstructor() {
		ArrayList<String> KB = new ArrayList<String>();
		KB.add("p=>p1");
		KB.add("a=>b");
		KB.add("a&c=>p");
		
		ArrayList<String> KBSymbols = new ArrayList<String>();
		KB.add("p");
		KB.add("p1");
		KB.add("a");
		KB.add("b");
		KB.add("c");
		
		TruthTable tt = new TruthTable(KB, KBSymbols, "p");
		// Test Constructor
		assertEquals(tt.knowledgeBase, KB);
		assertEquals(tt.symbols, KBSymbols);
		assertEquals(tt.query, "p");
	}
	
	@Test
	void testGetSentenceSymbols() {
		ArrayList<String> KB = new ArrayList<String>();
		KB.add("p=>p1");
		KB.add("a=>b");
		KB.add("a&c=>p");
		KB.add("a&b=>p1");
		KB.add("d");
		
		ArrayList<String> KBSymbols = new ArrayList<String>();
		
		TruthTable tt = new TruthTable(KB, KBSymbols, "p");
		
		String[] sentence1 = tt.getSentenceSymbols(tt.knowledgeBase.get(0));
		assertEquals(sentence1[0], "p");
		assertEquals(sentence1[1], "p1");

		String[] sentence3 = tt.getSentenceSymbols(tt.knowledgeBase.get(2));
		assertEquals(sentence3[0], "a");
		assertEquals(sentence3[1], "c");
		assertEquals(sentence3[2], "p");
		
		String[] sentence4 = tt.getSentenceSymbols(tt.knowledgeBase.get(3));
		assertEquals(sentence4[0], "a");
		assertEquals(sentence4[1], "b");
		assertEquals(sentence4[2], "p1");
		
		String[] sentence5 = tt.getSentenceSymbols(tt.knowledgeBase.get(4));
		assertEquals(sentence5[0], "d");
	}

	@Test
	void testGetSentenceOperators() {
		ArrayList<String> KB = new ArrayList<String>();
		KB.add("p=>p1");
		KB.add("a=>b");
		KB.add("a&c=>p");
		KB.add("a&b=>p1");
		KB.add("d");
		
		ArrayList<String> KBSymbols = new ArrayList<String>();

		TruthTable tt = new TruthTable(KB, KBSymbols, "p");
		
		String[] sentence1Operators = tt.getSentenceOperators(tt.knowledgeBase.get(0));
		System.out.println(sentence1Operators[0]);
		assertEquals(sentence1Operators[0], "=>");

		
		String[] sentence3Operators = tt.getSentenceOperators(tt.knowledgeBase.get(2));
		assertEquals(sentence3Operators[0], "&");
		assertEquals(sentence3Operators[1], "=>");
		
		String[] sentence4Operators = tt.getSentenceOperators(tt.knowledgeBase.get(3));
		assertEquals(sentence4Operators[0], "&");
		assertEquals(sentence4Operators[1], "=>");
		
		String[] sentence5Operators = tt.getSentenceOperators(tt.knowledgeBase.get(4));
		assertEquals(sentence5Operators, null);

	}
}
