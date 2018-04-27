package engine;

import java.util.ArrayList;

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
				truthTable[i][j] = i/(int) Math.pow(2, j) % 2 == 1 ? true : false;
			}
		}
	}
	
	
}
