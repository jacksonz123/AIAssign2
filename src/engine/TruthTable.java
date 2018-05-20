package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TruthTable extends Method {

	private boolean[][] truthTable;

	TruthTable(ArrayList<String> kb, ArrayList<String> symbols, String query) {
		name = "Truth Table";
		knowledgeBase = kb;
		this.symbols = symbols;
		this.query = query;

		int rows = (int) Math.pow(2, symbols.size());
		truthTable = new boolean[rows][symbols.size()];
		// Create Truth Table
		for (int i = 0; i < rows; i++) {
			for (int j = symbols.size() - 1; j >= 0; j--) {
				truthTable[i][j] = i / (int) Math.pow(2, j) % 2 == 1 ? true : false;
			}
		}
	}

	public boolean checkSentence(String sentence, boolean[] tableRow) {
		String[] sentenceOperators = getSentenceOperators(sentence);
		String[] sentenceSymbols = getSentenceSymbols(sentence);
		
		// If no operators
		if (sentenceOperators == null) {
			// just return value of symbol in tt
			return tableRow[symbols.indexOf(sentenceSymbols[0])];
		}
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (int i = 0; i < sentenceSymbols.length; i++) {
			// Populate Map with Symbol and value in truth table
			map.put(sentenceSymbols[i], tableRow[symbols.indexOf(sentenceSymbols[i])]);
		}
		// Check what form the sentence is in
		if (sentenceOperators.length == 1 && sentenceOperators[0].equals("=>")) {
			// get value of symbols and return
			return !map.get(sentenceSymbols[0]) || map.get(sentenceSymbols[1]);
			
		} else if (sentenceSymbols.length > 2 && sentenceOperators[0].equals("&") && sentenceOperators[sentenceOperators.length - 1].equals("=>")) {
			// build results
			boolean result = true;
			for (int i = 0; i < sentenceSymbols.length - 1; i++) {
				// check that sentence is in correct form
				if (sentenceOperators[i].equals("&")) {
					// build the result by anding each symbol
					result = result && map.get(sentenceSymbols[i]);
				}
				else if (i == sentenceSymbols.length - 2 && sentenceOperators[i].equals("=>")) {
					result = result && map.get(sentenceSymbols[i]);
				}
				else {
					System.out.println("Invalid Input, ensure in Horn Form");
					System.exit(1);
				}
			}
			return !result || map.get(sentenceSymbols[sentenceSymbols.length - 1]);

		} else {
			System.out.println("Invalid Input, ensure in Horn Form");
			System.exit(1);
		}
		return false;
	}

	public boolean checkKB(boolean[] tableRow) {
		for (int i = 0; i < knowledgeBase.size(); i++) {
			// Check if sentence is entailed
			if (!checkSentence(knowledgeBase.get(i), tableRow)) {
				// if not return false for KB in tableRow
				return false;
			}
		}
		// If all are entailed in model, return true
		return true;
	}

	public void check() {
		int numberEntailed = 0;
		for (int i = 0; i < truthTable.length; i++) {
			// Check TT against where KB is entailed
			if (checkKB(truthTable[i])) {
				// Check if query is entailed in model
				if (truthTable[i][symbols.indexOf(query)]) {
					numberEntailed++;
				}
			}
		}
		String output = numberEntailed > 0 ? "YES: " + numberEntailed : "NO";
		System.out.println(output);
	}

}
