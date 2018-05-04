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
		if (sentenceOperators == null) {
			return true;
		}
		String[] sentenceSymbols = getSentenceSymbols(sentence);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (int i = 0; i < sentenceSymbols.length; i++) {
			// Populate Map with Symbol and value in truth table
			map.put(sentenceSymbols[i], tableRow[symbols.indexOf(sentenceSymbols[i])]);
		}

		if (sentenceOperators.length == 1 && sentenceOperators[0].equals("=>")) {
			if (!map.get(sentenceSymbols[0]) || map.get(sentenceSymbols[1])) {
				// Sentence is entailed
				return true;
			} else {
				// Sentence is not entailed
				return false;
			}
		} else if (sentenceOperators.length == 2 && sentenceOperators[0].equals("&")
				&& sentenceOperators[1].equals("=>")) {
			if (!(map.get(sentenceSymbols[0]) && map.get(sentenceSymbols[1])) || map.get(sentenceSymbols[2])) {
				// Sentence is entailed
				return true;
			} else {
				// Sentence is not entailed
				return false;
			}
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
			if (checkKB(truthTable[i])) {
				numberEntailed++;
			}
		}
		System.out.println(numberEntailed);
	}

}
